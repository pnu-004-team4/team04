package com.team04.musiccloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SampleTestController {

    @RequestMapping("/logout")
    public ModelAndView logout() {
        return new ModelAndView("/Login/logout");
    }
}
