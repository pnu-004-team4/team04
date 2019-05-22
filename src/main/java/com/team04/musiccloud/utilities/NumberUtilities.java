package com.team04.musiccloud.utilities;

import java.util.logging.Logger;

public class NumberUtilities {

  private NumberUtilities() {
  }

  public static double parseDoubleOrZero(String doubleString) {
    double parsedValue = 0.0;

    try {
      parsedValue = Double.parseDouble(doubleString);
    } catch (NumberFormatException e) {
      Logger.getGlobal().info("String cannot be parsed to Double (set to 0): " + doubleString);
    }

    return parsedValue;
  }
}
