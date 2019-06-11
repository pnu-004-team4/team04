package com.team04.musiccloud.controller;

import com.team04.musiccloud.auth.EmailServiceImplement;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.logging.Logger;

@Controller
public class EmailController {

    EmailServiceImplement service = new EmailServiceImplement();

    @RequestMapping(value = "/emailConfirm", method = RequestMethod.GET)
    public String emailConfirm(String userEmail, String authKey) { // 이메일인증
        service.emailVerification(userEmail, authKey);
        //model.addAttribute("userEmail", userEmail);
        return "/emailConfirm";
    }
}
