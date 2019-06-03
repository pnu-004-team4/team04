package com.team04.musiccloud.utilities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

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

  @Test
  public void system() {
    assertEquals(system, StaticPaths.system.toString());
  }

  @Test
  public void staticResources() {
    assertEquals(resources, StaticPaths.staticResources.toString());
  }

  @Test
  public void storage() {
    assertEquals(storage, StaticPaths.storage.toString());
  }

  @Test
  public void tempStorage() {
    assertEquals(temp, StaticPaths.tempStorage.toString());
  }
}
