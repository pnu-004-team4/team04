package com.team04.musiccloud.stream.caching.manager;

import com.team04.musiccloud.utilities.FileSystemUtilities;
import com.team04.musiccloud.utilities.StaticPaths;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class CacheManager {

  private String user;
  private boolean created;


  public CacheManager(String user) {
    this.user = user;
    this.created = false;
  }

  public Path getUserCachePath() {
    return Paths.get(StaticPaths.tempStorage.toString(), user);
  }


  public boolean exists(String fileFullName) {
    final Path fileInCache = this.getUserCachePath().resolve(fileFullName);
    return Files.exists(fileInCache);
  }

  public void createCacheOf(Path source, String fileFullName) throws IOException {
    createUserTemp();
    Files.copy(source, this.getUserCachePath().resolve(fileFullName),
        StandardCopyOption.REPLACE_EXISTING);
  }

  public void createUserTemp() throws UserTempAlreadyExists {
    created = false;
    File directory = new File(this.getUserCachePath().toString());

    if (directory.mkdirs()) {
      created = true;
    }
  }

  public void updateOrLoad(Path sourceDirectory, String fileFullName) throws IOException {
    if (this.exists(fileFullName)) {
      final Path userTemp = getUserCachePath();
      FileSystemUtilities.updateModifiedDate(userTemp);
      created = false;
    } else {
      createCacheOf(sourceDirectory.resolve(fileFullName), fileFullName);
      created = true;
    }
  }

  public boolean isDoCreated() {
    return created;
  }
}
