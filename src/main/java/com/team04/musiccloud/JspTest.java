package com.team04.musiccloud;

import com.team04.musiccloud.audio.ExtractorException;
import com.team04.musiccloud.audio.Tester;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class JspTest {
    
    @RequestMapping("/")
    public ModelAndView main() throws IOException, ExtractorException {
        Tester.test();
        return new ModelAndView("index");
    }
}
