package com.team04.musiccloud.db;

import com.team04.musiccloud.audio.AudioMeta;
import com.team04.musiccloud.audio.AudioMetaBuilder;
import com.team04.musiccloud.audio.FileMeta;
import com.team04.musiccloud.audio.FileMetaBuilder;
import com.team04.musiccloud.utilities.StaticKeys;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class MetadataCustomRepositoryTest {
    MetadataCustomRepository metadataCustomRepository;
    private AudioMeta audioMeta;
    private FileMeta fileMeta;

    public MetadataCustomRepositoryTest() {
        StaticKeys.setKeys("mongodb://test:test@35.200.2.141/test");
        StaticKeys.setDbName("test");

        metadataCustomRepository = MetadataCustomRepository.getInstance("test@test.com");

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
                .setDbId("5afe1efdbbeeb20adca58016")
                .setName("name")
                .setExtension("mp3")
                .setUser("test").build();
    }

    @Test
    public void testGetInstance() {
        assertEquals(metadataCustomRepository, MetadataCustomRepository.getInstance("test@test.com"));
    }

    @Test
    public void testStoreMetadata() {
        if (metadataCustomRepository.getAudioMeta(audioMeta.getDbId()) != null
                && metadataCustomRepository.getFileMeta(fileMeta.getDbId()) != null) {
            metadataCustomRepository.deleteMetadata(audioMeta.getDbId());
        }
        metadataCustomRepository.storeMetadata(audioMeta, fileMeta);
    }

    @Test
    public void testUpdateMetadata() {
        metadataCustomRepository.updateMetadata(audioMeta, fileMeta);
    }

    @Test
    public void testDeleteMetadata() {
        metadataCustomRepository.deleteMetadata("5afe1efdbbeeb20adca58017");
    }

    @Test
    public void testGetAudioMeta() {
        metadataCustomRepository.getAudioMeta(audioMeta.getDbId());
    }

    @Test
    public void testGetFileMeta() {
        metadataCustomRepository.getFileMeta(fileMeta.getDbId());
    }

    @Test
    public void testGetPlaylist() {
        metadataCustomRepository.getPlaylist();
    }
}