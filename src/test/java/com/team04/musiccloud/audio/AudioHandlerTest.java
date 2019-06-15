package com.team04.musiccloud.audio;

import static org.junit.Assert.fail;

import com.team04.musiccloud.db.MetadataCustomRepository;
import com.team04.musiccloud.utilities.StaticKeys;
import com.team04.musiccloud.utilities.StaticPaths;
import com.team04.musiccloud.utilities.network.NetStatusManager;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

/**
 * 이 테스트 파일은 로컬 작업용으로만 사용하도록 할 것. 만약 사용하려고 한다면 총괄 기술담당자에게 DB ID를 획득하고 작업 수행하도록 할 것
 *
 * @author 오기준, 김창섭
 * @version 2019년 5월 19일
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AudioHandlerTest {

  private final Logger LOGGER = Logger.getGlobal();
  private final String USER_NAME = "admin@admin.com";

  @Before
  public void networkAnalysis() {
    StaticKeys.setKeys("mongodb://test:test@35.200.2.141/test");
    StaticKeys.setDbName("test");
    NetStatusManager netStatusManager = NetStatusManager.getInstance();
    netStatusManager.addUserNetDelay(USER_NAME, 10);
    netStatusManager.addUserNetDelay(USER_NAME, 13);
    netStatusManager.addUserNetDelay(USER_NAME, 12);
    netStatusManager.addUserNetDelay(USER_NAME, 9);

    LOGGER.info("Network delay avg: " + netStatusManager.getUserNetDelayAverage(USER_NAME));
  }

  @Test // @Test 설정 ==> 정상 작동 확인 (2019년 5월 19일, 검토: 오기준)
  public void requestAUpload() {
    try {
      final MultipartFile mockMultipartFile = getMockMultipartFile();
      new AudioHandler(USER_NAME).requestUpload(mockMultipartFile);
    } catch (Exception e) {
      fail(e.toString());
    }
  }

  @Test// @Test 설정 ==> 정상 작동 확인 (2019년 5월 19일, 검토: 오기준)
  public void requestBLoad() {
    try {
      new AudioHandler(USER_NAME).requestLoad(true, USER_NAME, getFirstDbId());
    } catch (Exception e) {
      fail(e.toString());
    }
  }

  @Test
  public void requestDelete() {
    try {
      Path sourceLocation = StaticPaths.storage.resolve(USER_NAME).resolve("sample.mp3");
      Path targetLocation = StaticPaths.storage.resolve(USER_NAME).resolve("sample.tmp.mp3");
      Files.copy(sourceLocation, targetLocation);
      new AudioHandler(USER_NAME).requestDelete(getFirstDbId());
      Files.move(targetLocation, sourceLocation);
    } catch (Exception e) {
      fail(e.toString());
    }
  }

  public MultipartFile getMockMultipartFile() throws IOException {
    final String fileName = "sample.mp3";
    final Path filePath = StaticPaths.storage
        .resolve(USER_NAME)
        .resolve(fileName)
        .toAbsolutePath();

    return new MockMultipartFile(
        filePath.toString(),
        fileName,
        null,
        new FileInputStream(filePath.toFile())
    );
  }

  public String getFirstDbId() {
    return MetadataCustomRepository.getInstance(USER_NAME)
        .getPlaylist().get(0)
        .getDbId();
  }
}
