package com.team04.musiccloud.stream.caching;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.team04.musiccloud.utilities.StaticPaths;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AudioCachingTest {

  private AudioCaching audioCaching = null;

  @Before
  public void setUp() {
    final String currentDirectory = StaticPaths.tempStorage.toString();
    audioCaching = new AudioCaching(currentDirectory);
    assertEquals(currentDirectory, audioCaching.getBaseDirectory());
    audioCaching = new AudioCaching();
    assertEquals(currentDirectory, audioCaching.getBaseDirectory());
    audioCaching.setTimeUnit(TimeUnit.SECONDS);
    audioCaching.setPeriod(30);
    assertNotNull(audioCaching);
  }

  @After
  public void tearDown() {
    audioCaching = null;
  }

  @Test
  public void start() {
    audioCaching.start();
  }
}