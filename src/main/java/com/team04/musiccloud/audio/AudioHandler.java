package com.team04.musiccloud.audio;

import com.team04.musiccloud.audio.extractor.AudioExtractor;
import com.team04.musiccloud.audio.extractor.ExtractorException;
import com.team04.musiccloud.audio.extractor.ExtractorFactory;
import com.team04.musiccloud.audio.extractor.InvalidFileFormat;
import com.team04.musiccloud.db.MetadataCustomRepository;
import com.team04.musiccloud.stream.caching.manager.CacheManager;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class AudioHandler {
    private String user;
    
    public AudioHandler(String user) {
        this.user = user;
    }
    
    public void requestUpload(MultipartFile multipartFile) throws InvalidFileFormat, IOException, ExtractorException {
        final AudioExtractor extractor = ExtractorFactory.getInstance(multipartFile);
        Audio audio = extractor.getAudio(multipartFile, user);
        
        saveAudioToStorage(audio);
        saveMetaToDb(audio);
    }
    
    public void requestLoad(String user, String dbId) throws IOException {
        final MetadataCustomRepository customRepository = new MetadataCustomRepository(this.user);
        final AudioMeta audioMeta = customRepository.getAudioMeta(dbId);
        final FileMeta fileMeta = customRepository.getFileMeta(dbId);
        
        // @TODO Remove this log when done with the project
        LOGGER.info("\n" +
                "------ fileMeta ------\n" +
                "\tDB ID: " + fileMeta.getDbId() + "\n" +
                "\tPath: " + fileMeta.getDirectory() + "\n" +
                "\tFile: " + fileMeta.getName() + "\n" +
                "\tExtension: " + fileMeta.getExtension() + "\n" +
                "\tUser: " + fileMeta.getUser() + "\n" +
                "-----------------"
        );
        
        final FileMeta cacheFileMeta = getCacheFileMeta(user, fileMeta);
        final Audio audio = new Audio(audioMeta, cacheFileMeta, null);
        
        // @TODO Send audio to transcoding module
        //new Streaming().getAudioFromBack(audio);
    }
    
    private FileMeta getCacheFileMeta(String user, FileMeta fileMeta) throws IOException {
        final CacheManager cacheManager = new CacheManager(user);
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
        try ( FileOutputStream fileOutputStream = new FileOutputStream(fullPath) ) {
            fileOutputStream.write(audio.getBytes());
        }
    }
    
    private void saveMetaToDb(Audio audio) {
        new MetadataCustomRepository(user)
                .storeMetadata(audio.getAudioMeta(), audio.getFileMeta());
    }
    
    private void deleteAudioFromStorage(String dbId) throws IOException {
        final MetadataCustomRepository customRepository = new MetadataCustomRepository(user);
        final FileMeta audio = customRepository.getFileMeta(dbId);
        Files.deleteIfExists(audio.getFullPath());
    }
    
    private void deleteMetaFromDb(String dbId) {
        new MetadataCustomRepository(user)
                .deleteMetadata(dbId);
    }
    
}
