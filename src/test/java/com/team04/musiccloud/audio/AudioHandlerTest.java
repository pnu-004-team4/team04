package com.team04.musiccloud.audio;

import com.team04.musiccloud.audio.extractor.ExtractorException;
import com.team04.musiccloud.audio.extractor.InvalidFileFormat;
import com.team04.musiccloud.db.MetadataCustomRepository;
import com.team04.musiccloud.utilities.StaticPaths;
import com.team04.musiccloud.utilities.network.NetStatusManager;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
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
  private final String USER_NAME = "CSK";

  @Ignore// @Before 설정
  public void networkAnalysis() {
    //StaticKeys.setKeys(dbId);
    NetStatusManager netStatusManager = NetStatusManager.getInstance();
    netStatusManager.addUserNetDelay(USER_NAME, 10);
    netStatusManager.addUserNetDelay(USER_NAME, 13);
    netStatusManager.addUserNetDelay(USER_NAME, 12);
    netStatusManager.addUserNetDelay(USER_NAME, 9);

    LOGGER.info("Network delay avg: " + netStatusManager.getUserNetDelayAverage(USER_NAME));
  }

  @Ignore // @Test 설정 ==> 정상 작동 확인 (2019년 5월 19일, 검토: 오기준)
  public void request_A_Upload() throws IOException, InvalidFileFormat, ExtractorException {
    final MultipartFile mockMultipartFile = getMockMultipartFile();
    new AudioHandler(USER_NAME).requestUpload(mockMultipartFile);
  }

  @Ignore // @Test 설정 ==> 정상 작동 확인 (2019년 5월 19일, 검토: 오기준)
  public void request_B_Load() throws IOException {
    new AudioHandler(USER_NAME).requestLoad(USER_NAME, getFirstDbId());
  }

  @Ignore
  public void requestDelete() throws IOException {
    new AudioHandler(USER_NAME).requestDelete(getFirstDbId());
  }

  private MultipartFile getMockMultipartFile() throws IOException {
    final String fileName = "sample2.mp3";
    final String userName = "test";
    final Path filePath = StaticPaths.storage
        .resolve(userName)
        .resolve(fileName)
        .toAbsolutePath();

    return new MockMultipartFile(
        filePath.toString(),
        fileName,
        null,
        new FileInputStream(filePath.toFile())
    );
  }

  private String getFirstDbId() {
    return new MetadataCustomRepository(USER_NAME)
        .getPlaylist().get(0)
        .getDbId();
  }
}