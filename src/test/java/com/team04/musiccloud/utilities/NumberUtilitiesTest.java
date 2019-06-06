package com.team04.musiccloud.utilities;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NumberUtilitiesTest {

  @Test
  public void parseDoubleOrZero() {
    assertEquals(Double.valueOf(123.456),
        Double.valueOf(NumberUtilities.parseDoubleOrZero("123.456")));
  }

  @Test
  public void parseString() {
    assertEquals(0, (int) NumberUtilities.parseDoubleOrZero("adfs"));
  }
}
