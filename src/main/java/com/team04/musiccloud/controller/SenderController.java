package com.team04.musiccloud.controller;

import com.beust.jcommander.ParameterException;
import com.team04.musiccloud.audio.Audio;
import com.team04.musiccloud.audio.AudioHandler;
import com.team04.musiccloud.auth.Account;
import com.team04.musiccloud.utilities.AccountRepositoryUtil;
import com.team04.musiccloud.utilities.MusicFileUtilities;
import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * sender가 "donwload?id=MUSIC_FILE_DB_ID"의 형태로 값을 요청한 경우에 적절하게 transcode 처리를 한 데이터를 반환을 해주도록 합니다.
 *
 * @author 오기준
 * @version 2019년 5월 21일
 */
@Controller
public class SenderController {

  private Logger logger;

  @RequestMapping(value = "/download", method = RequestMethod.GET,
      produces = {MediaType.APPLICATION_OCTET_STREAM_VALUE})
  public HttpEntity<byte[]> downloadRequestFile(@RequestParam("id") String id, ModelMap model,
      HttpServletResponse response) throws IOException {

    AccountRepositoryUtil accountRepositoryUtil = AccountRepositoryUtil.getInstance();
    Account account = accountRepositoryUtil.getCurrentAccount();

    System.out.println("[CURRENT ACCOUNT CHECK]");
    System.out.println("User\t\tID\tResolution");
    System.out
        .println(account.getEmail() + "\t" + account.getId() + "\t" + account.getResolution());
    String userName = account.getEmail();

    AudioHandler audioHandler;
    Audio audio;
    try {
      audioHandler = new AudioHandler(userName);
      audio = audioHandler.requestLoad(account.getResolution(), userName, id);
    } catch (ParameterException e) {
      logger.warning("Unexpected request detected ==> "+e.toString());
      return null;
    }
    System.out.println("[USER CHECK]");
    System.out.println(audio.getFileMeta().getUser() + "\t" + userName);



    HttpHeaders header = new HttpHeaders();
    String mimeType = MusicFileUtilities.getMimeType(audio.getFileMeta().getExtension());

    if (mimeType != null) {
      header.setContentType(new MediaType("audio", mimeType));
      header.setContentLength(audio.getBytes().length);
      header.add("Accept-Ranges","bytes");
    }

    return new HttpEntity<>(audio.getBytes(), header);
  }


}

