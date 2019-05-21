package com.team04.musiccloud.audio.extractor;

public class ExtractorException extends Exception {

  public ExtractorException() {
  }

  public ExtractorException(String message) {
    super(message);
  }

  public ExtractorException(String message, Throwable cause) {
    super(message, cause);
  }

  public ExtractorException(Throwable cause) {
    super(cause);
  }
}