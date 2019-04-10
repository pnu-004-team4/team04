package com.team04.musiccloud;

import com.team04.musiccloud.audio.ExtractorException;
import com.team04.musiccloud.audio.Tester;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class JspTest {

    @RequestMapping("/test") // 변경됨: 2019년 4월 9일, 변경자: 이경찬
    public ModelAndView main() {
        return new ModelAndView("index");
    }

    @RequestMapping("/logout")
    public ModelAndView logout() {
        return new ModelAndView("/Login/logout");
    }
}
