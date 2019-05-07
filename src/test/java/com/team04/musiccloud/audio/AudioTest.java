package com.team04.musiccloud.audio;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AudioTest {
    private final String id = "id";
    private final String tit = "tit";
    private final String auth = "auth";
    private final String alb = "alb";
    private final LocalDateTime date = LocalDateTime.of(2019, 5, 7, 1, 0);
    private final String dir = "dir";
    private final String name = "name";
    private final String ext = "ext";
    private final String usr = "usr";
    
    private AudioMeta audioMeta = new AudioMeta(id, tit, auth, alb, date);
    private FileMeta fileMeta = new FileMeta(id, dir, name, ext, usr);
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