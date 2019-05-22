package com.team04.musiccloud.db;

import com.beust.jcommander.ParameterException;
import com.team04.musiccloud.audio.AudioMeta;
import com.team04.musiccloud.audio.FileMeta;

import java.util.List;

public interface iMetadataRepository {

    /*
    AudioMeta와 FileMeta를 DB에 저장하는 함수.
    여기서 전달된 AudioMeta와 FileMeta는 동일한 dbId 값을 가지게 됨.
    성공적으로 저장하면 true를, 그렇지 않았다면 false를 반환.
    */
    boolean storeMetadata(AudioMeta audioMeta, FileMeta fileMeta);

    /*
    DB에 저장된 AudioMeta와 FileMeta를 삭제하는 함수.
    여기서 전달된 dbId는 AudioMeta와 FileMeta의 _id값.
    */
    void deleteMetadata(String dbId);

    /*
    특정 AudioMeta를 찾고자 할 때 사용하는 함수.
    여기서 AudioMeta를 찾기 위해선 dbId 값을 전달해야 함.
    찾으면 그에 대응되는 AudioMeta를, 찾지 못한다면 null을 반환.
    */
    AudioMeta getAudioMeta(String dbId);

    /*
    특정 FileMeta를 찾고자 할 때 사용하는 함수.
    여기서 FileMeta를 찾기 위해선 dbId 값을 전달해야 함.
    찾으면 그에 대응되는 FileMeta를, 찾지 못한다면 null을 반환.
    */
    FileMeta getFileMeta(String dbId) throws ParameterException;

    /*
    사용자의 재생 목록을 보여주기 위해 사용하는 함수.
    사용자가 업로드한 모든 음원의 AudioMeta를 반환하므로 사용자의 아이디(이메일 주소)만을 전달하면 된다.
    하나 이상의 AudioMeta 존재 시 List<AudioMeta>를, 하나도 없을 시 List는 empty할 것이다.
    */
    List<AudioMeta> getPlaylist();
}
