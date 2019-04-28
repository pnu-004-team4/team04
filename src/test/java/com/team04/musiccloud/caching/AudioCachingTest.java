package com.team04.musiccloud.caching;

import static org.junit.Assert.*;

import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AudioCachingTest {
  private AudioCaching audioCaching = null;

  @Before
  public void setUp(){
    final String currentDirectory= Paths.get(System.getProperty("user.dir"),
        "src", "main", "resources", "static/media", "audios").toString();
    audioCaching = new AudioCaching(currentDirectory);
    assertEquals(currentDirectory, audioCaching.getBaseDirectory());
    audioCaching = new AudioCaching();
    assertEquals(currentDirectory, audioCaching.getBaseDirectory());
    audioCaching.setTimeUnit(TimeUnit.SECONDS);
    audioCaching.setPeriod(30);
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