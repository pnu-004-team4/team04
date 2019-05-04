package com.team04.musiccloud.controller;

import com.team04.musiccloud.audio.Audio;
import com.team04.musiccloud.audio.extractor.AudioExtractor;
import com.team04.musiccloud.audio.extractor.ExtractorException;
import com.team04.musiccloud.audio.extractor.Mp3Extractor;
import com.team04.musiccloud.auth.AccountService;
import com.team04.musiccloud.stream.Streaming;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * UI 점검 및 스트리밍, 로그인 테스트용 클래스
 *
 * UI 점검은 아래와 같은 링크로 가능합니다.
 * 127.0.0.1/login    ==> login.jsp 파일 점검
 * 127.0.0.1/register ==> register.jsp 파일 점검
 * 127.0.0.1/player   ==> player.jsp 파일 점검
 *
 * 스트리밍 테스트 과정은 다음과 같습니다.
 *
 * 1. JSP Read를 위한 ModelAnd View 객체 생성
 * 2. 제작한 Streaming Test를 통해서 Transcode 처리된 데이터의 DIR을 받음
 * 3. 해당 DIR을 기반으로 visibility:hidden인 audio tag 생성
 * 4. 해당 태그를 player request에 삽입
 * 5. 대상 JSP 설정(`setViewName`) 6. 해당 JSP 객체 반환
 *
 * 이렇게 하면 127.0.0.1/player 에서 재생 버튼을 누르면 음원을 재생할 수 있게 됩니다.
 *
 * >> 2019년 4월 9일 오후 9시 추가
 *
 * 로그인은 __회원 가입 창을 최초 1회__ 키면 이하 계정이 자동으로 등록됩니다.
 * ID: smile@smile.com
 * PW: 1234
 * 해당 계정을 바탕으로 로그인을 실시하면 Player 창으로 이동을 하게 됩니다.
 *
 * >> 2019년 4월 29일 오전 12시 추가
 *
 * 91 번째 줄에서 폴더가 가변적으로 변경이 되는 지를 확인을 해보도록 했습니다.
 * 80 번째 줄에서 mp3 파일인 경우 __확장자__를 기입해줘야 합니다.
 * SampleStreamingController로 이름이 변경됨.
 * 사용하지 않는 변수 제거
 * 좀 더 Universal한 User로 제작함.
 *
 * @author 오기준, 이경찬
 * @version 2019년 4월 29일
 */
@Controller
public class SampleStreamingController {

  @Autowired
  AccountService accountService;

  private static Path testDirectory = Paths
      .get(System.getProperty("user.dir"), "src", "main", "resources", "static/media", "audios");

  @RequestMapping("/login")
  public ModelAndView login() {
    return new ModelAndView("Login/login");
  }

  @RequestMapping("/register")
  public ModelAndView registration() { return new ModelAndView("Registration/register"); }

  @RequestMapping("/setting")
  public ModelAndView setting() {
    System.out.println("User setting");
    return new ModelAndView("Setting/setting");
  }

  @RequestMapping("/")
  public ModelAndView player() throws IOException, ExtractorException {
    ModelAndView base = new ModelAndView();
    String dir = SampleStreamingController.test(); // 2019년 4월 29일 수정됨.
    String section =
        "<audio id=\"bgAudio\" controls style=\"visibility:hidden;\"><source src=\"" + dir
            + "\" type=\"audio/mpeg\"></audio>";
    base.addObject("streamingTest", section);
    base.setViewName("Player/player");
    return base;
  }

  private static String test() throws IOException, ExtractorException {
    Streaming myStream = new Streaming();

    String user = "CSK"; // 2019년 4월 29일 수정됨.
    
    // Backend에서 준비되는 과정...
    MultipartFile multipartFile = getMockMultipartFile(user);
    final AudioExtractor extractor = new Mp3Extractor();

    Audio src = extractor.getAudio(multipartFile, user);//extractor.getAudioMeta(multipartFile);
    //src.setOwner("test");

    // Backend에서 가져오는 과정...
    myStream.getAudioFromBack(src);

    String dir = myStream.sendAudioToFront();
    System.out.println(dir);
    return dir;
  }

  private static MultipartFile getMockMultipartFile(String user) throws IOException {

    final Path filePath = testDirectory.resolve(user).resolve("sample.mp3").toAbsolutePath();

    return new MockMultipartFile(filePath.toString(), "sample.mp3", null,
        new FileInputStream(filePath.toFile()));
  }

  /**
   * 2019년 4월 29일 추가됨. - 이경찬
   */
  @RequestMapping("/registerCheck")
  public ModelAndView registerCheck() { return new ModelAndView("Registration/registerCheck"); }
}
