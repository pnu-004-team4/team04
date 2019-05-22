package com.team04.musiccloud.db.repository;

import com.beust.jcommander.ParameterException;
import com.team04.musiccloud.audio.AudioMeta;
import com.team04.musiccloud.audio.FileMeta;
import com.team04.musiccloud.audio.FileMetaBuilder;
import com.team04.musiccloud.db.iMetadataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MetadataRepository implements iMetadataRepository {
    //@TODO collection 생성에 대해 알아보기.
    @Autowired
    iAudioMetaMongoDBRepository audioMetaMongoDBRepository;
    @Autowired
    iFileMetaMongoDBRepository fileMetaMongoDBRepository;

    //@TODO dbId에 대한 테스트를 꼭 할 것.
    public boolean storeMetadata(AudioMeta audioMeta, FileMeta fileMeta) {
        audioMetaMongoDBRepository.insert(audioMeta);
        fileMeta = FileMetaBuilder.builder().setDbId(audioMeta.getDbId()).build();
        fileMetaMongoDBRepository.insert(fileMeta);

        return true;
    }

    public void deleteMetadata(String dbId) {
        audioMetaMongoDBRepository.deleteById(dbId);
        fileMetaMongoDBRepository.deleteById(dbId);
    }

    public AudioMeta getAudioMeta(String dbId) {
        AudioMeta audioMeta = null;

        Optional<AudioMeta> found = audioMetaMongoDBRepository.findById(dbId);
        if (found.isPresent()) {
            audioMeta = found.get();
        }

        return audioMeta;
    }

    public FileMeta getFileMeta(String dbId) throws ParameterException {
        FileMeta fileMeta = null;

        Optional<FileMeta> found = fileMetaMongoDBRepository.findById(dbId);
        if (found.isPresent()) {
            fileMeta = found.get();
        }

        return fileMeta;
    }

    public List<AudioMeta> getPlaylist() {
        return audioMetaMongoDBRepository.findAll();
    }
}
