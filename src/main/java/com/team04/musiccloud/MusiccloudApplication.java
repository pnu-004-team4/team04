package com.team04.musiccloud;

import com.team04.musiccloud.audio.*;
import com.team04.musiccloud.controller.SampleStreamingController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class MusiccloudApplication {
    
    public static void main(String[] args) throws IOException, ExtractorException, InvalidFileFormat {
        SpringApplication.run(MusiccloudApplication.class, args);
        TempGarbageCollector.startGC();
        Tester.test();
        new SampleStreamingController().player();
    }
}
