package com.team04.musiccloud.stream.caching.manager;

import static org.junit.Assert.assertEquals;

import com.team04.musiccloud.utilities.structure.OverSizeLimitException;
import org.junit.Test;

public class UserTempAlreadyExistsTest {

  @Test(expected = UserTempAlreadyExists.class)
  public void constructor() {
    throw new UserTempAlreadyExists();
  }

  @Test
  public void constructorWithMessage() {
    try {
      throw new UserTempAlreadyExists("Test");
    } catch (Exception e) {
      assertEquals("Test", e.getMessage());
    }
  }

  @Test(expected = IllegalStateException.class)
  public void constructorWithThrowable() throws Throwable {
    try {

      try {
        throw new IllegalStateException();
      } catch (Exception e) {
        throw new UserTempAlreadyExists(e);
      }

    } catch (Exception e) {
      throw e.getCause();
    }
  }

  @Test(expected = IllegalStateException.class)
  public void constructorWithMessageAndThrowable() throws Throwable {
    try {

      try {
        throw new IllegalStateException();
      } catch (Exception e) {
        throw new UserTempAlreadyExists("Test", e);
      }

    } catch (Exception e) {
      assertEquals("Test", e.getMessage());
      throw e.getCause();
    }
  }
}
