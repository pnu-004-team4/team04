package com.team04.musiccloud.audio.extractor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.team04.musiccloud.audio.Audio;
import com.team04.musiccloud.audio.FileMeta;
import com.team04.musiccloud.utilities.StaticPaths;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

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
  public void setBaseDirectory() throws IOException, InvalidFileFormat, ExtractorException {
    MultipartFile multipartFile = getMockMultipartFile();
    AudioExtractor audioExtractor = ExtractorFactory.getInstance(multipartFile);

    final Path path = Paths.get("test", "directory");
    audioExtractor.setBaseDirectory(path);

    final String userName = "s";
    final Audio audio = audioExtractor.getAudio(multipartFile, userName);
    final FileMeta fileMeta = audio.getFileMeta();
    assertEquals(path.resolve(userName).toString(), fileMeta.getDirectory());
  }

  private static MultipartFile getMockMultipartFile() throws IOException {
    final String fileName = "sample.mp3";
    final String userName = "test";
    final Path filePath = StaticPaths.storage
        .resolve(userName)
        .resolve(fileName)
        .toAbsolutePath();

    return new MockMultipartFile(filePath.toString(), fileName, null,
        new FileInputStream(filePath.toFile()));
  }
}