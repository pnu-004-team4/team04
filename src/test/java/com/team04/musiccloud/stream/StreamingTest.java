package com.team04.musiccloud.stream;

import com.team04.musiccloud.audio.Audio;
import com.team04.musiccloud.audio.extractor.AudioExtractor;
import com.team04.musiccloud.audio.extractor.Mp3Extractor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class StreamingTest {

  private Streaming stream;
  private Audio testAudio;

  private static Path cacheDirectory = Paths
      .get(System.getProperty("user.dir"), "src", "main", "resources", "static/media", "audios");

  @Before
  public void setUp() throws Exception {
    stream = new Streaming();
    final String user = "CSK";
    final Path currentLocation = cacheDirectory.resolve(user)
        .resolve("sample.mp3").toAbsolutePath();
    final AudioExtractor extractor = new Mp3Extractor();

    MultipartFile myFile = new MockMultipartFile(currentLocation.toString(),
        "sample.mp3", null, new FileInputStream(currentLocation.toFile()));
    testAudio = extractor.getAudio(myFile, user);
  }

  @After
  public void tearDown() throws Exception {
    stream = null;
    testAudio = null;
  }

  @Test
  public void test() {
    stream.getAudioFromBack(testAudio);
    assertEquals("media/audios/CSK/sample.mp3", stream.sendAudioToFront());
  }
}