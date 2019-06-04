package com.team04.musiccloud.utilities.network;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.function.IntPredicate;
import java.util.stream.IntStream;
import org.junit.Before;
import org.junit.Test;

public class NetStatusManagerTest {

  private NetStatusManager manager;

  @Before
  public void setUp() {
    manager = NetStatusManager.getInstance();
  }

  @Test
  public void getInstanceCall() {
    NetStatusManager manager = NetStatusManager.getInstance();

    assertEquals(this.manager, manager);
  }

  @Test
  public void exist() {
    manager.addUserNetDelay("test", 10);

    assertTrue(manager.exist("test"));
    assertFalse(manager.exist("random"));
  }

  @Test
  public void getUserNetState() {
    assertNotNull(manager.getUserNetState("test"));
  }

  @Test
  public void getUserNetDelayAverage() {
    int[] values = {1, 2, 3, 4, 5, 6, 7, 8};

    for (final int i : values) {
      manager.addUserNetDelay("CSK", i);
    }

    double actual = IntStream.of(values)
        .average()
        .orElseThrow(IllegalStateException::new);

    assertEquals(Double.valueOf(actual), Double.valueOf(manager.getUserNetDelayAverage("CSK")));
  }

  @Test
  public void getUserNetDelayAverage2() {
    int[] values = {1, 2, 3, 4, 5, 6, 7, 8};

    for (final int i : values) {
      manager.addUserNetDelay("CSK2", i);
    }

    IntPredicate lessThan5 = a -> a < 5;
    double actual = IntStream.of(values)
        .filter(lessThan5)
        .average()
        .orElseThrow(IllegalStateException::new);

    assertEquals(Double.valueOf(actual),
        Double.valueOf(manager.getUserNetDelayAverage("CSK2", lessThan5)));
  }
}
