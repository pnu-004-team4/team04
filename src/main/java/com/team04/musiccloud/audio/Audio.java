package com.team04.musiccloud.audio;

import com.beust.jcommander.ParameterException;
import java.util.Arrays;
import java.util.Objects;

public class Audio {

  private AudioMeta audioMeta;
  private FileMeta fileMeta;
  private byte[] bytes;

  public Audio(AudioMeta audioMeta, FileMeta fileMeta) {
    setAudioMeta(audioMeta);
    setFileMeta(fileMeta);
  }

  public Audio(AudioMeta audioMeta, FileMeta fileMeta, byte[] bytes) {
    setAudioMeta(audioMeta);
    setFileMeta(fileMeta);
    setBytes(bytes);
  }

  public boolean hasAudioMeta() {
    if (audioMeta == null) {
      return false;
    }

    return !audioMeta.isEmpty();
  }

  public boolean hasFileMeta() {
    if (fileMeta == null) {
      return false;
    }

    return !fileMeta.isEmpty();
  }

  public boolean hasBytes() {
    if (bytes == null) {
      return false;
    }

    return bytes.length > 0;
  }

  public AudioMeta getAudioMeta() throws ParameterException {
    if (audioMeta == null) {
      throw new ParameterException("Audio meta doesn't exist!!");
    }
    return audioMeta;
  }

  private void setAudioMeta(AudioMeta audioMeta) {
    this.audioMeta = audioMeta;
  }

  public FileMeta getFileMeta() throws ParameterException {
    if (fileMeta == null) {
      throw new ParameterException("File meta doesn't exist!!");
    }
    return fileMeta;
  }

  private void setFileMeta(FileMeta fileMeta) {
    this.fileMeta = fileMeta;
  }

  public byte[] getBytes() {
    return Arrays.copyOf(bytes, bytes.length);
  }

  public void setBytes(byte[] bytes) {
    try {
      this.bytes = Arrays.copyOf(bytes, bytes.length);
    } catch (NullPointerException e) {
      this.bytes = null;
    }
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    Audio audio = (Audio) obj;

    return Objects.equals(audioMeta, audio.audioMeta) &&
        Objects.equals(fileMeta, audio.fileMeta);
  }

  @Override
  public int hashCode() {
    return Objects.hash(audioMeta, fileMeta);
  }

}
