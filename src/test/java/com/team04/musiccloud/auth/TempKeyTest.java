package com.team04.musiccloud.auth;

import static org.junit.Assert.*;

import java.util.logging.Logger;
import org.junit.Test;

public class TempKeyTest {

  private final static Logger logger = Logger.getGlobal();

  @Test
  public void getKey() {
    TempKey tempKey = new TempKey();
    String key = tempKey.getKey(50, false);
    logger.info(key);
    assertEquals(key.length(), 50);
  }
}