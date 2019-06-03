package com.team04.musiccloud.utilities;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Ignore;

public class StaticPathsTest {

  private String system;
  private String resources;
  private String storage;
  private String temp;

  @Before
  public void setUp() {
    system = System.getProperty("user.dir");
    resources = system + "\\src\\main\\resources\\static\\server";
    storage = resources + "\\storage";
    temp = resources + "\\temp";
  }

  @Ignore
  public void system() {
    assertEquals(system, StaticPaths.system.toString());
  }

  @Ignore
  public void staticResources() {
    assertEquals(resources, StaticPaths.staticResources.toString());
  }

  @Ignore
  public void storage() {
    assertEquals(storage, StaticPaths.storage.toString());
  }

  @Ignore
  public void tempStorage() {
    assertEquals(temp, StaticPaths.tempStorage.toString());
  }
}
