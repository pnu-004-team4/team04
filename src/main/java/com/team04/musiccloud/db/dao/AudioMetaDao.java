package com.team04.musiccloud.db.dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.team04.musiccloud.audio.AudioMeta;
import com.team04.musiccloud.db.converter.AudioMetaConverter;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class AudioMetaDao {

    private MongoCollection<Document> mongoCollection;

    public AudioMetaDao(MongoCollection<Document> mongoCollection) {
        this.mongoCollection = mongoCollection;
    }

    public String create(AudioMeta audioMeta) {
        Document document = AudioMetaConverter.toDocument(audioMeta);
        this.mongoCollection.insertOne(document);

        return document.get("_id").toString();
    }

    public boolean update(AudioMeta audioMeta) {
        this.mongoCollection.updateOne(eq("_id", audioMeta.getDbId()),
                new Document("$set", AudioMetaConverter.toDocument(audioMeta)));

        return true;
    }

    public boolean delete(String dbId) {
        this.mongoCollection.deleteOne(eq("_id", dbId));

        return true;
    }

    public boolean exists(String dbId) {
        FindIterable<Document> document = this.mongoCollection.find(eq("_id", dbId)).limit(1);
        boolean exists = document != null;

        return exists;
    }

    public List<AudioMeta> getList() {
        List<AudioMeta> audioMetaList = new ArrayList<AudioMeta>();
        MongoCursor<Document> mongoCursor = this.mongoCollection.find().iterator();

        try {
            while (mongoCursor.hasNext()) {
                Document document = mongoCursor.next();
                AudioMeta audioMeta = AudioMetaConverter.toAudioMeta(document);
                audioMetaList.add(audioMeta);
            }
        } finally {
            mongoCursor.close();
        }

        return audioMetaList;
    }

    public AudioMeta getAudioMeta(ObjectId dbId) {
        Document document = this.mongoCollection.find(eq("_id", dbId)).first();

        return AudioMetaConverter.toAudioMeta(document);
    }
}
