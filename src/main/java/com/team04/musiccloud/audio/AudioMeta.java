package com.team04.musiccloud.audio;

import com.team04.musiccloud.utilities.StringUtilities;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

public class AudioMeta {

  private String dbId;

  private String title;
  private String author;
  private String album;
  private LocalDateTime releaseDate;
  private int durationMs;

  public AudioMeta(String dbId, String title, String author, String album,
      LocalDateTime releaseDate, int durationMs) {
    this(title, author, album, releaseDate, durationMs);
    setDbId(dbId);
  }

  public AudioMeta(String title, String author, String album, LocalDateTime releaseDate,
      int durationMs) {
    setTitle(title);
    setAuthor(author);
    setAlbum(album);
    setReleaseDate(releaseDate);
    setDurationMs(durationMs);
  }

  public AudioMeta(AudioMeta other) {
    this(other.dbId, other.title, other.author, other.album, other.releaseDate, other.durationMs);
  }

  public boolean isEmpty() {
    return !(hasDbId() || hasTitle() || hasAuthor() || hasAlbum() || hasReleaseDate()
        || hasDurationMs());
  }

  public boolean hasDbId() {
    return StringUtilities.hasContentAfterTrim(dbId);
  }

  public boolean hasAuthor() {
    return StringUtilities.hasContentAfterTrim(author);
  }

  public boolean hasTitle() {
    return StringUtilities.hasContentAfterTrim(title);
  }

  public boolean hasAlbum() {
    return StringUtilities.hasContentAfterTrim(album);
  }

  public boolean hasReleaseDate() {
    return releaseDate != null && !releaseDate.isEqual(LocalDateTime.MIN);
  }

  public boolean hasDurationMs() {
    return durationMs != 0;
  }

  public String getDbId() {
    return dbId;
  }

  public void setDbId(String dbId) {
    this.dbId = dbId;
  }

  public String getTitle() {
    return title;
  }

  protected void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  private void setAuthor(String author) {
    this.author = author;
  }

  public String getAlbum() {
    return album;
  }

  private void setAlbum(String album) {
    this.album = album;
  }

  public LocalDateTime getReleaseDate() {
    return Optional.ofNullable(releaseDate).orElse(LocalDateTime.MIN);
  }

  private void setReleaseDate(LocalDateTime releaseDate) {
    this.releaseDate = releaseDate;
  }

  public int getDurationMs() {
    return durationMs;
  }

  private void setDurationMs(int durationMs) {
    this.durationMs = durationMs;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    AudioMeta audioMeta = (AudioMeta) o;

    return Objects.equals(title, audioMeta.title) &&
        Objects.equals(author, audioMeta.author) &&
        Objects.equals(releaseDate, audioMeta.releaseDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, author, releaseDate);
  }
}