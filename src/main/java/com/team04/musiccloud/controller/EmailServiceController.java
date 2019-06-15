package com.team04.musiccloud.controller;

import com.team04.musiccloud.auth.EmailServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmailServiceController {

  private static EmailServiceImpl service;

  @GetMapping("/emailConfirm")
  public String emailConfirm(@RequestParam("userEmail") String userEmail,
      @RequestParam("authKey") String authKey) { // 이메일인증
    if (service == null) {
      service = new EmailServiceImpl();
    }
    System.out.println("authKey is:" + authKey);
    service.emailVerification(userEmail, authKey);
    return "/Registration/emailConfirm";
  }

  @RequestMapping("/emailConfirm")
  public ModelAndView emailConfirm() {
    return new ModelAndView("Registration/emailConfirm");
  }
}
