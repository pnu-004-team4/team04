package com.team04.musiccloud.db.converter;

import com.team04.musiccloud.audio.FileMeta;
import com.team04.musiccloud.audio.FileMetaBuilder;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

import static com.team04.musiccloud.db.converter.FileMetaConverter.toDocument;
import static com.team04.musiccloud.db.converter.FileMetaConverter.toFileMeta;
import static org.junit.Assert.*;

public class FileMetaConverterTest {
    private FileMeta fileMeta;
    private Document document;

    @Before
    public void setUp() {
        this.fileMeta = FileMetaBuilder.builder()
                .setDbId("5afe1efdbbeeb20adca58016")
                .setName("name")
                .setExtension("mp3")
                .setUser("test").build();

        this.document = new Document()
                .append("_id", "5afe1efdbbeeb20adca58016")
                .append("name", "name")
                .append("extension", "mp3")
                .append("user", "test");
    }

    @Test
    public void testToDocument() {
        assertEquals(document.toString(), toDocument(fileMeta).toString());
    }

    @Test
    public void testToDocument1() {
        assertEquals(document.toString(), toDocument(fileMeta, "5afe1efdbbeeb20adca58016").toString());
    }

    @Test
    public void testToFileMeta() {
        assertEquals(fileMeta.getDbId(), toFileMeta(document).getDbId());
        assertEquals(fileMeta.getName(), toFileMeta(document).getName());
        assertEquals(fileMeta.getExtension(), toFileMeta(document).getExtension());
        assertEquals(fileMeta.getUser(), toFileMeta(document).getUser());
    }
}