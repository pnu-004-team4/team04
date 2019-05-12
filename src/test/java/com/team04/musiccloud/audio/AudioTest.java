package com.team04.musiccloud.audio;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AudioTest {
    private final static String ID = "id";
    private final static String TITLE = "tit";
    private final static String AUTHOR = "auth";
    private final static String ALBUM = "alb";
    private final static LocalDateTime DATE_TIME = LocalDateTime.of(2019, 5, 7, 1, 0);
    private final static String DIRECTORY = "dir";
    private final static String NAME = "name";
    private final static String EXTENSION = "ext";
    private final static String USER = "usr";
    
    private AudioMeta audioMeta = new AudioMeta(ID, TITLE, AUTHOR, ALBUM, DATE_TIME);
    private FileMeta fileMeta = new FileMeta(ID, DIRECTORY, NAME, EXTENSION, USER);
    private byte[] byteData = new byte[]{1, 0, 0, 0, 1, 1};
    
    @Test
    public void objectConstructorTest() {
        Audio audio = new Audio(audioMeta, fileMeta, byteData);
        
        assertNotNull(audio);
    }
    
    @Test
    public void objectEqualityTest() {
        Audio audio1 = new Audio(audioMeta, fileMeta, byteData);
        Audio audio2 = new Audio(audioMeta, fileMeta, byteData);
        
        assertEquals(audio1, audio2);
        assertEquals(audio1.hashCode(), audio2.hashCode());
    }
    
    @Test
    public void getterTest() {
        Audio audio = new Audio(audioMeta, fileMeta, byteData);
        
        assertEquals(audioMeta, audio.getAudioMeta());
        assertEquals(fileMeta, audio.getFileMeta());
        assertEquals(byteData.length, audio.getBytes().length);
        
        for ( int i = 0; i < byteData.length; i++ ) {
            assertEquals(byteData[i], audio.getBytes()[i]);
        }
    }
}