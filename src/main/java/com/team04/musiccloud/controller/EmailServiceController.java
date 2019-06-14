package com.team04.musiccloud.controller;

import com.team04.musiccloud.auth.EmailServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@Controller
public class EmailServiceController {
    EmailServiceImpl service;

    @GetMapping("/emailConfirm")
    public String emailConfirm(@RequestParam("userEmail") String userEmail,@RequestParam("authKey")  String authKey, ModelMap model,
                               HttpServletResponse response)  throws Exception { // 이메일인증
        if (service == null)
            service = new EmailServiceImpl();
        System.out.println("authKey is:" + authKey);
        service.emailVerification(userEmail, authKey);
        //model.addAttribute("userEmail", userEmail);
        return "/Registration/emailConfirm";
    }

    @RequestMapping("/emailConfirm")
    public ModelAndView emailConfirm() {
        return new ModelAndView("Registration/emailConfirm");
    }
}
