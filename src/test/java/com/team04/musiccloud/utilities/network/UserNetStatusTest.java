package com.team04.musiccloud.utilities.network;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class UserNetStatusTest {

  private static final String USER = "testCase@testing.com";

  @Test
  public void equalityTest() {
    UserNetStatus netStatus1 = new UserNetStatus(USER);
    UserNetStatus netStatus2 = new UserNetStatus(USER);
    FakeNetStatus fakeNetStatus = new FakeNetStatus();

    assertEquals(netStatus1, netStatus1);
    assertEquals(netStatus1, netStatus2);
    assertNotEquals(null, netStatus1);
    assertNotEquals(netStatus1, fakeNetStatus);

    assertEquals(netStatus1.hashCode(), netStatus2.hashCode());
  }

  @Test
  public void clearAllNetDelays() {
    UserNetStatus netStatus = new UserNetStatus(USER);
    netStatus.addNetDelay(12);
    netStatus.clearAllNetDelays();

    assertEquals(Double.valueOf(0), Double.valueOf(netStatus.getAverageNetDelay()));
  }

  private class FakeNetStatus {

  }
}
