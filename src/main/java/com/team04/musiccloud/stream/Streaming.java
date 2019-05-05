package com.team04.musiccloud.stream;

import com.team04.musiccloud.audio.Audio;
import com.team04.musiccloud.stream.transcode.Transcode;
import com.team04.musiccloud.utilities.network.NetStatusManager;
import java.io.IOException;

/**
 * audio 파일의 디렉토리를 유지 관리합니다.
 *
 * `getAudioFromBack`은 Backend에서만 사용하는 코드입니다. 이를 사용하면 Backend에서 조회된 audio를 객체의 멤버로 받을 수 있게 됩니다.
 *
 * `sendAudioToFront`는 Frontend에서만 사용하는 코드입니다. 이를 사용하면 Backend로부터 받은 audio의 DIR을 Front는 받을 수 있습니다.
 *
 * >> 2019년 4월 29일 수정 extension이 붙을 수 있도록 제작하였습니다.
 *
 * @author 오기준
 * @version 2019년 4월 29일
 */
public class Streaming implements IBackStreaming, IFrontStreaming {

  private final static String baseDirectory = "server/temp/";
  private final static String directoryDelimiter = "/";
  private final static String extensionDelimiter = ".";
  private Audio audio;
  private boolean isRequireTranscode;

  @Override
  public void getAudioFromBack(Audio audio) {
    this.audio = audio;
    this.isRequireTranscode = false;
  }

  @Override
  public String sendAudioToFront() throws IOException {
    return serveAudio();
  }

  @Override
  public void setUseTranscode(boolean isRequireTranscode) {
    this.isRequireTranscode = isRequireTranscode;
  }

  private String formedServerStyle(Audio audio) {
    return baseDirectory + audio.getFileMeta().getUser() + directoryDelimiter
        + audio.getFileMeta().getName() + extensionDelimiter
        + audio.getFileMeta().getExtension();
  }

  private String serveAudio() throws IOException {
    // Network의 지연 시간을 파악하는 부분에 해당합니다.
    if (isRequireTranscode) {
      if (audio == null) {
        throw new IOException();
      }
      NetStatusManager networkMangager = NetStatusManager.getInstance();
      String userName = audio.getFileMeta().getUser();
      double userDelayAverage = networkMangager.getUserNetDelayAverage(userName);

      // Transcode를 진행하는 부분에 해당합니다.
      Transcode transcode = new Transcode(audio);
      transcode.setWeight(userDelayAverage);
      audio = transcode.getAudio();
    }
    return formedServerStyle(audio);
  }
}
