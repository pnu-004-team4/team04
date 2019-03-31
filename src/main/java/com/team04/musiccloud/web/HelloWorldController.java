package com.team04.musiccloud.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    
    @RequestMapping(value = "hello", method =  RequestMethod.POST)
    public String printHelloWorld(@RequestParam(value = "user") String user) {
        return "samepl, " + user + "!";
    }
}
