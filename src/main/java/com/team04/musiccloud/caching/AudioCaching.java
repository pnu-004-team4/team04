package com.team04.musiccloud.caching;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 실제로 외부로 노출되는 클래스로
 * 이를 통해서 캐시의 내장 기능에
 * 관련된 작업을 진행합니다.
 *
 * @author 오기준
 * @version 2019년 4월 29일
 */
public class AudioCaching {

  private AudioCollector audioCollector;

  public AudioCaching() {
    // default 설정이 됩니다.
    audioCollector = new AudioCollector();
  }

  public AudioCaching(String baseDirectory) {
    audioCollector = new AudioCollector(baseDirectory);
  }

  public void setTimeUnit(TimeUnit timeUnit) {
    audioCollector.setTimeUnit(timeUnit);
  }

  public void setPeriod(int period) {
    audioCollector.setPeriod(period);
  }

  public String getBaseDirectory() {
    return audioCollector.getBaseDirectory();
  }

  /**
   * AudioCollector를 활용하여 자원을 주기적으로 회수합니다.
   * 만약 setTimeUnit과 setPeriod가 설정안되면
   * AudioCollector의 기본 값으로 주기가 설정됩니다.
   */
  public void start() {
    int period = audioCollector.getPeriod();
    TimeUnit timeUnit = audioCollector.getTimeUnit();
    audioCollector.setDeleteEnable(false); //@TODO: Release 시에 변경할 것.

    ScheduledExecutorService service
        = Executors.newSingleThreadScheduledExecutor();
    service.scheduleAtFixedRate(audioCollector, 0, period, timeUnit);
  }
}
