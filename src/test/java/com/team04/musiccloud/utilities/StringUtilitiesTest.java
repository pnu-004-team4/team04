package com.team04.musiccloud.utilities;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringUtilitiesTest {

  @Test
  public void hasContent() {
    assertTrue(StringUtilities.hasContent("", a -> a.concat(".")));
  }

  @Test
  public void hasContentAfterTrim() {
    assertFalse(StringUtilities.hasContentAfterTrim("    "));
    assertTrue(StringUtilities.hasContentAfterTrim("   yes  "));
  }
}
