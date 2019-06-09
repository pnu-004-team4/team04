package com.team04.musiccloud.db.dao;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.team04.musiccloud.audio.AudioMeta;
import com.team04.musiccloud.audio.AudioMetaBuilder;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class AudioMetaDaoTest {
    private AudioMeta audioMeta;
    private AudioMetaDao audioMetaDao;

    @Before
    public void setUp() {
        MongoClientURI mongoClientURI = new MongoClientURI("mongodb://test:test@35.200.2.141:27017/test");
        MongoClient mongoClient = new MongoClient(mongoClientURI);
        MongoCollection<Document> mongoCollection = mongoClient.getDatabase("test")
                .getCollection("audiometa.test@test.com");
        this.audioMetaDao = new AudioMetaDao(mongoCollection);

        LocalDateTime now = LocalDateTime.now();
        this.audioMeta = AudioMetaBuilder.builder()
                .setDbId("5afe1efdbbeeb20adca58016")
                .setTitle("title")
                .setAuthor("author")
                .setAlbum("album")
                .setReleaseDate(now)
                .setDurationMs(0)
                .setPlayCount(0).build();
    }

    @Test
    public void testCreate() {
        if (audioMetaDao.exists(audioMeta.getDbId())) {
            audioMetaDao.delete(audioMeta.getDbId());
        }
        assertEquals(audioMeta.getDbId(), audioMetaDao.create(audioMeta));
    }

    @Test
    public void testUpdate() {
        if (audioMetaDao.exists(audioMeta.getDbId())) {
            audioMetaDao.delete(audioMeta.getDbId());
        }
        audioMetaDao.create(audioMeta);
        assertTrue(audioMetaDao.update(audioMeta));
    }

    @Test
    public void testDelete() {
        if (audioMetaDao.exists(audioMeta.getDbId())) {
            assertTrue(audioMetaDao.delete(audioMeta.getDbId()));
        } else {
            audioMetaDao.create(audioMeta);
            assertTrue(audioMetaDao.delete(audioMeta.getDbId()));
        }
    }

    @Test
    public void testExists() {
        if (audioMetaDao.exists(audioMeta.getDbId())) {
            assertTrue(audioMetaDao.exists(audioMeta.getDbId()));
        } else {
            audioMetaDao.create(audioMeta);
            assertTrue(audioMetaDao.exists(audioMeta.getDbId()));
        }
    }

    @Test
    public void testGetList() {
        assertNotNull(audioMetaDao.getList());
    }

    @Test
    public void testGetAudioMeta() {
        if (audioMetaDao.exists(audioMeta.getDbId())) {
            audioMetaDao.delete(audioMeta.getDbId());
        }
        audioMetaDao.create(audioMeta);
        assertEquals(audioMeta.getDbId(), audioMetaDao.getAudioMeta(audioMeta.getDbId()).getDbId());
        assertEquals(audioMeta.getTitle(), audioMetaDao.getAudioMeta(audioMeta.getDbId()).getTitle());
        assertEquals(audioMeta.getAuthor(), audioMetaDao.getAudioMeta(audioMeta.getDbId()).getAuthor());
        assertEquals(audioMeta.getAlbum(), audioMetaDao.getAudioMeta(audioMeta.getDbId()).getAlbum());
        assertEquals(audioMeta.getReleaseDate(), audioMetaDao.getAudioMeta(audioMeta.getDbId()).getReleaseDate());
        assertEquals(audioMeta.getDurationMs(), audioMetaDao.getAudioMeta(audioMeta.getDbId()).getDurationMs());
        assertEquals(audioMeta.getPlayCount(), audioMetaDao.getAudioMeta(audioMeta.getDbId()).getPlayCount());
    }
}