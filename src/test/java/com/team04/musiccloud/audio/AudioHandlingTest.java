package com.team04.musiccloud.audio;

import com.team04.musiccloud.audio.extractor.AudioExtractor;
import com.team04.musiccloud.audio.extractor.ExtractorException;
import com.team04.musiccloud.audio.extractor.ExtractorFactory;
import com.team04.musiccloud.audio.extractor.InvalidFileFormat;
import com.team04.musiccloud.stream.caching.manager.CacheManager;
import com.team04.musiccloud.utilities.FileSystemUtilities;
import com.team04.musiccloud.utilities.StaticPaths;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class AudioHandlingTest {
    
    @Test
    public void testUploader()
            throws IOException, ExtractorException, InvalidFileFormat {
        
        MultipartFile multipartFile = getMockMultipartFile();
        upload(multipartFile, "CSK");
    }
    
    @Test
    public void testLoader() {
    
    }
    
    private void load(String mongoId) throws IOException {
        FileMeta fileMeta = getAudioFromDB(mongoId);
        CacheManager cacheManager = new CacheManager();
        final Path userTempDirectory = cacheManager.getUserTemp(fileMeta.getUser());
        
        if ( !cacheManager.exists(fileMeta) ) {
            cacheManager.loadFrom(fileMeta);
        } else {
            FileSystemUtilities.updateModifiedDate(userTempDirectory);
        }
        
        sendToTranscoding(userTempDirectory);
    }
    
    private FileMeta getAudioFromDB(String mongoId) {
        final Path path = Paths.get(StaticPaths.storage.toString(), "test");
        return new FileMeta(path.toString(), "sample", "mp3", "test");
    }
    
    private void sendToTranscoding(Path tempPath) {
    
    }
    
    public void upload(MultipartFile multipartFile, String user)
            throws IOException, ExtractorException, InvalidFileFormat {
        
        final AudioExtractor extractor = ExtractorFactory.getInstance(multipartFile);
        Audio audio = extractor.getAudio(multipartFile, user);
        
        saveAudioToStorage(audio);
        saveMetaToDB(audio.getAudioMeta(), audio.getFileMeta());
    }
    
    private MultipartFile getMockMultipartFile() throws IOException {
        final String fileName = "sample.mp3";
        final String userName = "test";
        final Path filePath = StaticPaths.storage
                .resolve(userName)
                .resolve(fileName)
                .toAbsolutePath();
        
        return new MockMultipartFile(filePath.toString(), fileName, null, new FileInputStream(filePath.toFile()));
    }
    
    private void saveAudioToStorage(Audio audio) throws IOException {
        try ( FileOutputStream fileOutputStream = new FileOutputStream(audio.getFileMeta().getFullPathAsFile()) ) {
            fileOutputStream.write(audio.getBytes());
        }
    }
    
    private void saveMetaToDB(AudioMeta audioMeta, FileMeta fileMeta) {
        LOGGER.info("" +
                "--------------------\n" +
                "DB received: \n" +
                "\tTitle: " + audioMeta.getTitle() + "\n" +
                "\tAuthor: " + audioMeta.getAuthor() + "\n" +
                "\tReleaseDate: " + audioMeta.getReleaseDate() + "\n" +
                "\t---\n" +
                "\tDirectory: " + fileMeta.getDirectory() + "\n" +
                "\tName: " + fileMeta.getName() + "\n" +
                "\tExtension: " + fileMeta.getExtension() + "\n" +
                "--------------------"
        );
    }
}