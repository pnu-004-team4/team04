package com.team04.musiccloud.stream.transcode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import be.hogent.tarsos.transcoder.DefaultAttributes;
import com.team04.musiccloud.audio.Audio;
import com.team04.musiccloud.audio.extractor.AudioExtractor;
import com.team04.musiccloud.audio.extractor.Mp3Extractor;
import com.team04.musiccloud.utilities.StaticPaths;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

public class TranscodeTest {

  private static Path cacheDirectory = StaticPaths.tempStorage;
  private static Path storageDirectory = StaticPaths.storage;
  private Transcode transcode;
  private static final Logger logger = Logger.getGlobal();

  @Before
  public void setUp() throws Exception {
    final String user = "admin@admin.com";
    logger.info(cacheDirectory.resolve(user).toString());
    File directory = new File(cacheDirectory.resolve(user).toString());

    if (!directory.exists()) {
      assertTrue(directory.mkdirs());
    }

    Files.copy((storageDirectory.resolve(user)).resolve("sample.mp3")
        , (cacheDirectory.resolve(user)).resolve("sample.mp3")
        , StandardCopyOption.REPLACE_EXISTING);

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

    transcode.setWeight(14000); // 14s
    weight = transcode.getWeight();
    assertEquals(DefaultAttributes.MP3_128KBS_STEREO_44KHZ, transcode.getAudioSetting(weight));

    transcode.setWeight(7000); // 7s
    weight = transcode.getWeight();
    assertEquals(DefaultAttributes.MP3_192KBS_STEREO_44KHZ, transcode.getAudioSetting(weight));

    transcode.setWeight(1000); // 1s
    weight = transcode.getWeight();
    assertEquals(DefaultAttributes.MP3_320KBS_STEREO_44KHZ, transcode.getAudioSetting(weight));
  }
}