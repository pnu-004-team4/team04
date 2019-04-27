package com.team04.musiccloud.stream;

import com.team04.musiccloud.audio.Audio;

/**
 * 제작자       : 오기준
 * 최종 수정일  : 2019년 4월 6일
 * 내용         : 백 엔드에서 Streaming을 호출하는 경우 사용하는 인터페이스
 */
public interface IBackStreaming {

  void getAudioFromBack(Audio audio);
}
