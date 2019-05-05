package com.team04.musiccloud.stream.caching;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 캐시 내부에 있는 파일 중에 시간이 만료된 것을 회수합니다.
 * 이는 쓰레드로 따로 동작하도록 제작됩니다.
 *
 * 기본 default 설정은 아래와 같습니다.
 * - 경로: resources/static/media/audios
 * - 주기: 1000ms
 * - 삭제 허용 여부: 불허
 *
 * @author 오기준
 * @version 2019년 4월 29일
 */
public class AudioCollector implements Runnable {

  final String DEFAULT = Paths.get(System.getProperty("user.dir"),
      "src", "main", "resources", "static/media", "audios").toString();


  private String baseDirectory = null;
  private int period;
  private TimeUnit timeUnit;
  // 혹여 caching이 갑자기 동작하여 시스템을 부스는 것을 방지
  private boolean deleteEnable;
  private final static Logger logger = Logger.getGlobal();

  AudioCollector() {
    logger.setLevel(Level.INFO);
    baseDirectory = DEFAULT;
    period = 1000;
    timeUnit = TimeUnit.MILLISECONDS;
    deleteEnable = false;
  }

  AudioCollector(String baseDirectory) {
    this.baseDirectory = baseDirectory;
    period = 1000;
    timeUnit = TimeUnit.MILLISECONDS;
    deleteEnable = false;
  }

  String getBaseDirectory() {
    return baseDirectory;
  }

  int getPeriod() {
    return period;
  }

  void setPeriod(int period) {
    this.period = period;
  }

  TimeUnit getTimeUnit() {
    return timeUnit;
  }

  void setTimeUnit(TimeUnit timeUnit) {
    this.timeUnit = timeUnit;
  }

  void setDeleteEnable(boolean deleteEnable) {
    this.deleteEnable = deleteEnable;
  }

  /**
   * 파일이 캐시에서 만료가 되었는 지를 확인하도록 합니다.
   *
   * @param file
   * @return timeOutStatus
   */
  private boolean isFileTimeout(File file) {
    return System.currentTimeMillis() - file.lastModified() > timeUnit.toMillis(period);
  }

  /**
   * 파일을 삭제하도록 합니다.
   *
   * 주의 사항) 정상 작동 시키고 싶으면 deleteEnable을 true로 변경해야 합니다.
   * 이렇게 제작한 이유는 개발 중에 혹시나 발생할 의도치 않은
   * 파일의 삭제를 방지하기 위함입니다.
   *
   * @param file
   * @throws IOException
   */
  private void deleteFile(File file) throws IOException {
    if (deleteEnable) {
      if (!file.delete()) {
        throw new IOException();
      }
    } else {
      System.out.println("Deletion target ==> "+file.getPath());
    }
  }

  /**
   * 파일 디렉토리를 탐색합니다.
   *
   * 캐시에 해당하는 파일 디렉토리를 탐색하면서 파일을 확인을하고,
   * 파일인 경우에 파일의 시간이 만료되었는 지를 점검한 후에
   * 만료 시 삭제하도록 합니다.
   *
   * 그렇지 않고 디렉토리인 경우에는 traverse를 하도록 합니다.
   *
   * @param path
   * @throws NullPointerException
   * @throws IOException
   */
  private void traverseDirectory(String path) throws NullPointerException, IOException {
    File directory = new File(path);
    File[] fileList = directory.listFiles();
    if (fileList == null) {
      throw new NullPointerException();
    }
    for (File file : fileList) {
      if (file.isFile()) {
        final boolean isTimeOutInCache = isFileTimeout(file);
        if (isTimeOutInCache) {
          deleteFile(file);
        }
      } else if (file.isDirectory()) {
        traverseDirectory(file.getCanonicalPath());
      }
    }

  }

  @Override
  public void run() {
    try {
      traverseDirectory(baseDirectory);
    } catch (IOException e) {
      logger.severe(e.toString());
    }
  }
}
