package com.team04.musiccloud.db.dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.team04.musiccloud.audio.FileMeta;
import com.team04.musiccloud.db.converter.FileMetaConverter;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class FileMetaDao {

    private MongoCollection<Document> mongoCollection;

    public FileMetaDao(MongoCollection<Document> mongoCollection) {
        this.mongoCollection = mongoCollection;
    }

    public boolean create(FileMeta fileMeta, String dbId) {
        Document document = FileMetaConverter.toDocument(fileMeta, dbId);
        this.mongoCollection.insertOne(document);

        return true;
    }

    public boolean update(FileMeta fileMeta) {
        this.mongoCollection.updateOne(eq("_id", fileMeta.getDbId()),
                new Document("$set", FileMetaConverter.toDocument(fileMeta)));

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

    public List<FileMeta> getList() {
        List<FileMeta> fileMetaList = new ArrayList<FileMeta>();
        MongoCursor<Document> mongoCursor = this.mongoCollection.find().iterator();

        try {
            while (mongoCursor.hasNext()) {
                Document document = mongoCursor.next();
                FileMeta fileMeta = FileMetaConverter.toFileMeta(document);
                fileMetaList.add(fileMeta);
            }
        } finally {
            mongoCursor.close();
        }

        return fileMetaList;
    }

    public FileMeta getFileMeta(ObjectId dbId) {
        Document document = this.mongoCollection.find(eq("_id", dbId)).first();

        return FileMetaConverter.toFileMeta(document);
    }
}
