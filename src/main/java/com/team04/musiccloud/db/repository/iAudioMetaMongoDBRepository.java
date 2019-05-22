package com.team04.musiccloud.db.repository;

import com.team04.musiccloud.audio.AudioMeta;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface iAudioMetaMongoDBRepository extends MongoRepository<AudioMeta, String> {

}
