package com.team04.musiccloud.audio;

import java.time.LocalDateTime;

public class AudioMetaBuilder {
    private String title;
    private String author;
    private String album;
    private LocalDateTime releaseDate;
    private String directory;
    private String baseFilename;
    private String extension;
    
    private AudioMetaBuilder() {
    }
    
    public static AudioMetaBuilder builder() {
        return new AudioMetaBuilder();
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
    
    public AudioMetaBuilder setDirectory(String directory) {
        this.directory = directory;
        return this;
    }
    
    public AudioMetaBuilder setBaseFilename(String baseFilename) {
        this.baseFilename = baseFilename;
        return this;
    }
    
    public AudioMetaBuilder setExtension(String extension) {
        this.extension = extension;
        return this;
    }
    
    public AudioMeta build() {
        AudioMeta audioMeta = new AudioMeta();
        
        if ( title != null ) audioMeta.setTitle(title);
        if ( author != null ) audioMeta.setAuthor(author);
        if ( album != null ) audioMeta.setAuthor(album);
        if ( releaseDate != null ) audioMeta.setReleaseDate(releaseDate);
        if ( directory != null ) audioMeta.setDirectory(directory);
        if ( baseFilename != null ) audioMeta.setBaseFilename(baseFilename);
        if ( extension != null ) audioMeta.setExtension(extension);
        
        return audioMeta;
    }
}
