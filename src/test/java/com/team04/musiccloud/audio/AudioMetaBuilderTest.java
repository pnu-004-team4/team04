package com.team04.musiccloud.audio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import org.junit.Test;

public class AudioMetaBuilderTest {

  private static final String DB_ID = "123";
  private static final String TITLE = "tit";
  private static final String AUTHOR = "auth";
  private static final String ALBUM = "alb";
  private static final LocalDateTime DATE_TIME = LocalDateTime.of(2019, 5, 7, 1, 0);
  private static final int DURATION = 123;
  private static final int COUNTER = 0;

  @Test
  public void builderConstructorTest() {
    AudioMetaBuilder audioMetaBuilder = AudioMetaBuilder.builder();

    assertNotNull(audioMetaBuilder);
  }

  @Test
  public void builderConstructorTest2() {
    AudioMeta audioMeta1 = new AudioMeta(null, null, null, null, null, 0, -1);
    AudioMeta audioMeta2 = AudioMetaBuilder.builder(audioMeta1).build();

    assertTrue(audioMeta1.isEmpty());
    assertTrue(audioMeta2.isEmpty());
  }

  @Test
  public void builderContentsTest() {
    AudioMeta audioMeta = AudioMetaBuilder.builder()
        .setDbId(DB_ID)
        .setTitle(TITLE)
        .setAuthor(AUTHOR)
        .setAlbum(ALBUM)
        .setReleaseDate(DATE_TIME)
        .setDurationMs(DURATION)
        .setPlayCount(COUNTER)
        .build();

    assertEquals(DB_ID, audioMeta.getDbId());
    assertEquals(TITLE, audioMeta.getTitle());
    assertEquals(AUTHOR, audioMeta.getAuthor());
    assertEquals(ALBUM, audioMeta.getAlbum());
    assertEquals(DATE_TIME, audioMeta.getReleaseDate());
    assertEquals(DURATION, audioMeta.getDurationMs());
    assertEquals(COUNTER, audioMeta.getPlayCount());
  }

  @Test
  public void builderFromAudioMeta() {
    final AudioMeta source = new AudioMeta(DB_ID, TITLE, AUTHOR, ALBUM, DATE_TIME, DURATION,
        COUNTER);
    AudioMeta audioMeta = AudioMetaBuilder.builder(source).build();

    assertEquals(DB_ID, audioMeta.getDbId());
    assertEquals(TITLE, audioMeta.getTitle());
    assertEquals(AUTHOR, audioMeta.getAuthor());
    assertEquals(ALBUM, audioMeta.getAlbum());
    assertEquals(DATE_TIME, audioMeta.getReleaseDate());
    assertEquals(DURATION, audioMeta.getDurationMs());
    assertEquals(COUNTER, audioMeta.getPlayCount());
  }
}
