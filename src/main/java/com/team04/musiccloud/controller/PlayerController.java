package com.team04.musiccloud.controller;

import com.team04.musiccloud.audio.Audio;
import com.team04.musiccloud.audio.AudioMeta;
import com.team04.musiccloud.audio.extractor.AudioExtractor;
import com.team04.musiccloud.audio.extractor.ExtractorException;
import com.team04.musiccloud.audio.extractor.Mp3Extractor;
import com.team04.musiccloud.auth.Account;
import com.team04.musiccloud.db.AccountCustomRepository;
import com.team04.musiccloud.stream.Streaming;
import com.team04.musiccloud.utilities.StaticPaths;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * Player의 GET, POST가 정의된 controller 입니다.
 *
 * 기존의 SampleStreamingController에서 PlayerController로 이동하였습니다. 라이브러리 리스트의 항목을 클릭해서 적절한 데이터를 가져올 수 있도록
 * 만들었습니다.
 *
 * @author 오기준
 * @version 2019년 5월 13일
 */
@Controller
public class PlayerController {

  private final static Logger logger = Logger.getGlobal();
  private static Path cacheDirectory = StaticPaths.tempStorage;
  private Streaming stream;
  private Audio testAudio;
  private ModelAndView base;

  // @TODO: 나중에는 지우도록 합니다. 유의사항 ==> TranscodeTest.class가 이 함수를 호출중 입니다.
  public static Audio getTestAudio() throws Exception {
    final String user = "CSK";
    final Path currentLocation = cacheDirectory.resolve(user)
        .resolve("sample.mp3").toAbsolutePath();
    final AudioExtractor extractor = new Mp3Extractor();

    MultipartFile myFile = new MockMultipartFile(currentLocation.toString(),
        "sample.mp3", null, new FileInputStream(currentLocation.toFile()));
    extractor.setBaseDirectory(cacheDirectory);
    return extractor.getAudio(myFile, user);
  }

  // @TODO: 나중에 이 내용은 반드시 변경을 해야 합니다. 테스트를 위해서 임시로 설정한 것입니다.
  private void setUp() throws Exception {
    stream = new Streaming();
    base = new ModelAndView();
    testAudio = getTestAudio();
  }

  /**
   * HTML5에서 지원하는 오디오 태그를 생성하는 역할을 합니다.
   *
   * @return HTML's audio Tag
   */
  private String audioTagGenerator(String audioLocation) {
    return "<audio id=\"bgAudio\" controls style=\"visibility:hidden;\"><source src=\""
        + audioLocation
        + "\" type=\"audio/mpeg\"></audio>";
  }

  private String getUserName() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String currentPrincipalName = authentication.getName();
    AccountCustomRepository repository = new AccountCustomRepository();
    Account SavedAccount = repository.findAccountByEmail(currentPrincipalName);
    return SavedAccount.getName();
  }

  /**
   * 최초 상태의 JSP를 반환 및 GET에 의한 호출을 담당합니다.
   *
   * @return Initial Player JSP file
   */
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public ModelAndView responseInitializedPlayer() throws IOException, ExtractorException {

    if (stream == null) {
      try {
        setUp();
      } catch (Exception e) {
        logger.severe("Setup failed ==>" + e.toString());
      }
    }

    // @TODO: 혹시 모를 POST 이후 GET이 오는 상황을 방지하기 위한 방지책이 요구됨.

    stream.getAudioFromBack(testAudio);
    stream.setUseTranscode(true);

    String dir = stream.sendAudioToFront();
    base.addObject("streamingTest", audioTagGenerator(dir));
    base.addObject("username", getUserName());
    base.setViewName("Player/player");
    return base;
  }

  /**
   * DB에서 받은 meta 배열에 적합한 리스트를 구성해서 반환합니다.
   *
   * @return HTML track list codes
   */
  private String trackTagGenerator(List<AudioMeta> metaArray) {
    StringBuilder trackTagContents = new StringBuilder();
    int counter = 1;
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
          .append("@TODO: TIME)")
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

    String item = httpServletRequest.getParameter("songs");
    List<AudioMeta> audioMetaArrayList = new ArrayList<>();

    //@TODO: 반드시 지워야 합니다.
    int numberOfPrints = 0;

    if (item.equalsIgnoreCase("all songs")) {
      numberOfPrints = 10;
    } else if (item.equalsIgnoreCase("some songs")) {
      numberOfPrints = 5;
    }

    System.out.println("POST value ==>" + item);
    for (int i = 0; i < numberOfPrints; i++) {
      audioMetaArrayList.add(testAudio.getAudioMeta());
    }
    ///// 위 내용은 지워야함 /////
    String trackList = trackTagGenerator(audioMetaArrayList);
    base.addObject("getLibrary", trackList);
    return base;
  }
}
