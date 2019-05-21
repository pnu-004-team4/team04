package com.team04.musiccloud.audio;

import java.time.LocalDateTime;

public class AudioMetaBuilder {

  private String dbId;

  private String title;
  private String author;
  private String album;
  private LocalDateTime releaseDate;

  private AudioMetaBuilder() {
  }

  private AudioMetaBuilder(AudioMeta audioMeta) {
    if (audioMeta.isEmpty()) {
      return;
    }

    if (audioMeta.hasDbId()) {
      dbId = audioMeta.getDbId();
    }
    if (audioMeta.hasTitle()) {
      title = audioMeta.getTitle();
    }
    if (audioMeta.hasAuthor()) {
      author = audioMeta.getAuthor();
    }
    if (audioMeta.hasAlbum()) {
      album = audioMeta.getAlbum();
    }
    if (audioMeta.hasReleaseDate()) {
      releaseDate = audioMeta.getReleaseDate();
    }
  }

  public static AudioMetaBuilder builder() {
    return new AudioMetaBuilder();
  }

  public static AudioMetaBuilder builder(AudioMeta audioMeta) {
    return new AudioMetaBuilder(audioMeta);
  }

  public AudioMetaBuilder setDbId(String dbId) {
    this.dbId = dbId;
    return this;
  }

  public AudioMetaBuilder setTitle(String title) {
    this.title = title;
    return this;
  }

  public AudioMetaBuilder setAuthor(String author) {
    this.author = author;
    return this;
  }

  public AudioMetaBuilder setAlbum(String album) {
    this.album = album;
    return this;
  }

  public AudioMetaBuilder setReleaseDate(LocalDateTime releaseDate) {
    if (releaseDate != null) {
      this.releaseDate = releaseDate;
    } else {
      this.releaseDate = LocalDateTime.MIN;
    }
    return this;
  }

  public AudioMeta build() {
    return new AudioMeta(dbId, title, author, album, releaseDate);
  }
}
