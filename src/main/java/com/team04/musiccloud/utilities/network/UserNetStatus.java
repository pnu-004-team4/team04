package com.team04.musiccloud.utilities.network;

import com.team04.musiccloud.utilities.structure.UpdateDeque;
import java.util.Objects;
import java.util.Queue;
import java.util.function.IntPredicate;

public class UserNetStatus {

  private static final int DEFAULT_CAPACITY = 20;

  private String user;
  private Queue<Integer> netDelays;

  public UserNetStatus(String user, int capacity) {
    this.user = user;
    netDelays = new UpdateDeque<>(capacity);
  }

  public UserNetStatus(String user) {
    this(user, DEFAULT_CAPACITY);
  }

  public void addNetDelay(Integer delayTime) {
    netDelays.add(delayTime);
  }

  public void clearAllNetDelays() {
    if (!netDelays.isEmpty()) {
      netDelays.clear();
    }
  }

  public double getAverageNetDelay() {
    return netDelays.stream()
        .mapToInt(Integer::intValue)
        .average()
        .orElse(0);
  }

  public double getAverageNetDelay(IntPredicate intPredicate) {
    return netDelays.stream()
        .mapToInt(Integer::intValue)
        .filter(intPredicate)
        .average()
        .orElse(0);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    UserNetStatus that = (UserNetStatus) o;

    return Objects.equals(user, that.user) &&
        Objects.equals(netDelays, that.netDelays);
  }

  @Override
  public int hashCode() {
    return Objects.hash(user, netDelays);
  }
}
