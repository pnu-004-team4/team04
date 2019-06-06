package com.team04.musiccloud.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.team04.musiccloud.utilities.StaticKeys;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SettingControllerTest {

  private SettingController settingController;

  @Before
  public void setUp() throws Exception {
    StaticKeys.setKeys("mongodb://test:test@35.200.2.141/test");
    StaticKeys.setDbName("test");
    settingController = new SettingController();
  }

  @After
  public void tearDown() throws Exception {
    settingController = null;
  }

  @Test
  public void userSetCheck() {
    assertNotNull(settingController.userSetCheck());
  }

  @Test
  public void confirmChangedSetting() {
    assertEquals("redirect:/"
        , settingController.confirmChangedSetting(
            "admin@admin.com",
            "{bcrypt}$2a$10$OlMFo22iNr0LkMsP96uI5.wi59q0O36REKrqiCMTzFzUS7YKT0mkK",
            "admin",
            "false"
        ));
  }
}