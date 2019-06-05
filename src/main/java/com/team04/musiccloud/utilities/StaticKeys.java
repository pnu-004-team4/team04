package com.team04.musiccloud.utilities;

public class StaticKeys {

  private static String keys;
  private static String dbName;

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
}
