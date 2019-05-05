package com.team04.musiccloud.stream;

import static org.junit.Assert.assertEquals;

import com.team04.musiccloud.audio.Audio;
import com.team04.musiccloud.audio.extractor.AudioExtractor;
import com.team04.musiccloud.audio.extractor.Mp3Extractor;
import com.team04.musiccloud.utilities.StaticPaths;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

public class StreamingTest {

  private static Path cacheDirectory = StaticPaths.tempStorage;
  private Streaming stream;
  private Audio testAudio;

  @Before
  public void setUp() throws Exception {
    stream = new Streaming();
    final String user = "CSK";
    final Path currentLocation = cacheDirectory.resolve(user)
        .resolve("sample.mp3").toAbsolutePath();
    final AudioExtractor extractor = new Mp3Extractor();

    MultipartFile myFile = new MockMultipartFile(currentLocation.toString(),
        "sample.mp3", null, new FileInputStream(currentLocation.toFile()));
    extractor.setBaseDirectory(cacheDirectory);
    testAudio = extractor.getAudio(myFile, user);
    System.out.println(testAudio.getFileMeta().getDirectory());
    System.out.println(testAudio.getFileMeta().getFullPath());
    assertEquals(currentLocation.toString(), testAudio.getFileMeta().getFullPath().toString());
  }

  @After
  public void tearDown() throws Exception {
    stream = null;
    testAudio = null;
  }

  @Test
  public void audioTransportTest() throws IOException {
    stream.getAudioFromBack(testAudio);
    assertEquals("server/temp/CSK/sample.mp3", stream.sendAudioToFront());
    stream.getAudioFromBack(testAudio);
    stream.setUseTranscode(true);
    assertEquals("server/temp/CSK/sample.mp3", stream.sendAudioToFront());

  }
}