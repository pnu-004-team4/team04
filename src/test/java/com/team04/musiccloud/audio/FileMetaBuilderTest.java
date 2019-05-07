package com.team04.musiccloud.audio;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FileMetaBuilderTest {
    @Test
    public void builderConstructorTest() {
        FileMetaBuilder fileMetaBuilder = FileMetaBuilder.builder();
        
        assertNotNull(fileMetaBuilder);
    }
    
    @Test
    public void builderContentsTest() {
        final String dbId = "123";
        final String dir = "dir";
        final String name = "name";
        final String ext = "ext";
        final String usr = "usr";
        
        FileMeta fileMeta = FileMetaBuilder.builder()
                .setDbId(dbId)
                .setDirectory(dir)
                .setName(name)
                .setExtension(ext)
                .setUser(usr)
                .build();
        
        assertEquals(dbId, fileMeta.getDbId());
        assertEquals(dir, fileMeta.getDirectory());
        assertEquals(name, fileMeta.getName());
        assertEquals(ext, fileMeta.getExtension());
        assertEquals(usr, fileMeta.getUser());
    }
}