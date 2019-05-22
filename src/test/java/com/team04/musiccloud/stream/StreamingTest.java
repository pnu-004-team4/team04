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

  private static Path storageDirectory = StaticPaths.storage;
  private Streaming stream;
  private Audio testAudio;

  @Before
  public void setUp() throws Exception {
    stream = new Streaming();
    final String user = "admin@admin.com";
    final Path currentLocation = storageDirectory.resolve(user)
        .resolve("sample.mp3").toAbsolutePath();
    final AudioExtractor extractor = new Mp3Extractor();

    MultipartFile myFile = new MockMultipartFile(currentLocation.toString(),
        "sample.mp3", null, new FileInputStream(currentLocation.toFile()));
    extractor.setBaseDirectory(storageDirectory);
    testAudio = extractor.getAudio(myFile, user);
    System.out.println(testAudio.getFileMeta().getDirectory());
    System.out.println(testAudio.getFileMeta().getFullPath());
    assertEquals(currentLocation.toString(), testAudio.getFileMeta().getFullPath().toString());
  }

  @After
  public void tearDown() {
    stream = null;
    testAudio = null;
  }

  @Test
  public void audioTransportTest() throws IOException {
    stream.getAudioFromBack(testAudio);
    assertEquals("download?id=null", stream.sendAudioToFront());
  }
}