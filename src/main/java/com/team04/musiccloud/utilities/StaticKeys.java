package com.team04.musiccloud.utilities;

public class StaticKeys {

  private static String keys;
  private static String dbName;
  private static final String emailUser = "musiccloudtest@gmail.com";
  private static final String emailAccessCode = "music123!@#";

  public static String getKeys() {
    return keys;
  }

  public static void setKeys(String keys) {
    StaticKeys.keys = keys;
  }

  public static String getDbName() {
    return dbName;
  }

  public static void setDbName(String dbName) {
    StaticKeys.dbName = dbName;
  }

  public static String getEmailUser() {
    return emailUser;
  }

  public static String getEmailAccessCode() {
    return emailAccessCode;
  }
}
