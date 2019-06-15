package com.team04.musiccloud.stream.caching.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.team04.musiccloud.utilities.StaticPaths;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class CacheManagerTest {

  private static final String USER = "admin@admin.com";
  public static final String SAMPLE = "sample.mp3";

  private CacheManager cacheManager;

  @Before
  public void setUp() {
    cacheManager = new CacheManager(USER);
  }

  @Ignore
  public void getUserCachePath() {
    final Path expect = Paths.get(StaticPaths.tempStorage.toString(), USER);
    final Path actual = cacheManager.getUserCachePath();
    assertEquals(expect.toString(), actual.toString());
  }

  @Test
  public void exists() {
    cacheManager.createUserTemp();
    assertTrue(Files.exists(cacheManager.getUserCachePath()));
  }

  @Test
  public void updateOrLoad() throws IOException {
    final Path path = Paths.get(StaticPaths.storage.toString(), USER);
    cacheManager.updateOrLoad(path, SAMPLE);

    assertTrue(cacheManager.exists(SAMPLE));
  }
}
