package com.team04.musiccloud.stream.transcode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import be.hogent.tarsos.transcoder.DefaultAttributes;
import com.team04.musiccloud.audio.Audio;
import com.team04.musiccloud.controller.PlayerController;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TranscodeTest {

  private Transcode transcode;

  @Before
  public void setUp() throws Exception {
    Audio audio = PlayerController.getTestAudio();
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