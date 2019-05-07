package com.team04.musiccloud.audio;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AudioMetaTest {
    private final String id = "id";
    private final String tit = "tit";
    private final String auth = "auth";
    private final String alb = "alb";
    private final LocalDateTime date = LocalDateTime.of(2019, 5, 7, 1, 0);
    
    @Test
    public void objectConstructorTest() {
        final LocalDateTime date = LocalDateTime.of(2019, 5, 7, 1, 0);
        
        AudioMeta audioMeta1 = new AudioMeta("", "", "", date);
        assertNotNull(audioMeta1);
        
        AudioMeta audioMeta2 = new AudioMeta("", "", "", "", date);
        assertNotNull(audioMeta2);
        
        AudioMeta audioMeta3 = new AudioMeta(audioMeta1);
        assertNotNull(audioMeta3);
    }
    
    @Test
    public void objectEqualityTest() {
        AudioMeta audioMeta1 = new AudioMeta(id, tit, auth, alb, date);
        AudioMeta audioMeta2 = new AudioMeta(id, tit, auth, alb, date);
        
        assertEquals(audioMeta1, audioMeta2);
        assertEquals(audioMeta1.hashCode(), audioMeta2.hashCode());
    }
    
    @Test
    public void getterTest() {
        AudioMeta audioMeta = new AudioMeta(id, tit, auth, alb, date);
        
        assertEquals(id, audioMeta.getDbId());
        assertEquals(tit, audioMeta.getTitle());
        assertEquals(auth, audioMeta.getAuthor());
        assertEquals(alb, audioMeta.getAlbum());
        assertEquals(date, audioMeta.getReleaseDate());
    }
}