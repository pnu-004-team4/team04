package com.team04.musiccloud.db.dao;

import static com.mongodb.client.model.Filters.eq;

import com.beust.jcommander.ParameterException;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.team04.musiccloud.audio.FileMeta;
import com.team04.musiccloud.db.converter.FileMetaConverter;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

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
    Bson filter;
    Document update;
    Bson query;
    try {
      filter = new Document("_id", new ObjectId(fileMeta.getDbId()));
      update = FileMetaConverter.toDocument(fileMeta);
      update.remove("_id");
      query = new Document("$set", update);
      this.mongoCollection.updateOne(filter, query);
    } catch (MongoException e) {
      return false;
    }

    return true;
  }

  public boolean delete(String dbId) {
    return AudioMetaDao.deleteDataInDatabase(dbId, this.mongoCollection);
  }

  public boolean exists(String dbId) {
    FindIterable<Document> document = this.mongoCollection.find(eq("_id", dbId)).limit(1);

    return document != null;
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

  public FileMeta getFileMeta(String dbId) throws ParameterException {
    Document document = this.mongoCollection.find(eq("_id", new ObjectId(dbId))).first();

    if (document == null) {
      throw new ParameterException("Incorrect dbId ==>" + dbId);
    }

    return FileMetaConverter.toFileMeta(document);
  }
}