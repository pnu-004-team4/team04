package com.team04.musiccloud.audio;

import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FileMetaTest {
    private final static String ID = "id";
    private final static String DIRECTORY = "dir";
    private final static String NAME = "name";
    private final static String EXTENSION = "ext";
    private final static String USER = "usr";
    
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
        FileMeta fileMeta1 = new FileMeta(ID, DIRECTORY, NAME, EXTENSION, USER);
        FileMeta fileMeta2 = new FileMeta(ID, DIRECTORY, NAME, EXTENSION, USER);
        
        assertEquals(fileMeta1, fileMeta2);
        assertEquals(fileMeta1.hashCode(), fileMeta2.hashCode());
    }
    
    @Test
    public void getterTest() {
        FileMeta fileMeta = new FileMeta(ID, DIRECTORY, NAME, EXTENSION, USER);
    
        assertEquals(ID, fileMeta.getDbId());
        assertEquals(DIRECTORY, fileMeta.getDirectory());
        assertEquals(NAME, fileMeta.getName());
        assertEquals(EXTENSION, fileMeta.getExtension());
        assertEquals(USER, fileMeta.getUser());
    }
    
    @Test
    public void additionalGetterTest() {
        FileMeta fileMeta = new FileMeta(ID, DIRECTORY, NAME, EXTENSION, USER);
    
        final Path expectedFullPath = Paths.get(DIRECTORY, NAME + '.' + EXTENSION).toAbsolutePath();
        assertEquals(expectedFullPath, fileMeta.getFullPath());
        assertEquals(expectedFullPath.toFile(), fileMeta.getFullPathAsFile());
    
        final Path expectedNameExtension = Paths.get(NAME + '.' + EXTENSION);
        assertEquals(expectedNameExtension, fileMeta.getNameExtension());
    }
}