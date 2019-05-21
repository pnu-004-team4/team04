package com.team04.musiccloud.db.dao;

import static com.mongodb.client.model.Filters.eq;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.team04.musiccloud.auth.Account;
import com.team04.musiccloud.db.converter.AccountConverter;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

public class AccountDao {

  private MongoCollection<Document> mongoCollection;

  public AccountDao(MongoCollection<Document> mongoCollection) {
    this.mongoCollection = mongoCollection;
  }

  public boolean create(Account account) {
    Document document = AccountConverter.toDocument(account);
    this.mongoCollection.insertOne(document);

    return true;
  }

  public boolean update(Account account) {
    this.mongoCollection.updateOne(eq("_id", account.getEmail()),
        new Document("$set", AccountConverter.toDocument(account)));

    return true;
  }

  public boolean delete(String email) {
    this.mongoCollection.deleteOne(eq("_id", email));

    return true;
  }

  public boolean exists(String email) {
    boolean exists = false;

    FindIterable<Document> document = this.mongoCollection.find(eq("_id", email)).limit(1);
    if (document.iterator().hasNext()) {
      exists = true;
    }

    return exists;
  }

  public List<Account> getList() {
    List<Account> accountList = new ArrayList<Account>();
    MongoCursor<Document> mongoCursor = this.mongoCollection.find().iterator();

    try {
      while (mongoCursor.hasNext()) {
        Document document = mongoCursor.next();
        Account account = AccountConverter.toAccount(document);
        accountList.add(account);
      }
    } finally {
      mongoCursor.close();
    }

    return accountList;
  }

  public Account getAccount(String email) {
    Document document = this.mongoCollection.find(eq("_id", email)).first();

    return AccountConverter.toAccount(document);
  }
}
