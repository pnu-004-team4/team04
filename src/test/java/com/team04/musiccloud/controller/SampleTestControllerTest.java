package com.team04.musiccloud.controller;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SampleTestControllerTest {

  private SampleTestController sampleTestController;

  @Before
  public void setUp() throws Exception {
    sampleTestController = new SampleTestController();
  }

  @After
  public void tearDown() throws Exception {
    sampleTestController = null;
  }

  @Test
  public void main() {
    assertNotNull(sampleTestController.main());
  }

  @Test
  public void logout() {
    assertNotNull(sampleTestController.logout());
  }

  @Test
  public void userSetting() {
    assertNotNull(sampleTestController.userSetting());
  }

  @Test
  public void sortTest() {
    assertNotNull(sampleTestController.sortTest());
  }

  @Test
  public void search() {
    assertNotNull(sampleTestController.search());
  }

  @Test
  public void testPage() {
    assertNotNull(sampleTestController.testPage());
  }
}