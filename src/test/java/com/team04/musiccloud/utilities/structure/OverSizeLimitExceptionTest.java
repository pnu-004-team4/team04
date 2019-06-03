package com.team04.musiccloud.utilities.structure;

import static org.junit.Assert.*;

import org.junit.Test;

public class OverSizeLimitExceptionTest {

  @Test(expected = OverSizeLimitException.class)
  public void constructor() {
    throw new OverSizeLimitException();
  }

  @Test
  public void constructorWithMessage() {
    try {
      throw new OverSizeLimitException("Test");
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
        throw new OverSizeLimitException(e);
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
        throw new OverSizeLimitException("Test", e);
      }

    } catch (Exception e) {
      assertEquals("Test", e.getMessage());
      throw e.getCause();
    }
  }
}
