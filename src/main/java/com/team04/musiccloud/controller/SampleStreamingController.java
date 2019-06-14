package com.team04.musiccloud.controller;

import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * UI 점검 및 스트리밍, 로그인 테스트용 클래스
 *
 * UI 점검은 아래와 같은 링크로 가능합니다. 127.0.0.1/login    ==> login.jsp 파일 점검 127.0.0.1/register ==>
 * register.jsp 파일 점검 127.0.0.1/player   ==> player.jsp 파일 점검
 *
 * 스트리밍 테스트 과정은 다음과 같습니다.
 *
 * 1. JSP Read를 위한 ModelAnd View 객체 생성 2. 제작한 Streaming Test를 통해서 Transcode 처리된 데이터의 DIR을 받음 3. 해당
 * DIR을 기반으로 visibility:hidden인 audio tag 생성 4. 해당 태그를 player request에 삽입 5. 대상 JSP
 * 설정(`setViewName`) 6. 해당 JSP 객체 반환
 *
 * 이렇게 하면 127.0.0.1/player 에서 재생 버튼을 누르면 음원을 재생할 수 있게 됩니다.
 *
 * >> 2019년 4월 9일 오후 9시 추가
 *
 * 로그인은 __회원 가입 창을 최초 1회__ 키면 이하 계정이 자동으로 등록됩니다. ID: smile@smile.com PW: 1234 해당 계정을 바탕으로 로그인을 실시하면
 * Player 창으로 이동을 하게 됩니다.
 *
 * >> 2019년 4월 29일 00시 추가
 *
 * 91 번째 줄에서 폴더가 가변적으로 변경이 되는 지를 확인을 해보도록 했습니다. 80 번째 줄에서 mp3 파일인 경우 __확장자__를 기입해줘야 합니다.
 * SampleStreamingController로 이름이 변경됨. 사용하지 않는 변수 제거 좀 더 Universal한 User로 제작함.
 *
 * >> 2019년 5월 13일 00시 추가
 *
 * player 관련 내용은 PlayerController로 이동하였습니다.
 *
 * @author 오기준, 이경찬
 * @version 2019년 5월 13일
 */
@Controller
public class SampleStreamingController {
  private final static Logger logger = Logger.getGlobal();

  @RequestMapping("/login")
  public ModelAndView login() {
    logger.info("login called");
    return new ModelAndView("Login/login");
  }
  @RequestMapping("/login?error")
  public ModelAndView loginError(){
    logger.info("loginError called");
    return new ModelAndView("Login/login");
  }

  @RequestMapping("/register")
  public ModelAndView registration() {
    return new ModelAndView("Registration/register");
  }

  @RequestMapping("/setting")
  public ModelAndView setting() {
    logger.info("User setting");
    return new ModelAndView("Setting/setting");
  }

  @RequestMapping("/registerCheck")
  public ModelAndView registerCheck() {
    return new ModelAndView("Registration/registerCheck");
  }
}
