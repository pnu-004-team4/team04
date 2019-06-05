package com.team04.musiccloud.controller;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SampleStreamingControllerTest {

  private SampleStreamingController sampleStreamingController;

  @Before
  public void setUp() throws Exception {
    sampleStreamingController = new SampleStreamingController();
  }

  @After
  public void tearDown() throws Exception {
    sampleStreamingController = null;
  }

  @Test
  public void login() {
    assertNotNull(sampleStreamingController.login());
  }

  @Test
  public void registration() {
    assertNotNull(sampleStreamingController.registration());
  }

  @Test
  public void setting() {
    assertNotNull(sampleStreamingController.setting());
  }

  @Test
  public void registerCheck() {
    assertNotNull(sampleStreamingController.registerCheck());
  }
}