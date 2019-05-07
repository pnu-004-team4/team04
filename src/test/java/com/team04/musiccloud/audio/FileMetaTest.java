package com.team04.musiccloud.audio;

import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FileMetaTest {
    private final String id = "id";
    private final String dir = "dir";
    private final String name = "name";
    private final String ext = "ext";
    private final String usr = "usr";
    
    @Test
    public void objectConstructorTest() {
        FileMeta fileMeta1 = new FileMeta("", "", "", "");
        assertNotNull(fileMeta1);
        
        FileMeta fileMeta2 = new FileMeta("", "", "", "", "");
        assertNotNull(fileMeta2);
        
        FileMeta fileMeta3 = new FileMeta(fileMeta1);
        assertNotNull(fileMeta3);
    }
    
    @Test
    public void objectEqualityTest() {
        FileMeta fileMeta1 = new FileMeta(id, dir, name, ext, usr);
        FileMeta fileMeta2 = new FileMeta(id, dir, name, ext, usr);
        
        assertEquals(fileMeta1, fileMeta2);
        assertEquals(fileMeta1.hashCode(), fileMeta2.hashCode());
    }
    
    @Test
    public void getterTest() {
        FileMeta fileMeta = new FileMeta(id, dir, name, ext, usr);
        
        assertEquals(id, fileMeta.getDbId());
        assertEquals(dir, fileMeta.getDirectory());
        assertEquals(name, fileMeta.getName());
        assertEquals(ext, fileMeta.getExtension());
        assertEquals(usr, fileMeta.getUser());
    }
    
    @Test
    public void additionalGetterTest() {
        FileMeta fileMeta = new FileMeta(id, dir, name, ext, usr);
        
        final Path expectedFullPath = Paths.get(dir, name + '.' + ext).toAbsolutePath();
        assertEquals(expectedFullPath, fileMeta.getFullPath());
        assertEquals(expectedFullPath.toFile(), fileMeta.getFullPathAsFile());
        
        final Path expectedNameExtension = Paths.get(name + '.' + ext);
        assertEquals(expectedNameExtension, fileMeta.getNameExtension());
    }
}