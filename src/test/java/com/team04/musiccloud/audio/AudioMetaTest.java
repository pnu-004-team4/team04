package com.team04.musiccloud.audio;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class AudioMetaTest {
    private final static String ID = "id";
    private final static String TITLE = "tit";
    private final static String AUTHOR = "auth";
    private final static String ALBUM = "alb";
    private final static LocalDateTime DATE_TIME = LocalDateTime.of(2019, 5, 7, 1, 0);
    
    @Test
    public void constructorTest() {
        AudioMeta audioMeta1 = new AudioMeta("", "", "", DATE_TIME);
        assertNotNull(audioMeta1);
        
        AudioMeta audioMeta2 = new AudioMeta("", "", "", "", DATE_TIME);
        assertNotNull(audioMeta2);
        
        AudioMeta audioMeta3 = new AudioMeta(audioMeta1);
        assertNotNull(audioMeta3);
    }
    
    @Test
    public void equalityTest() {
        AudioMeta audioMeta1 = new AudioMeta(ID, TITLE, AUTHOR, ALBUM, DATE_TIME);
        AudioMeta audioMeta2 = new AudioMeta(ID, TITLE, AUTHOR, ALBUM, DATE_TIME);
        FakeAudioMeta fakeAudioMeta = new FakeAudioMeta();
        
        assertEquals(audioMeta1, audioMeta1);
        assertEquals(audioMeta1, audioMeta2);
        assertNotEquals(null, audioMeta1);
        assertNotEquals(fakeAudioMeta, audioMeta1);
        
        assertEquals(audioMeta1.hashCode(), audioMeta2.hashCode());
    }
    
    @Test
    public void hasTest() {
        AudioMeta audioMeta = new AudioMeta(ID, TITLE, AUTHOR, ALBUM, DATE_TIME);
        
        assertTrue(audioMeta.hasDbId());
        assertTrue(audioMeta.hasTitle());
        assertTrue(audioMeta.hasAuthor());
        assertTrue(audioMeta.hasAlbum());
        assertTrue(audioMeta.hasReleaseDate());
        assertFalse(audioMeta.isEmpty());
    }
    
    @Test
    public void getterTest() {
        AudioMeta audioMeta = new AudioMeta(ID, TITLE, AUTHOR, ALBUM, DATE_TIME);
        
        assertEquals(ID, audioMeta.getDbId());
        assertEquals(TITLE, audioMeta.getTitle());
        assertEquals(AUTHOR, audioMeta.getAuthor());
        assertEquals(ALBUM, audioMeta.getAlbum());
        assertEquals(DATE_TIME, audioMeta.getReleaseDate());
    }
    
    private class FakeAudioMeta {
    }
}