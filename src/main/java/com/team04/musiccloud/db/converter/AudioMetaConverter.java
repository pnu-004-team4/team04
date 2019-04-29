package com.team04.musiccloud.db.converter;

import com.team04.musiccloud.audio.AudioMeta;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AudioMetaConverter {

    public static Document toDocument(AudioMeta audioMeta) {
        Document document = new Document("title", audioMeta.getTitle())
                .append("author", audioMeta.getAuthor())
                .append("album", audioMeta.getAlbum())
                .append("releaseDate", audioMeta.getReleaseDate().toString());
        if ( audioMeta.getDbId() == null ) {
            document.append("_id", new ObjectId());
        } else {
            document.append("_id", audioMeta.getDbId());
        }

        return document;
    }

    public static AudioMeta toAudioMeta(Document document) {
        AudioMeta audioMeta = new AudioMeta();
        audioMeta.setDbId(document.get("_id").toString());
        audioMeta.setTitle((String) document.get("title"));
        audioMeta.setAuthor((String) document.get("author"));
        audioMeta.setAlbum((String) document.get("album"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        LocalDateTime dateTime = LocalDateTime.parse(document.get("releaseDate").toString(), formatter);
        audioMeta.setReleaseDate(dateTime);

        return audioMeta;
    }
}
