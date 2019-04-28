package com.team04.musiccloud.db;

import com.team04.musiccloud.audio.AudioMeta;
import com.team04.musiccloud.audio.FileMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MetadataCustomMethods implements MetadataCustomRepository {

    @Autowired
    MongoOperations mongoOperations;

    @Override
    public boolean storeMetadata(AudioMeta audioMeta, FileMeta fileMeta) {
        boolean storeCompleted = true;

        mongoOperations.insert(audioMeta, "audiometa." + fileMeta.getUser());
        fileMeta.setDbId(audioMeta.getDbId());
        mongoOperations.insert(fileMeta, "filemeta." + fileMeta.getUser());

        return storeCompleted;
    }

    @Override
    public AudioMeta getAudioMeta(String email, String dbId) {
        return mongoOperations.findById(dbId, AudioMeta.class, "audiometa." + email);
    }

    @Override
    public FileMeta getFileMeta(String email, String dbId) {
        return mongoOperations.findById(dbId, FileMeta.class, "filemeta." + email);
    }

    @Override
    public List<AudioMeta> getPlaylist(String email) {
        return mongoOperations.findAll(AudioMeta.class, "audiometa." + email);
    }
}
