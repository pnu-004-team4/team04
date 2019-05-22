package com.team04.musiccloud.audio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import org.junit.Test;

public class AudioMetaTest {

  private final static String ID = "id";
  private final static String TITLE = "tit";
  private final static String AUTHOR = "auth";
  private final static String ALBUM = "alb";
  private final static LocalDateTime DATE_TIME = LocalDateTime.of(2019, 5, 7, 1, 0);
  private final static int DURATION = 123;
  private final static int COUNTER = 0;

  @Test
  public void constructorTest() {
    AudioMeta audioMeta1 = new AudioMeta(TITLE, AUTHOR, ALBUM, DATE_TIME, DURATION, COUNTER);
    assertNotNull(audioMeta1);

    AudioMeta audioMeta2 = new AudioMeta(ID, TITLE, AUTHOR, ALBUM, DATE_TIME, DURATION, COUNTER);
    assertNotNull(audioMeta2);

    AudioMeta audioMeta3 = new AudioMeta(audioMeta1);
    assertNotNull(audioMeta3);
  }

  @Test
  public void equalityTest() {
    AudioMeta audioMeta1 = new AudioMeta(ID, TITLE, AUTHOR, ALBUM, DATE_TIME, DURATION, COUNTER);
    AudioMeta audioMeta2 = new AudioMeta(ID, TITLE, AUTHOR, ALBUM, DATE_TIME, DURATION, COUNTER);
    FakeAudioMeta fakeAudioMeta = new FakeAudioMeta();

    assertEquals(audioMeta1, audioMeta1);
    assertEquals(audioMeta1, audioMeta2);
    assertNotEquals(null, audioMeta1);
    assertNotEquals(audioMeta1, fakeAudioMeta);

    assertEquals(audioMeta1.hashCode(), audioMeta2.hashCode());
  }

  @Test
  public void hasTest() {
    AudioMeta audioMeta = new AudioMeta(ID, TITLE, AUTHOR, ALBUM, DATE_TIME, DURATION, COUNTER);

    assertTrue(audioMeta.hasDbId());
    assertTrue(audioMeta.hasTitle());
    assertTrue(audioMeta.hasAuthor());
    assertTrue(audioMeta.hasAlbum());
    assertTrue(audioMeta.hasReleaseDate());
    assertTrue(audioMeta.hasDurationMs());
    assertTrue(audioMeta.hasPlayCount());

    assertFalse(audioMeta.isEmpty());
  }

  @Test
  public void getterTest() {
    AudioMeta audioMeta = new AudioMeta(ID, TITLE, AUTHOR, ALBUM, DATE_TIME, DURATION, COUNTER);

    assertEquals(ID, audioMeta.getDbId());
    assertEquals(TITLE, audioMeta.getTitle());
    assertEquals(AUTHOR, audioMeta.getAuthor());
    assertEquals(ALBUM, audioMeta.getAlbum());
    assertEquals(DATE_TIME, audioMeta.getReleaseDate());
    assertEquals(DURATION, audioMeta.getDurationMs());
    assertEquals(COUNTER, audioMeta.getPlayCount());
  }

  private class FakeAudioMeta {

  }
}