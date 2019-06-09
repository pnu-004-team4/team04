package com.team04.musiccloud.db.dao;

import static com.mongodb.client.model.Filters.eq;

import com.beust.jcommander.ParameterException;
import com.mongodb.MongoException;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.BsonField;
import com.team04.musiccloud.audio.AudioMeta;
import com.team04.musiccloud.db.converter.AudioMetaConverter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class AudioMetaDao {

  private MongoCollection<Document> mongoCollection;

  public AudioMetaDao(MongoCollection<Document> mongoCollection) {
    this.mongoCollection = mongoCollection;
  }

  static public boolean deleteDataInDatabase(String dbId,
                                             MongoCollection<Document> mongoCollection) {
    Bson filter;
    try {
      filter = new Document("_id", new ObjectId(dbId));
      mongoCollection.deleteOne(filter);
    } catch (MongoException e) {
      return false;
    }

    return true;
  }

  public String create(AudioMeta audioMeta) {
    Document document = AudioMetaConverter.toDocument(audioMeta);
    this.mongoCollection.insertOne(document);

    return document.get("_id").toString();
  }

  public boolean update(AudioMeta audioMeta) {
    Bson filter;
    Document update;
    Bson query;
    try {
      filter = new Document("_id", new ObjectId(audioMeta.getDbId()));
      update = AudioMetaConverter.toDocument(audioMeta);
      update.remove("_id");
      query = new Document("$set", update);
      this.mongoCollection.updateOne(filter, query);
    } catch (MongoException e) {
      return false;
    }

    return true;
  }

  public boolean delete(String dbId) {
    return deleteDataInDatabase(dbId, this.mongoCollection);
  }

  public boolean exists(String dbId) {
    FindIterable<Document> document = this.mongoCollection.find(eq("_id", dbId)).limit(1);

    return document != null;
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

  public AudioMeta getAudioMeta(String dbId) throws ParameterException {
    Document document = this.mongoCollection.find(eq("_id", new ObjectId(dbId))).first();

    if (document == null) {
      throw new ParameterException("Incorrect dbId ==>" + dbId);
    }

    return AudioMetaConverter.toAudioMeta(document);
  }

  public int getAveragePlayCount() {
    BsonString playCountString = new BsonString("$count");
    BsonDocument averageDocument = new BsonDocument("$avg", playCountString);
    BsonField averageField = new BsonField("averagePlayCount", averageDocument);
    List<Bson> averageCollection = Collections.singletonList(Aggregates.group("_id", averageField));

    AggregateIterable<Document> aggregateIterable = this.mongoCollection
            .aggregate(averageCollection);
    Document averageResult = aggregateIterable.first();

    if (averageResult != null) {
      return averageResult.getDouble("averagePlayCount").intValue();
    } else {
      return 0;
    }
  }
}