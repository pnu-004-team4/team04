/*
package com.team04.musiccloud.db.dao;

import com.beust.jcommander.ParameterException;
import com.team04.musiccloud.audio.FileMeta;
import com.team04.musiccloud.audio.FileMetaBuilder;
import org.junit.Test;

import static org.junit.Assert.*;

public class FileMetaDaoTest {
    /*
    private FileMeta fileMeta, fileMeta1, faultyFileMeta;
    private FileMetaDao fileMetaDao, faultyFileMetaDao;

    public FileMetaDaoTest() {
        this.fileMeta = FileMetaBuilder.builder()
                .setName("name")
                .setExtension("mp3")
                .setUser("test").build();

        this.fileMeta1 = FileMetaBuilder.builder()
                .setName("name")
                .setExtension("mp3")
                .setUser("test").build();

        fileMetaDao.create(fileMeta1, "5afe1efdbbeeb20adca58017");
    }

    @Test
    public void testCreate() {
        assertTrue(fileMetaDao.create(fileMeta, "5afe1efdbbeeb20adca58016"));
    }

    @Test
    public void testUpdate() {
        assertTrue(fileMetaDao.update(fileMeta));
        //assertFalse(fileMetaDao.update(faultyFileMeta));
    }

    @Test
    public void testDelete() {
        assertTrue(fileMetaDao.delete("5afe1efdbbeeb20adca58017"));
    }

    @Test
    public void testExists() {
        assertTrue(fileMetaDao.exists("5afe1efdbbeeb20adca58016"));
    }

    @Test
    public void testGetList() {
        //fileMetaDao.getList();
    }

    @Test(expected = ParameterException.class)
    public void testGetFileMetaException() {
        fileMetaDao.getFileMeta("non_existing_id");
    }

    @Test
    public void testGetFileMeta() {
        assertEquals(fileMeta.getDbId(), fileMetaDao.getFileMeta("5afe1efdbbeeb20adca58016").getDbId());
        assertEquals(fileMeta.getName(), fileMetaDao.getFileMeta("5afe1efdbbeeb20adca58016").getName());
        assertEquals(fileMeta.getExtension(), fileMetaDao.getFileMeta("5afe1efdbbeeb20adca58016").getExtension());
        assertEquals(fileMeta.getUser(), fileMetaDao.getFileMeta("5afe1efdbbeeb20adca58016").getUser());
    }
}

 */