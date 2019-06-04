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

    //문광수
    @RequestMapping("/set")
    public ModelAndView userSetting() {
        return new ModelAndView("/UserSettings/usersetting");
    }

    @RequestMapping("/setcheck")
    public ModelAndView userSetCheck() {
        return new ModelAndView("/Setting/usersetcheck");
    }

    @RequestMapping("/search")
    public ModelAndView sortTest() {
        return new ModelAndView("/SearchSort/search");
    }

    @RequestMapping("/sorttest")
    public ModelAndView search() {
        return new ModelAndView("/SearchSort/testpage");
    }

    @RequestMapping("/sort")
    public ModelAndView testPage() {
        return new ModelAndView("/SearchSort/sortpage");
    }
}
