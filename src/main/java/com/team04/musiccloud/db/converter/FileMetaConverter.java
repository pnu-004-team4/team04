package com.team04.musiccloud.db.converter;

import com.team04.musiccloud.audio.FileMeta;
import com.team04.musiccloud.audio.FileMetaBuilder;
import com.team04.musiccloud.utilities.StaticPaths;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.nio.file.Path;

public class FileMetaConverter {

    public static Document toDocument(FileMeta fileMeta) {
        return new Document("_id", fileMeta.getDbId())
                .append("name", fileMeta.getName())
                .append("extension", fileMeta.getExtension())
                .append("user", fileMeta.getUser());
    }

    public static Document toDocument(FileMeta fileMeta, String dbId) {
        return new Document("_id", new ObjectId(dbId))
                .append("name", fileMeta.getName())
                .append("extension", fileMeta.getExtension())
                .append("user", fileMeta.getUser());
    }

    public static FileMeta toFileMeta(Document document) {
        String userName = document.get("user").toString();
        Path filePath = StaticPaths.storage.resolve(userName).toAbsolutePath();

        return FileMetaBuilder.builder()
                .setDbId(document.get("_id").toString())
                .setName((String) document.get("name"))
                .setExtension((String) document.get("extension"))
                .setDirectory(filePath.toString())
                .setUser(userName).build();
    }
}
