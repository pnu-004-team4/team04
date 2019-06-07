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

    @RequestMapping("/setcheck")
    public ModelAndView userSetCheck() {
        return new ModelAndView("/Setting/usersetcheck");
    }

}
