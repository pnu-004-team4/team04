package com.team04.musiccloud.utilities;

import static org.junit.Assert.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class StaticPathsTest {

  private Path system;
  private Path resources;
  private Path storage;
  private Path temp;

  @Before
  public void setUp() {
    system = Paths.get(System.getProperty("user.dir"));
    resources = Paths.get(system.toString(),"src", "main", "resources", "static", "server");
    storage = Paths.get(resources.toString(), "storage");
    temp = Paths.get(resources.toString(), "temp");
  }

  @Test
  public void system() {
    assertEquals(system.toString(), StaticPaths.system.toString());
  }

  @Test
  public void staticResources() {
    assertEquals(resources.toString(), StaticPaths.staticResources.toString());
  }

  @Test
  public void storage() {
    assertEquals(storage.toString(), StaticPaths.storage.toString());
  }

  @Test
  public void tempStorage() {
    assertEquals(temp.toString(), StaticPaths.tempStorage.toString());
  }
}
