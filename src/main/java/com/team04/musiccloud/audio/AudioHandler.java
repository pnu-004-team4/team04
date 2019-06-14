package com.team04.musiccloud.audio;

import com.beust.jcommander.ParameterException;
import com.team04.musiccloud.audio.extractor.AudioExtractor;
import com.team04.musiccloud.audio.extractor.ExtractorException;
import com.team04.musiccloud.audio.extractor.ExtractorFactory;
import com.team04.musiccloud.audio.extractor.InvalidFileFormat;
import com.team04.musiccloud.db.MetadataCustomRepository;
import com.team04.musiccloud.stream.caching.manager.CacheManager;
import com.team04.musiccloud.stream.transcode.Transcode;
import com.team04.musiccloud.utilities.network.NetStatusManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

public class AudioHandler {

  private final static Logger logger = Logger.getGlobal();
  private String user;

  public AudioHandler(String user) {
    this.user = user;
  }

  public void requestUpload(MultipartFile multipartFile)
      throws InvalidFileFormat, IOException, ExtractorException {
    final AudioExtractor extractor = ExtractorFactory.getInstance(multipartFile);
    Audio audio = extractor.getAudio(multipartFile, user);

    saveAudioToStorage(audio);
    saveMetaToDb(audio);
  }

  private Audio doTranscodeAudio(String username, Audio audio) throws IOException {
    NetStatusManager netStatusManager = NetStatusManager.getInstance();
    double connectionQuality = netStatusManager.getUserNetDelayAverage(username);

    Transcode transcode = new Transcode(audio);
    transcode.setWeight(connectionQuality);
    transcode.start();

    return audio;
  }

  private byte[] getAudioFromStorage(Audio audio) throws IOException {
    return new MockMultipartFile(
        audio.getFileMeta().getFullPath().toString(),
        audio.getFileMeta().getNameExtension(),
        null,
        new FileInputStream(audio.getFileMeta().getFullPathAsFile())
    ).getBytes();
  }

  public Audio requestLoad(Boolean isDoTranscode, String user, String dbId)
      throws IOException, ParameterException, InterruptedException {
    final MetadataCustomRepository customRepository = MetadataCustomRepository
        .getInstance(this.user);
    AudioMeta audioMeta = customRepository.getAudioMeta(dbId);
    final CacheManager cacheManager = new CacheManager(user);
    FileMeta fileMeta = customRepository.getFileMeta(dbId);

    final FileMeta cacheFileMeta = getCacheFileMeta(cacheManager, fileMeta);
    Audio audio = new Audio(audioMeta, cacheFileMeta, null);

    for (int counter = 0; counter < 5; counter++) {
      boolean isMakeTheFile = false;

      try {
        if (cacheManager.isDoCreated() && isDoTranscode) {
          audio = doTranscodeAudio(user, audio);
          isMakeTheFile = true;
        }
        if (!isDoTranscode || !cacheManager.isDoCreated()) {
          isMakeTheFile = true;
        }
      } catch (Exception e) {
        logger.warning("file doesn't exist");
        isMakeTheFile = false;
      }

      if (isMakeTheFile) {
        break;
      }
      Thread.sleep(1000);
    }

    audio.setBytes(getAudioFromStorage(audio));

    audioMeta.setPlayCount(audioMeta.getPlayCount() + 1);
    logger.info(Integer.toString(audioMeta.getPlayCount()));
    customRepository.updateMetadata(audioMeta, audio.getFileMeta());

    return audio;
  }

  private FileMeta getCacheFileMeta(CacheManager cacheManager, FileMeta fileMeta)
      throws IOException {
    cacheManager.updateOrLoad(fileMeta.getDirectoryAsPath(), fileMeta.getNameExtension());

    final String cacheDirectory = cacheManager.getUserCachePath().toString();
    return FileMetaBuilder.builder(fileMeta)
        .setDirectory(cacheDirectory)
        .build();
  }

  public void requestDelete(String dbId) throws IOException {
    deleteAudioFromStorage(dbId);
    deleteMetaFromDb(dbId);
  }

  private void saveAudioToStorage(Audio audio) throws IOException {
    final File fullPath = audio.getFileMeta().getFullPathAsFile();
    final Path directoryPath = audio.getFileMeta().getDirectoryAsPath();

    File directory = new File(directoryPath.toString());

    if (directory.mkdirs()) {
      logger.info("directory");
    }
    try (FileOutputStream fileOutputStream = new FileOutputStream(fullPath)) {
      fileOutputStream.write(audio.getBytes());
    }
  }

  private void saveMetaToDb(Audio audio) {
    MetadataCustomRepository.getInstance(user)
        .storeMetadata(audio.getAudioMeta(), audio.getFileMeta());
  }

  private void deleteAudioFromStorage(String dbId) throws IOException {
    final MetadataCustomRepository customRepository = MetadataCustomRepository.getInstance(user);
    final FileMeta audio = customRepository.getFileMeta(dbId);
    Files.deleteIfExists(audio.getFullPath());
  }

  private void deleteMetaFromDb(String dbId) {
    MetadataCustomRepository.getInstance(user)
        .deleteMetadata(dbId);
  }

}
