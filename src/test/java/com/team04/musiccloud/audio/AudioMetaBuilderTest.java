package com.team04.musiccloud.audio;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AudioMetaBuilderTest {
    @Test
    public void builderConstructorTest() {
        AudioMetaBuilder audioMetaBuilder = AudioMetaBuilder.builder();
        
        assertNotNull(audioMetaBuilder);
    }
    
    @Test
    public void builderContentsTest() {
        final String dbId = "123";
        final String tit = "tit";
        final String auth = "auth";
        final String alb = "alb";
        final LocalDateTime date = LocalDateTime.of(2019, 5, 7, 1, 0);
        
        AudioMeta fileMeta = AudioMetaBuilder.builder()
                .setDbId(dbId)
                .setTitle(tit)
                .setAuthor(auth)
                .setAlbum(alb)
                .setReleaseDate(date)
                .build();
        
        assertEquals(dbId, fileMeta.getDbId());
        assertEquals(tit, fileMeta.getTitle());
        assertEquals(auth, fileMeta.getAuthor());
        assertEquals(alb, fileMeta.getAlbum());
        assertEquals(date, fileMeta.getReleaseDate());
    }
}