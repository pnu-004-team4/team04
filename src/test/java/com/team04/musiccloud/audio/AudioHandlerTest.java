package com.team04.musiccloud.audio;

import com.team04.musiccloud.audio.extractor.ExtractorException;
import com.team04.musiccloud.audio.extractor.InvalidFileFormat;
import com.team04.musiccloud.db.MetadataCustomRepository;
import com.team04.musiccloud.utilities.StaticPaths;
import com.team04.musiccloud.utilities.network.NetStatusManager;
import org.junit.Ignore;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;

import static org.apache.sis.internal.jaxb.Context.LOGGER;

public class AudioHandlerTest {
    public static final String USER_NAME = "CSK";
    
    @Ignore
    public void networkAnalysis() {
        NetStatusManager netStatusManager = NetStatusManager.getInstance();
        netStatusManager.addUserNetDelay(USER_NAME, 10);
        netStatusManager.addUserNetDelay(USER_NAME, 13);
        netStatusManager.addUserNetDelay(USER_NAME, 12);
        netStatusManager.addUserNetDelay(USER_NAME, 9);
        
        LOGGER.info("Network delay avg: " + netStatusManager.getUserNetDelayAverage(USER_NAME));
    }
    
    @Ignore
    public void requestUpload() throws IOException, InvalidFileFormat, ExtractorException {
        final MultipartFile mockMultipartFile = getMockMultipartFile();
        new AudioHandler(USER_NAME).requestUpload(mockMultipartFile);
    }
    
    @Ignore
    public void requestLoad() throws IOException {
        new AudioHandler(USER_NAME).requestLoad(USER_NAME, getFirstDbId());
    }
    
    @Ignore
    public void requestDelete() throws IOException {
        new AudioHandler(USER_NAME).requestDelete(getFirstDbId());
    }
    
    private MultipartFile getMockMultipartFile() throws IOException {
        final String fileName = "sample.mp3";
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