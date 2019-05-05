package com.team04.musiccloud.stream.transcode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import be.hogent.tarsos.transcoder.DefaultAttributes;
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

public class TranscodeTest {

  private static Path cacheDirectory = StaticPaths.tempStorage;
  private Transcode transcode;

  @Before
  public void setUp() throws Exception {
    final String user = "CSK";
    final Path currentLocation = cacheDirectory.resolve(user)
        .resolve("sample.mp3").toAbsolutePath();
    final AudioExtractor extractor = new Mp3Extractor();

    MultipartFile myFile = new MockMultipartFile(currentLocation.toString(),
        "sample.mp3", null, new FileInputStream(currentLocation.toFile()));
    extractor.setBaseDirectory(cacheDirectory);
    Audio audio = extractor.getAudio(myFile, user);
    transcode = new Transcode(audio);
  }

  @After
  public void tearDown() {
    transcode = null;
  }

  @Test
  public void getAudioTest() throws IOException {
    Audio audio;
    transcode.setWeight(24);
    audio = transcode.getAudio();
    assertNotNull(audio);

    transcode.setWeight(26);
    audio = transcode.getAudio();
    assertNotNull(audio);
  }

  @Test
  public void setWeightTest() {
    double weight;

    transcode.setWeight(81);
    weight = transcode.getWeight();
    assertEquals(DefaultAttributes.MP3_128KBS_STEREO_44KHZ, transcode.getAudioSetting(weight));

    transcode.setWeight(66);
    weight = transcode.getWeight();
    assertEquals(DefaultAttributes.MP3_192KBS_STEREO_44KHZ, transcode.getAudioSetting(weight));

    transcode.setWeight(23);
    weight = transcode.getWeight();
    assertEquals(DefaultAttributes.MP3_320KBS_STEREO_44KHZ, transcode.getAudioSetting(weight));
  }
}