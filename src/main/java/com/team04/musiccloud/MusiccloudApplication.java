package com.team04.musiccloud;

import com.beust.jcommander.ParameterException;
import com.team04.musiccloud.audio.extractor.ExtractorException;
import com.team04.musiccloud.audio.extractor.InvalidFileFormat;
import com.team04.musiccloud.stream.caching.AudioCaching;
import com.team04.musiccloud.utilities.StaticKeys;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class MusiccloudApplication {

  public static void main(String[] args)
      throws IOException, ExtractorException, InvalidFileFormat, ParameterException {
    SpringApplication.run(MusiccloudApplication.class, args);

    // 2019년 4월 19일 추가 ==> 5분 단위로 GC를 수행하도록 합니다.
    AudioCaching audioCaching = new AudioCaching();
    audioCaching.setPeriod(5); // 비기능적 요구사항 수행
    audioCaching.setTimeUnit(TimeUnit.MINUTES);
    audioCaching.start();

    if (args.length != 1) {
      throw new ParameterException("You must have the Mongo DB API Keys");
    }
    StaticKeys.setKeys(args[0]);
  }
}
