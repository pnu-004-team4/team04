package com.team04.musiccloud.audio.extractor;

import com.team04.musiccloud.audio.Audio;
import com.team04.musiccloud.audio.FileMeta;
import com.team04.musiccloud.utilities.StaticPaths;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AudioExtractorTest {
    final static private String USER_NAME = "admin@admin.com";

    @Test
    public void extractorTest() throws IOException, InvalidFileFormat, ExtractorException {
        MultipartFile multipartFile = getMockMultipartFile();
        final String userName = "user";

        AudioExtractor audioExtractor = ExtractorFactory.getInstance(multipartFile);
        Audio audio = audioExtractor.getAudio(multipartFile, userName);

        assertNotNull(audio);
    }

    @Test
    public void setBaseDirectory() throws IOException, InvalidFileFormat, ExtractorException {
        MultipartFile multipartFile = getMockMultipartFile();
        AudioExtractor audioExtractor = ExtractorFactory.getInstance(multipartFile);

        final Path path = Paths.get(USER_NAME, "directory");
        audioExtractor.setBaseDirectory(path);

        final String userName = "s";
        final Audio audio = audioExtractor.getAudio(multipartFile, userName);
        final FileMeta fileMeta = audio.getFileMeta();
        assertEquals(path.resolve(userName).toString(), fileMeta.getDirectory());
    }

    private static MultipartFile getMockMultipartFile() throws IOException {
        final String fileName = "sample.mp3";
        final Path filePath = StaticPaths.storage
                .resolve(USER_NAME)
                .resolve(fileName)
                .toAbsolutePath();

        return new MockMultipartFile(filePath.toString(), fileName, null, new FileInputStream(filePath.toFile()));
    }
}