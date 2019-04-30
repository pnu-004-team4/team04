package com.team04.musiccloud.audio;

import java.time.LocalDateTime;
import java.util.Objects;

public class AudioMeta {
    private String dbId;
    
    private String title;
    private String author;
    private String album;
    private LocalDateTime releaseDate;
    
    public AudioMeta(String dbId, String title, String author, String album, LocalDateTime releaseDate) {
        this(title, author, album, releaseDate);
        setDbId(dbId);
    }
    
    public AudioMeta(String title, String author, String album, LocalDateTime releaseDate) {
        setTitle(title);
        setAuthor(author);
        setAlbum(album);
        setReleaseDate(releaseDate);
    }
    
    public AudioMeta(AudioMeta other) {
        this(other.title, other.author, other.album, other.releaseDate);
        setDbId(other.dbId);
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
        return releaseDate;
    }
    
    private void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }
    
    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        
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