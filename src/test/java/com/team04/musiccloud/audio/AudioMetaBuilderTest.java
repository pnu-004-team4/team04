package com.team04.musiccloud.audio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;
import org.junit.Test;

public class AudioMetaBuilderTest {

  private static final String DB_ID = "123";
  private static final String TITLE = "tit";
  private static final String AUTHOR = "auth";
  private static final String ALBUM = "alb";
  private static final LocalDateTime DATE_TIME = LocalDateTime.of(2019, 5, 7, 1, 0);
  private final static int DURATION = 123;
  private final static int COUNTER = 0;

  @Test
  public void builderConstructorTest() {
    AudioMetaBuilder audioMetaBuilder = AudioMetaBuilder.builder();

    assertNotNull(audioMetaBuilder);
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