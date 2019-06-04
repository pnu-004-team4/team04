package com.team04.musiccloud.utilities;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

public class FileSystemUtilitiesTest {

  @Test
  public void getName() {
    final Optional<String> name = FileSystemUtilities.getName("dir1/dir2/test.txt");
    assertEquals("test", name.orElse(""));
  }

  @Test
  public void getName1() throws IOException {
    final Optional<String> name = FileSystemUtilities.getName(getMockMultipartFile());
    assertEquals("sample", name.orElse(""));
  }

  @Test
  public void getExtension() {
    final Optional<String> name = FileSystemUtilities.getExtension("dir1/dir2/test.txt");
    assertEquals("txt", name.orElse(""));
  }

  @Test
  public void getExtension1() throws IOException {
    final Optional<String> name = FileSystemUtilities.getExtension(getMockMultipartFile());
    assertEquals("mp3", name.orElse(""));
  }

  @Test
  public void updateModifiedDate() {
    assertFalse(FileSystemUtilities.updateModifiedDate(Paths.get("test.txt")));
  }

  private MultipartFile getMockMultipartFile() throws IOException {
    final String fileName = "sample.mp3";
    final Path filePath = StaticPaths.storage
        .resolve("admin@admin.com")
        .resolve(fileName)
        .toAbsolutePath();

    return new MockMultipartFile(
        filePath.toString(),
        fileName,
        null,
        new FileInputStream(filePath.toFile())
    );
  }
}
