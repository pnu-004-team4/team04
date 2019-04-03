package com.team04.musiccloud;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class JSPtest {
    
    @RequestMapping("/")
    public ModelAndView main(){
        ModelAndView view = new ModelAndView("index");
        return view;
    }
}
