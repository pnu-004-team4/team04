package com.team04.musiccloud.stream.caching;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AudioCollectorTest {

  private AudioCollector audioCollector = null;

  @Before
  public void setUp() throws Exception {
    audioCollector = new AudioCollector("sample/dir");
    assertEquals("sample/dir", audioCollector.getBaseDirectory());
    audioCollector = new AudioCollector();
    assertEquals(audioCollector.DEFAULT, audioCollector.getBaseDirectory());
  }

  @After
  public void tearDown() throws Exception {
    audioCollector = null;
  }

  @Test
  public void run() {
    audioCollector.setPeriod(200); // 이 값은 파일에 따라 달리 하도록 합니다.
    audioCollector.setTimeUnit(TimeUnit.MINUTES);
    audioCollector.run();

    audioCollector.setPeriod(100);
    audioCollector.setTimeUnit(TimeUnit.MINUTES);
    audioCollector.run();
  }
}