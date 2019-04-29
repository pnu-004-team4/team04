package com.team04.musiccloud.db.converter;

import com.team04.musiccloud.audio.FileMeta;
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
        FileMeta fileMeta = new FileMeta();
        fileMeta.setDbId(document.get("_id").toString());
        fileMeta.setDirectory((String) document.get("directory"));
        fileMeta.setName((String) document.get("name"));
        fileMeta.setExtension((String) document.get("extension"));
        fileMeta.setUser((String) document.get("user"));

        return fileMeta;
    }
}
