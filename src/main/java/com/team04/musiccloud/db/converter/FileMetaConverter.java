package com.team04.musiccloud.db.converter;

import com.team04.musiccloud.audio.FileMeta;
import com.team04.musiccloud.audio.FileMetaBuilder;
import org.bson.Document;
import org.bson.types.ObjectId;

public class FileMetaConverter {

    public static Document toDocument(FileMeta fileMeta) {
        Document document = new Document("_id", fileMeta.getDbId())
                .append("directory", fileMeta.getDirectory())
                .append("name", fileMeta.getName())
                .append("extension", fileMeta.getExtension())
                .append("user", fileMeta.getUser());

        return document;
    }

    public static Document toDocument(FileMeta fileMeta, String dbId) {
        Document document = new Document("_id", new ObjectId(dbId))
                .append("directory", fileMeta.getDirectory())
                .append("name", fileMeta.getName())
                .append("extension", fileMeta.getExtension())
                .append("user", fileMeta.getUser());

        return document;
    }

    public static FileMeta toFileMeta(Document document) {
        FileMeta fileMeta = FileMetaBuilder.builder()
                .setDbId(document.get("_id").toString())
                .setDirectory((String) document.get("directory"))
                .setName((String) document.get("name"))
                .setExtension((String) document.get("extension"))
                .setUser((String) document.get("user")).build();

        return fileMeta;
    }
}
