package com.team04.musiccloud.controller;

import com.team04.musiccloud.auth.EmailServiceImplement;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@Controller
public class EmailServiceController {
    EmailServiceImplement service;

    @GetMapping("/emailConfirm")
    public String emailConfirm(@RequestParam("userEmail") String userEmail,@RequestParam("authKey")  String authKey, ModelMap model,
                               HttpServletResponse response)  throws Exception { // 이메일인증
        if (service == null)
            service = new EmailServiceImplement();
        System.out.println("authKey is:" + authKey);
        service.emailVerification(userEmail, authKey);
        //model.addAttribute("userEmail", userEmail);
        return "/emailConfirm";
    }
}
