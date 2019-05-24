package com.team04.musiccloud.controller;

import com.team04.musiccloud.audio.Audio;
import com.team04.musiccloud.audio.AudioHandler;
import com.team04.musiccloud.audio.AudioMeta;
import com.team04.musiccloud.auth.Account;
import com.team04.musiccloud.db.MetadataCustomRepository;
import com.team04.musiccloud.stream.Streaming;
import com.team04.musiccloud.utilities.AccountRepositoryUtil;
import com.team04.musiccloud.utilities.MusicFileUtilities;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Player의 GET, POST가 정의된 controller 입니다.
 *
 * 기존의 SampleStreamingController에서 PlayerController로 이동하였습니다. 라이브러리 리스트의 항목을 클릭해서 적절한 데이터를 가져올 수 있도록
 * 만들었습니다.
 *
 * @author 오기준
 * @version 2019년 5월 20일
 */
@Controller
public class PlayerController {

  private final static Logger logger = Logger.getGlobal();
  private Streaming stream;
  private ModelAndView base;
  private AccountRepositoryUtil accountRepositoryUtil;


  private void setUp() {
    stream = new Streaming();
    base = new ModelAndView();
    accountRepositoryUtil = AccountRepositoryUtil.getInstance();
  }

  private List<AudioMeta> getAudioMetaList(String userName) {
    final MetadataCustomRepository customRepository = new MetadataCustomRepository(userName);
    return customRepository.getPlaylist();
  }

  /**
   * HTML5에서 지원하는 오디오 태그를 생성하는 역할을 합니다.
   *
   * @return HTML's audio Tag
   */
  // @TODO (2019년 5월 19일 추가) Player.jsp에서 시간 관련해서 문제가 있는 것 같다.
  private String audioTagGenerator(String audioLocation, String fileExtension) {
    return "<audio id=\"bgAudio\" controls style=\"visibility:hidden;\"><source src=\""
        + audioLocation
        + "\" type=\"audio/" + MusicFileUtilities.getMimeType(fileExtension)
        + "\" id=\"nowPlaying\"></audio>";
  }

  /**
   * 최초 상태의 JSP를 반환 및 GET에 의한 호출을 담당합니다.
   *
   * @return Initial Player JSP file
   */
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public ModelAndView responseInitializedPlayer() throws IOException {
    if (stream == null) {
      try {
        setUp();
      } catch (Exception e) {
        logger.severe("Setup failed ==>" + e.toString());
      }
    }

    Account account = accountRepositoryUtil.getCurrentAccount();
    String userName = account.getEmail();

    List<AudioMeta> audioMetaArrayList = getAudioMetaList(userName);

    AudioHandler audioHandler = new AudioHandler(userName);

    String firstFileId = "";
    try {
      firstFileId = audioMetaArrayList.get(0).getDbId();
    } catch (IndexOutOfBoundsException e) {
      logger.info(e.toString());
    }

    String trackList = trackTagGenerator(userName, audioMetaArrayList);

    if (firstFileId.isEmpty()) {
      base.addObject("streaming", audioTagGenerator("", ""));
      base.addObject("username", account.getName());
      base.addObject("getLibrary", trackList);
      base.setViewName("Player/player");
      return base;
    }

    final Boolean isDoTranscode = account.getResolution();

    Audio firstAudio = audioHandler.requestLoad(isDoTranscode, userName, firstFileId);

    stream.getAudioFromBack(firstAudio);

    String url = stream.sendAudioToFront();
    String mimeType = firstAudio.getFileMeta().getExtension();

    base.addObject("streaming", audioTagGenerator(url, mimeType));
    base.addObject("username", account.getName());
    base.addObject("getLibrary", trackList);
    base.setViewName("Player/player");
    return base;
  }

  /**
   * DB에서 받은 meta 배열에 적합한 리스트를 구성해서 반환합니다.
   *
   * @return HTML track list codes
   */
  private String trackTagGenerator(String userName, List<AudioMeta> metaArray) {
    StringBuilder trackTagContents = new StringBuilder();
    int counter = 1;
    if(metaArray.isEmpty())
      return "";
    for (AudioMeta meta : metaArray) {
      trackTagContents.append("<div class=\"track\">")
          .append("<div class=\"track__number\">")
          .append(counter++)
          .append("</div>")
          .append("<div class=\"track__play\">")
          .append("<i class=\"ion-ios-play\"></i>")
          .append("</div>")
          .append("<div class=\"track__delete\">")
          .append("<i class=\"ion-android-delete\"></i>")
          .append("</div>")
          .append("<div class=\"track__title\">")
          .append(meta.getTitle())
          .append("</div>")
          .append("<div class=\"track__artist\">")
          .append(meta.getAuthor())
          .append("</div>")
          .append("<div class=\"track__length\">")
          .append("@TODO: TIME") //@TODO: 몇 분 남았는 지를 보여주도록 한다.
          .append("</div>")
          .append("<div class=\"track__owner\" hidden>")
          .append(userName)
          .append("</div>")
          .append("<div class=\"track__id\" hidden>")
          .append(meta.getDbId())
          .append("</div>")
          .append("</div>");
    }
    return trackTagContents.toString();
  }

  /**
   * Front에서 받은 요청에 대하여 적절한 처리를 한 후에 반환하는 역할을 합니다.
   *
   * @return player JSP file which updated by library entry
   */
  @RequestMapping(value = "/", method = RequestMethod.POST)
  public ModelAndView updatePlayer(HttpServletRequest httpServletRequest) {
    //@TODO: 양식 다시 보내기가 실행되는 것을 방지하도록 합니다.
    //@TODO: 백 스페이스를 하게 되면 약간의 문제가 있다...

    //@TODO: 나중에 사용하도록 할 것, 이것을 통해서 library를 다룰 수 있습니다.
    String item = httpServletRequest.getParameter("songs");
    System.out.println("Currently clicked value => " + item);

    Account account = accountRepositoryUtil.getCurrentAccount();
    String userName = account.getEmail();

    String trackList = trackTagGenerator(userName, getAudioMetaList(userName));
    base.addObject("getLibrary", trackList);
    return base;
  }
}
