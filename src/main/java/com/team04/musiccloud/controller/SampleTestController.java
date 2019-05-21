package com.team04.musiccloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SampleTestController {
    
    @RequestMapping("/test") // 변경됨: 2019년 4월 9일, 변경자: 이경찬
    public ModelAndView main() {
        return new ModelAndView("index");
    }
    
    @RequestMapping("/logout")

    public ModelAndView logout() {
        return new ModelAndView("/Login/logout");
    }

    //문광수
    @RequestMapping("/set")
    public ModelAndView UserSetting() {
        return new ModelAndView("/UserSettings/usersetting");
    }

    @RequestMapping("/setcheck")
    public ModelAndView UserSetCheck() {
        return new ModelAndView("/Setting/usersetcheck");
    }

    @RequestMapping("/search")
    public ModelAndView sorttest() {
        return new ModelAndView("/SearchSort/search");
    }

    @RequestMapping("/sorttest")
    public ModelAndView search() {
        return new ModelAndView("/SearchSort/testpage");
    }

    @RequestMapping("/sort")
    public ModelAndView testpage() {
        return new ModelAndView("/SearchSort/sortpage");
    }
}
