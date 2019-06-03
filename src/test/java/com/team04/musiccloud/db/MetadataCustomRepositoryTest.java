/*
package com.team04.musiccloud.db;

import com.team04.musiccloud.audio.AudioMeta;
import com.team04.musiccloud.audio.AudioMetaBuilder;
import com.team04.musiccloud.audio.FileMeta;
import com.team04.musiccloud.audio.FileMetaBuilder;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class MetadataCustomRepositoryTest {
    MetadataCustomRepository metadataCustomRepository;
    private AudioMeta audioMeta, audioMeta1;
    private FileMeta fileMeta, fileMeta1;

    public MetadataCustomRepositoryTest() {
        LocalDateTime now = LocalDateTime.now();
        this.audioMeta = AudioMetaBuilder.builder()
                .setDbId("5afe1efdbbeeb20adca58016")
                .setTitle("title")
                .setAuthor("author")
                .setAlbum("album")
                .setReleaseDate(now)
                .setDurationMs(0)
                .setPlayCount(0).build();

        this.fileMeta = FileMetaBuilder.builder()
                .setName("name")
                .setExtension("mp3")
                .setUser("test").build();

        this.audioMeta1 = AudioMetaBuilder.builder()
                .setDbId("5afe1efdbbeeb20adca58017")
                .setTitle("title")
                .setAuthor("author")
                .setAlbum("album")
                .setReleaseDate(now)
                .setDurationMs(0)
                .setPlayCount(0).build();

        this.fileMeta1 = FileMetaBuilder.builder()
                .setName("name")
                .setExtension("mp3")
                .setUser("test").build();

        metadataCustomRepository.storeMetadata(audioMeta1, fileMeta1);
    }

    @Test
    public void testGetInstance() {
    }

    @Test
    public void testStoreMetadata() {
        assertTrue(metadataCustomRepository.storeMetadata(audioMeta, fileMeta));
    }

    @Test
    public void testUpdateMetadata() {
        assertTrue(metadataCustomRepository.updateMetadata(audioMeta, fileMeta));
    }

    @Test
    public void testDeleteMetadata() {
        metadataCustomRepository.deleteMetadata("5afe1efdbbeeb20adca58017");
    }

    @Test
    public void testGetAudioMeta() {
        assertEquals(audioMeta.getDbId(), metadataCustomRepository.getAudioMeta(audioMeta.getDbId()).getDbId());
        assertEquals(audioMeta.getTitle(), metadataCustomRepository.getAudioMeta(audioMeta.getDbId()).getTitle());
        assertEquals(audioMeta.getAuthor(), metadataCustomRepository.getAudioMeta(audioMeta.getDbId()).getAuthor());
        assertEquals(audioMeta.getAlbum(), metadataCustomRepository.getAudioMeta(audioMeta.getDbId()).getAlbum());
        assertEquals(audioMeta.getReleaseDate(), metadataCustomRepository.getAudioMeta(audioMeta.getDbId()).getReleaseDate());
        assertEquals(audioMeta.getDurationMs(), metadataCustomRepository.getAudioMeta(audioMeta.getDbId()).getDurationMs());
        assertEquals(audioMeta.getPlayCount(), metadataCustomRepository.getAudioMeta(audioMeta.getDbId()).getPlayCount());
    }

    @Test
    public void testGetFileMeta() {
        assertEquals(fileMeta.getDbId(), metadataCustomRepository.getFileMeta("5afe1efdbbeeb20adca58016").getDbId());
        assertEquals(fileMeta.getName(), metadataCustomRepository.getFileMeta("5afe1efdbbeeb20adca58016").getName());
        assertEquals(fileMeta.getExtension(), metadataCustomRepository.getFileMeta("5afe1efdbbeeb20adca58016").getExtension());
        assertEquals(fileMeta.getUser(), metadataCustomRepository.getFileMeta("5afe1efdbbeeb20adca58016").getUser());
    }

    @Test
    public void testGetPlaylist() {
        //metadataCustomRepository.getPlaylist();
    }

    @Test
    public void testGetAveragePlayCount() {
        //assertEquals(0, metadataCustomRepository.getAveragePlayCount());
    }

}

 */