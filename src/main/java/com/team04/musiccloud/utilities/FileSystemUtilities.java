package com.team04.musiccloud.utilities;

import java.nio.file.Path;
import java.util.Optional;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileSystemUtilities {

  private FileSystemUtilities() {
  }

  public static Optional<String> getName(String path) {
    return Optional.ofNullable(FilenameUtils.getBaseName(path));
  }

  public static Optional<String> getName(MultipartFile multipartFile) {
    return getName(multipartFile.getOriginalFilename());
  }

  public static Optional<String> getExtension(String path) {
    return Optional.ofNullable(FilenameUtils.getExtension(path));
  }

  public static Optional<String> getExtension(MultipartFile multipartFile) {
    return getExtension(multipartFile.getOriginalFilename());
  }

  public static boolean updateModifiedDate(Path path) {
    return path.toFile().setLastModified(System.currentTimeMillis());
  }
}
