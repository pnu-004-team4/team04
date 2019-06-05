package com.team04.musiccloud.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.team04.musiccloud.audio.AudioHandlerTest;
import com.team04.musiccloud.audio.extractor.ExtractorException;
import com.team04.musiccloud.audio.extractor.InvalidFileFormat;
import com.team04.musiccloud.utilities.StaticKeys;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

public class SenderAndUploadControllerTest {

  @Mock
  private HttpServletResponse httpServletResponse;
  @Mock
  private ModelMap model;
  private SenderController senderController;
  private UploadController uploadController;
  private AudioHandlerTest audioHandlerTest;

  @Before
  public void setUp() throws Exception {
    StaticKeys.setKeys("mongodb://test:test@35.200.2.141/test");
    StaticKeys.setDbName("test");
    audioHandlerTest = new AudioHandlerTest();
    senderController = new SenderController();
    uploadController = new UploadController();
  }

  @After
  public void tearDown() throws Exception {
    senderController = null;
  }

  @Test
  public void sendAndUpload() throws IOException, ExtractorException, InvalidFileFormat {
    MultipartFile multipartFile = audioHandlerTest.getMockMultipartFile();
    Authentication authentication = mock(Authentication.class);
    SecurityContext securityContext = mock(SecurityContext.class);
    when(securityContext.getAuthentication()).thenReturn(authentication);
    SecurityContextHolder.setContext(securityContext);
    when(SecurityContextHolder.getContext().getAuthentication().getName())
        .thenReturn("admin@admin.com");
    assertNotNull(uploadController.uploadFile(multipartFile, "admin@admin.com"));
    String id = audioHandlerTest.getFirstDbId();
    assertNotNull(senderController.downloadRequestFile(id, model, httpServletResponse));
    audioHandlerTest.requestDelete();
  }
}