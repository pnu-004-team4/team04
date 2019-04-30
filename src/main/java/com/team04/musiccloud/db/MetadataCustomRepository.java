package com.team04.musiccloud.db;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.team04.musiccloud.audio.AudioMeta;
import com.team04.musiccloud.audio.FileMeta;
import com.team04.musiccloud.db.dao.AudioMetaDao;
import com.team04.musiccloud.db.dao.FileMetaDao;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.List;

public class MetadataCustomRepository {

    private AudioMetaDao audioMetaDao;
    private FileMetaDao fileMetaDao;

    public MetadataCustomRepository(String email) {
        MongoClientURI mongoClientURI = new MongoClientURI(
                "mongodb+srv://deeopark:wFdAKPThZgZb3pTv@cluster0-p10yd.mongodb.net/test?retryWrites=true");
        MongoClient mongoClient = new MongoClient(mongoClientURI);
        MongoCollection<Document> audioMetaCollection = mongoClient.getDatabase("MusicCloud")
                .getCollection("audiometa." + email);
        MongoCollection<Document> fileMetaCollection = mongoClient.getDatabase("MusicCloud")
                .getCollection("filemeta." + email);
        this.audioMetaDao = new AudioMetaDao(audioMetaCollection);
        this.fileMetaDao = new FileMetaDao(fileMetaCollection);
    }

    /*
    AudioMeta와 FileMeta를 DB에 저장하는 함수.
    여기서 전달된 AudioMeta와 FileMeta는 동일한 dbId 값을 가지게 됨.
    성공적으로 저장하면 true를, 그렇지 않았다면 false를 반환.
    */
    public boolean storeMetadata(AudioMeta audioMeta, FileMeta fileMeta) {
        String dbId = audioMetaDao.create(audioMeta);
        fileMetaDao.create(fileMeta, dbId);

        return true;
    }

    /*
    특정 AudioMeta를 찾고자 할 때 사용하는 함수.
    여기서 AudioMeta를 찾기 위해선 dbId 값을 전달해야 함.
    찾으면 그에 대응되는 AudioMeta를, 찾지 못한다면 null을 반환.
    */
    public AudioMeta getAudioMeta(ObjectId dbId) {
        return audioMetaDao.getAudioMeta(dbId);
    }

    /*
    특정 FileMeta를 찾고자 할 때 사용하는 함수.
    여기서 FileMeta를 찾기 위해선 dbId 값을 전달해야 함.
    찾으면 그에 대응되는 FileMeta를, 찾지 못한다면 null을 반환.
    */
    public FileMeta getFileMeta(ObjectId dbId) {
        return fileMetaDao.getFileMeta(dbId);
    }

    /*
    특정 FileMeta의 directory를 찾고자 할 때 사용하는 함수.
    여기서 FileMeta를 찾기 위해선 dbId 값을 전달해야 함.
    찾으면 그에 대응되는 FileMeta의 directory를, 찾지 못한다면 null을 반환.
    */
    public String getFileDirectory(ObjectId dbId) {
        String directory = null;

        FileMeta found = fileMetaDao.getFileMeta(dbId);
        if ( found != null ) {
            directory = found.getDirectory();
        }

        return directory;
    }

    /*
    사용자의 재생 목록을 보여주기 위해 사용하는 함수.
    사용자가 업로드한 모든 음원의 AudioMeta를 반환하므로 사용자의 아이디(이메일 주소)만을 전달하면 된다.
    하나 이상의 AudioMeta 존재 시 List<AudioMeta>를, 하나도 없을 시 List는 empty할 것이다.
    */
    public List<AudioMeta> getPlaylist() {
        return audioMetaDao.getList();
    }
}
