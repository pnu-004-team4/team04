package com.team04.musiccloud;

import com.team04.musiccloud.Audio.Tester;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class JSPtest {
    
    @RequestMapping("/")
    public ModelAndView main() {
        Tester.test();
        return new ModelAndView("index");
    }
}
