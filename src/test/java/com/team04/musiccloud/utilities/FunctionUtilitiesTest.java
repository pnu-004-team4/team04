package com.team04.musiccloud.utilities;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class FunctionUtilitiesTest {

  @Test
  public void setOnCondition() {
    List<Character> list = new ArrayList<>();
    FunctionUtilities
        .setOnCondition("Test", str -> str.endsWith("t"), str -> str.charAt(0), list::add);

    assertEquals('T', list.get(0).charValue());
  }
}
