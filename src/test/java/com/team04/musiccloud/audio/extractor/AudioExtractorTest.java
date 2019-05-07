package com.team04.musiccloud.audio.extractor;

import com.team04.musiccloud.audio.Audio;
import com.team04.musiccloud.utilities.StaticPaths;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertNotNull;

public class AudioExtractorTest {
    @Test
    public void extractorTest() throws IOException, InvalidFileFormat, ExtractorException {
        MultipartFile multipartFile = getMockMultipartFile();
        final String userName = "user";
        
        AudioExtractor audioExtractor = ExtractorFactory.getInstance(multipartFile);
        Audio audio = audioExtractor.getAudio(multipartFile, userName);
        
        assertNotNull(audio);
    }
    
    @Test
    public void setBaseDirectory() throws IOException, InvalidFileFormat {
        final Path path = Paths.get("test", "directory");
        MultipartFile multipartFile = getMockMultipartFile();
        AudioExtractor audioExtractor = ExtractorFactory.getInstance(multipartFile);
        
        audioExtractor.setBaseDirectory(path);
    }
    
    private static MultipartFile getMockMultipartFile() throws IOException {
        final String fileName = "sample.mp3";
        final String userName = "test";
        final Path filePath = StaticPaths.storage
                .resolve(userName)
                .resolve(fileName)
                .toAbsolutePath();
        
        return new MockMultipartFile(filePath.toString(), fileName, null, new FileInputStream(filePath.toFile()));
    }
}