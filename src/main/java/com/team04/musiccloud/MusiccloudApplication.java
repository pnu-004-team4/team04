package com.team04.musiccloud;

import com.team04.musiccloud.audio.ExtractorException;
import com.team04.musiccloud.audio.InvalidFileFormat;
import com.team04.musiccloud.audio.Tester;
import com.team04.musiccloud.stream.StreamingTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class MusiccloudApplication {
    
    public static void main(String[] args) throws IOException, ExtractorException, InvalidFileFormat {
        SpringApplication.run(MusiccloudApplication.class, args);
        Tester.test();
        new StreamingTest().player();
    }
}
