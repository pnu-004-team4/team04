package com.team04.musiccloud.db.dao;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.team04.musiccloud.auth.Account;
import com.team04.musiccloud.db.converter.AccountConverter;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class AccountDao {

    private MongoCollection<Document> mongoCollection;

    public AccountDao() {
        MongoClientURI mongoClientURI = new MongoClientURI(
                "mongodb+srv://deeopark:wFdAKPThZgZb3pTv@cluster0-p10yd.mongodb.net/test?retryWrites=true");
        MongoClient mongoClient = new MongoClient(mongoClientURI);
        this.mongoCollection = mongoClient.getDatabase("MusicCloud")
                .getCollection("account");
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
        FindIterable<Document> document = this.mongoCollection.find(eq("_id", email)).limit(1);
        boolean exists = document != null;

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
