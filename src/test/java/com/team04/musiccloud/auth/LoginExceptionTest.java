package com.team04.musiccloud.auth;

import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import org.junit.Test;

public class LoginExceptionTest {

  @Test(expected = RuntimeException.class)
  public void Exception1(){
    throw new LoginException();
  }

  @Test(expected = RuntimeException.class)
  public void Exception2() {
    throw new LoginException("sample");
  }

  @Test(expected = RuntimeException.class)
  public void Exception3() {
    ValueException e = new ValueException("sample");
    throw new LoginException("sample", e);
  }

  @Test(expected = RuntimeException.class)
  public void Exception4() {
    ValueException e = new ValueException("sample");
    throw new LoginException(e);
  }
}