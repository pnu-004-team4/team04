package com.team04.musiccloud.db.repository;

import com.team04.musiccloud.audio.FileMeta;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface iFileMetaMongoDBRepository extends MongoRepository<FileMeta, String> {

}
