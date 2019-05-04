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
    
    public static AudioMetaBuilder builder() {
        return new AudioMetaBuilder();
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
        this.releaseDate = releaseDate;
        return this;
    }
    
    public AudioMeta build() {
        return new AudioMeta(dbId, title, author, album, releaseDate);
    }
}
