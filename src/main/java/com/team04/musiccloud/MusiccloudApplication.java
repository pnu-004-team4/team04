package com.team04.musiccloud;

import com.team04.musiccloud.audio.ExtractorException;
import com.team04.musiccloud.audio.InvalidFileFormat;
import com.team04.musiccloud.audio.Tester;
import com.team04.musiccloud.caching.AudioCaching;
import com.team04.musiccloud.controller.SampleStreamingController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class MusiccloudApplication {
    
    public static void main(String[] args) throws IOException, ExtractorException, InvalidFileFormat {
        SpringApplication.run(MusiccloudApplication.class, args);

        // 2019년 4월 19일 추가 ==> 5분 단위로 GC를 수행하도록 합니다.
        AudioCaching audioCaching = new AudioCaching();
        audioCaching.setPeriod(5); // 비기능적 요구사항 수행
        audioCaching.setTimeUnit(TimeUnit.MINUTES);
        audioCaching.start();
    
        Tester.testUploader();
        Tester.testLoader("123");
        new SampleStreamingController().player();
    }
}
