package com.team04.musiccloud.db.dao;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.team04.musiccloud.audio.FileMeta;
import com.team04.musiccloud.audio.FileMetaBuilder;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FileMetaDaoTest {
    private FileMeta fileMeta;
    private FileMetaDao fileMetaDao;

    @Before
    public void setUp() {
        MongoClientURI mongoClientURI = new MongoClientURI("mongodb://test:test@35.200.2.141:27017/test");
        MongoClient mongoClient = new MongoClient(mongoClientURI);
        MongoCollection<Document> mongoCollection = mongoClient.getDatabase("test")
                .getCollection("filemeta.test@test.com");
        this.fileMetaDao = new FileMetaDao(mongoCollection);

        this.fileMeta = FileMetaBuilder.builder()
                .setDbId("5afe1efdbbeeb20adca58016")
                .setName("name")
                .setExtension("mp3")
                .setUser("test").build();
    }

    @Test
    public void testCreate() {
        if (fileMetaDao.exists(fileMeta.getDbId())) {
            fileMetaDao.delete(fileMeta.getDbId());
        }
        assertTrue(fileMetaDao.create(fileMeta, fileMeta.getDbId()));
    }

    @Test
    public void testUpdate() {
        if (fileMetaDao.exists(fileMeta.getDbId())) {
            fileMetaDao.delete(fileMeta.getDbId());
        }
        fileMetaDao.create(fileMeta, fileMeta.getDbId());
        assertTrue(fileMetaDao.update(fileMeta));
    }

    @Test
    public void testDelete() {
        if (fileMetaDao.exists(fileMeta.getDbId())) {
            assertTrue(fileMetaDao.delete(fileMeta.getDbId()));
        } else {
            fileMetaDao.create(fileMeta, fileMeta.getDbId());
            assertTrue(fileMetaDao.delete(fileMeta.getDbId()));
        }

    }

    @Test
    public void testExists() {
        if (fileMetaDao.exists(fileMeta.getDbId())) {
            fileMetaDao.delete(fileMeta.getDbId());
        }
        fileMetaDao.create(fileMeta, fileMeta.getDbId());
        assertTrue(fileMetaDao.exists(fileMeta.getDbId()));
    }

    @Test
    public void testGetList() {
        assertNotNull(fileMetaDao.getList());
    }

    @Test
    public void testGetFileMeta() {
        if (fileMetaDao.exists(fileMeta.getDbId())) {
            fileMetaDao.delete(fileMeta.getDbId());
        }
        fileMetaDao.create(fileMeta, fileMeta.getDbId());
        assertEquals(fileMeta.getDbId(), fileMetaDao.getFileMeta(fileMeta.getDbId()).getDbId());
        assertEquals(fileMeta.getName(), fileMetaDao.getFileMeta(fileMeta.getDbId()).getName());
        assertEquals(fileMeta.getExtension(), fileMetaDao.getFileMeta(fileMeta.getDbId()).getExtension());
        assertEquals(fileMeta.getUser(), fileMetaDao.getFileMeta(fileMeta.getDbId()).getUser());
    }
}