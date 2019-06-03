package com.team04.musiccloud.utilities;

import static org.junit.Assert.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;

public class DateTimeUtilitiesTest {

  private String dateTime;

  @Before
  public void setUp() {
    dateTime = LocalDateTime.MIN.toString();
  }

  @Test
  public void isLocalDateTime() {
    assertFalse(DateTimeUtilities.isLocalDateTime("1970-1-1"));
    assertTrue(DateTimeUtilities.isLocalDateTime(dateTime));
  }

  @Test
  public void getLocalDateTime() {
    final Optional<LocalDateTime> localDateTime = DateTimeUtilities.getLocalDateTime(dateTime);
    assertEquals(dateTime, localDateTime.orElseThrow(IllegalStateException::new).toString());
  }

  @Test
  public void getLocalDateTime1() {
    final Optional<LocalDateTime> localDateTime = DateTimeUtilities
        .getLocalDateTime(Date.from(Instant.EPOCH));
    assertEquals("1970-01-01T09:00",
        localDateTime.orElseThrow(IllegalStateException::new).toString());
  }
}
