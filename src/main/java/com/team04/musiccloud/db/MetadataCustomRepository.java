package com.team04.musiccloud.db;

import com.team04.musiccloud.audio.AudioMeta;
import com.team04.musiccloud.audio.FileMeta;

import java.util.List;

public interface MetadataCustomRepository {

    /*
    AudioMeta와 FileMeta를 DB에 저장하는 함수.
    FileMeta의 user 변수를 이용해서 저장하므로,
    추후에 특정 AudioMeta나 FileMeta를 찾고자 할때는 FileMeta의 user 값을 알아야 한다.
    여기서 FileMeta의 user는 사용자가 가입 시 기입한 이메일 주소로 간주한다.
    추가적으로, AudioMeta와 FileMeta는 각자 @Id로 선언된 dbId가 primary key 역할을 맡으며,
    여기서 전달된 AudioMeta와 FileMeta는 동일한 dbId 값을 가지게 된다.
    성공적으로 저장하면 true를, 그렇지 않았다면 false를 반환한다.
    */
    public boolean storeMetadata(AudioMeta audioMeta, FileMeta fileMeta);

    /*
    특정 AudioMeta를 찾고자 할 때 사용하는 함수.
    AudioMeta는 여러 필드 값들 대신 고유 String을 @Id를 primary key와 비슷하게 사용한다.
    고로, AudioMeta를 찾기 위해선 사용자의 아이디(이메일 주소)와 @Id인 dbId 값을 알아야 한다.
    찾으면 그에 대응되는 AudioMeta를, 찾지 못한다면 null을 반환한다.
    */
    public AudioMeta getAudioMeta(String email, String dbId);

    /*
    특정 FileMeta를 찾고자 할 때 사용하는 함수.
    FileMeta는 여러 필드 값들 대신 고유 String을 @Id를 primary key와 비슷하게 사용한다.
    고로, FileMeta를 찾기 위해선 사용자의 아이디(이메일 주소)와 @Id인 dbId 값을 알아야 한다.
    찾으면 그에 대응되는 FileMeta를, 찾지 못한다면 null을 반환한다.
    */
    public FileMeta getFileMeta(String email, String dbId);

    /*
    사용자의 재생 목록을 보여주기 위해 사용하는 함수.
    사용자가 업로드한 모든 음원의 AudioMeta를 반환하므로 사용자의 아이디(이메일 주소)만을 전달하면 된다.
    하나 이상의 AudioMeta 존재 시 List<AudioMeta>를, 하나도 없을 시 List는 empty할 것이다.
    */
    public List<AudioMeta> getPlaylist(String email);
}
