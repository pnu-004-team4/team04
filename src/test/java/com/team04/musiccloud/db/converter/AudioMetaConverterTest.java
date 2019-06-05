package com.team04.musiccloud.db.converter;

import com.team04.musiccloud.audio.AudioMeta;
import com.team04.musiccloud.audio.AudioMetaBuilder;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static com.team04.musiccloud.db.converter.AudioMetaConverter.toAudioMeta;
import static com.team04.musiccloud.db.converter.AudioMetaConverter.toDocument;
import static org.junit.Assert.*;

public class AudioMetaConverterTest {
    private AudioMeta audioMeta, audioMetaWithNoId;
    private Document document;

    @Before
    public void setUp() {
        LocalDateTime now = LocalDateTime.of(1, 1, 1, 0, 0);
        this.audioMeta = AudioMetaBuilder.builder()
                .setDbId("test")
                .setTitle("title")
                .setAuthor("author")
                .setAlbum("album")
                .setReleaseDate(now)
                .setDurationMs(0)
                .setPlayCount(0).build();

        this.audioMetaWithNoId = AudioMetaBuilder.builder()
                .setTitle("title")
                .setAuthor("author")
                .setAlbum("album")
                .setReleaseDate(now)
                .setDurationMs(0)
                .setPlayCount(0).build();

        this.document = new Document()
                .append("title", "title")
                .append("author", "author")
                .append("album", "album")
                .append("releaseDate", now.toString())
                .append("durationMs", "0")
                .append("count", "0")
                .append("_id", "test");
    }

    @Test
    public void testToDocument() {
        assertEquals(document.toString(), toDocument(audioMeta).toString());

        Document result = toDocument(audioMetaWithNoId);
        document.append("_id", result.get("_id"));
        assertEquals(document.toString(), result.toString());
    }

    /*
    @Test(expected = DateTimeException.class)
    public void testToAudioMetaException() {
        document.append("releaseDate", "heyheyhey");
        toAudioMeta(document);
    }
     */

    @Test
    public void testToAudioMeta() {
        assertEquals(audioMeta.getDbId(), toAudioMeta(document).getDbId());
        assertEquals(audioMeta.getTitle(), toAudioMeta(document).getTitle());
        assertEquals(audioMeta.getAlbum(), toAudioMeta(document).getAlbum());
        assertEquals(audioMeta.getReleaseDate(), toAudioMeta(document).getReleaseDate());
        assertEquals(audioMeta.getDurationMs(), toAudioMeta(document).getDurationMs());
        assertEquals(audioMeta.getPlayCount(), toAudioMeta(document).getPlayCount());
    }
}