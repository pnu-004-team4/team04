/*
package com.team04.musiccloud.db.dao;

import com.beust.jcommander.ParameterException;
import com.mongodb.MongoException;
import com.team04.musiccloud.audio.AudioMeta;
import com.team04.musiccloud.audio.AudioMetaBuilder;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class AudioMetaDaoTest {
private AudioMeta audioMeta, audioMeta1, faultyAudioMeta;
private AudioMetaDao audioMetaDao, faultyAudioMetaDao;

public AudioMetaDaoTest() {
    LocalDateTime now = LocalDateTime.now();
    this.audioMeta = AudioMetaBuilder.builder()
            .setDbId("5afe1efdbbeeb20adca58016")
            .setTitle("title")
            .setAuthor("author")
            .setAlbum("album")
            .setReleaseDate(now)
            .setDurationMs(0)
            .setPlayCount(0).build();

    this.audioMeta1 = AudioMetaBuilder.builder()
            .setDbId("5afe1efdbbeeb20adca58017")
            .setTitle("title")
            .setAuthor("author")
            .setAlbum("album")
            .setReleaseDate(now)
            .setDurationMs(0)
            .setPlayCount(0).build();

    this.faultyAudioMeta = AudioMetaBuilder.builder()
            .setDbId("5afe1efdbbeeb20adca58017")
            .setTitle("title")
            .setAuthor("author")
            .setAlbum("album")
            .setReleaseDate(now)
            .setDurationMs(0)
            .setPlayCount(0).build();

    audioMetaDao.create(audioMeta1);
}

@Test
public void testCreate() {
    assertEquals("5afe1efdbbeeb20adca58016", audioMetaDao.create(audioMeta));
}

@Test
public void testUpdate() {
    assertTrue(audioMetaDao.update(audioMeta));
    //assertFalse(faultyAudioMetaDao.update(faultyAudioMeta));
}

@Test
public void testDelete() {
    assertTrue(audioMetaDao.delete(audioMeta1.getDbId()));
}

@Test
public void testExists() {
    assertTrue(audioMetaDao.exists(audioMeta.getDbId()));
}

@Test
public void testGetList() {
    audioMetaDao.getList();
}

@Test(expected = ParameterException.class)
public void testGetAudioMetaException() {
    audioMetaDao.getAudioMeta("none_existing_id");
}

@Test
public void testGetAudioMeta() {
    assertEquals(audioMeta.getDbId(), audioMetaDao.getAudioMeta(audioMeta.getDbId()).getDbId());
    assertEquals(audioMeta.getTitle(), audioMetaDao.getAudioMeta(audioMeta.getDbId()).getTitle());
    assertEquals(audioMeta.getAuthor(), audioMetaDao.getAudioMeta(audioMeta.getDbId()).getAuthor());
    assertEquals(audioMeta.getAlbum(), audioMetaDao.getAudioMeta(audioMeta.getDbId()).getAlbum());
    assertEquals(audioMeta.getReleaseDate(), audioMetaDao.getAudioMeta(audioMeta.getDbId()).getReleaseDate());
    assertEquals(audioMeta.getDurationMs(), audioMetaDao.getAudioMeta(audioMeta.getDbId()).getDurationMs());
    assertEquals(audioMeta.getPlayCount(), audioMetaDao.getAudioMeta(audioMeta.getDbId()).getPlayCount());
}

@Test
public void testGetAveragePlayCount() {
    //assertEquals();
}
}

 */