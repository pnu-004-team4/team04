package com.team04.musiccloud.utilities;

public class MusicFileUtilities {

  public static String getMimeType(String fileExtension) {
    if ("mp3".equalsIgnoreCase(fileExtension)) {
      return "mpeg";
    }
    return null;
  }
}
