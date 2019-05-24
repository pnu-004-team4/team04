package com.team04.musiccloud.audio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class FileMetaBuilderTest {

  private static final String DB_ID = "123";
  private static final String DIRECTORY = "dir";
  private static final String NAME = "name";
  private static final String EXTENSION = "ext";
  private static final String USER = "usr";

  @Test
  public void builderConstructorTest() {
    FileMetaBuilder fileMetaBuilder = FileMetaBuilder.builder();

    assertNotNull(fileMetaBuilder);
  }

  @Test
  public void builderContentsTest() {
    FileMeta fileMeta = FileMetaBuilder.builder()
        .setDbId(DB_ID)
        .setDirectory(DIRECTORY)
        .setName(NAME)
        .setExtension(EXTENSION)
        .setUser(USER)
        .build();

    assertEquals(DB_ID, fileMeta.getDbId());
    assertEquals(DIRECTORY, fileMeta.getDirectory());
    assertEquals(NAME, fileMeta.getName());
    assertEquals(EXTENSION, fileMeta.getExtension());
    assertEquals(USER, fileMeta.getUser());
  }

  @Test
  public void builderFromFileMeta() {
    FileMeta source = new FileMeta(DB_ID, DIRECTORY, NAME, EXTENSION, USER);
    FileMeta fileMeta = FileMetaBuilder.builder(source).build();

    assertEquals(DB_ID, fileMeta.getDbId());
    assertEquals(DIRECTORY, fileMeta.getDirectory());
    assertEquals(NAME, fileMeta.getName());
    assertEquals(EXTENSION, fileMeta.getExtension());
    assertEquals(USER, fileMeta.getUser());
  }
}