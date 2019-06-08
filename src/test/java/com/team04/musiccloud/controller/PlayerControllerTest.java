package com.team04.musiccloud.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.team04.musiccloud.utilities.StaticKeys;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class PlayerControllerTest {

  @Mock
  private HttpServletRequest httpServletRequest;
  private PlayerController playerController;

  @Before
  public void setUp() throws Exception {
    StaticKeys.setKeys("mongodb://test:test@35.200.2.141/test");
    StaticKeys.setDbName("test");
    MockitoAnnotations.initMocks(this);
    playerController = new PlayerController();
  }

  @After
  public void tearDown() {
    playerController = null;
  }

  @Test
  public void responseInitializedPlayer() throws IOException {
    when(httpServletRequest.getParameter("songs")).thenReturn("All Songs");
    Authentication authentication = mock(Authentication.class);
    SecurityContext securityContext = mock(SecurityContext.class);
    when(securityContext.getAuthentication()).thenReturn(authentication);
    SecurityContextHolder.setContext(securityContext);
    when(SecurityContextHolder.getContext().getAuthentication().getName())
        .thenReturn("admin@admin.com");
    assertNotNull(playerController.responseInitializedPlayer());
  }

  @Test
  public void updatePlayer() {
    when(httpServletRequest.getParameter("songs")).thenReturn("All Songs");
    Authentication authentication = mock(Authentication.class);
    SecurityContext securityContext = mock(SecurityContext.class);
    when(securityContext.getAuthentication()).thenReturn(authentication);
    SecurityContextHolder.setContext(securityContext);
    when(SecurityContextHolder.getContext().getAuthentication().getName())
        .thenReturn("admin@admin.com");

    assertNotNull(playerController.updatePlayer(httpServletRequest));
  }
}