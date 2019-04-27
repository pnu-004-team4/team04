package com.team04.musiccloud.audio;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

public class AudioMeta {
    private String title;
    private String author;
    private String album;
    private LocalDateTime releaseDate;
    private String directory;
    private String baseFilename;
    private String extension;
    
    public AudioMeta() {
    }
    
    public AudioMeta(AudioMeta other) {
        setTitle(other.title);
        setAuthor(other.author);
        setAlbum(other.album);
        setReleaseDate(other.releaseDate);
        setDirectory(other.directory);
        setBaseFilename(other.baseFilename);
    }
    
    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        
        AudioMeta audioMeta = (AudioMeta) o;
        
        return Objects.equals(title, audioMeta.title) &&
                Objects.equals(author, audioMeta.author) &&
                Objects.equals(releaseDate, audioMeta.releaseDate) &&
                Objects.equals(baseFilename, audioMeta.baseFilename);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(title, author, releaseDate, baseFilename);
    }
    
    public Optional<String> getTitle() {
        return Optional.ofNullable(title);
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public Optional<String> getAuthor() {
        return Optional.ofNullable(author);
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public Optional<String> getAlbum() {
        return Optional.ofNullable(album);
    }
    
    public void setAlbum(String album) {
        this.album = album;
    }
    
    public Optional<LocalDateTime> getReleaseDate() {
        return Optional.ofNullable(releaseDate);
    }
    
    public void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }
    
    protected Optional<String> getDirectory() {
        return Optional.ofNullable(directory);
    }
    
    protected void setDirectory(String directory) {
        this.directory = directory;
    }
    
    public Optional<String> getBaseFilename() {
        return Optional.ofNullable(baseFilename);
    }
    
    public void setBaseFilename(String baseFilename) {
        this.baseFilename = baseFilename;
    }
    
    public Optional<String> getExtension() {
        return Optional.ofNullable(extension);
    }
    
    public void setExtension(String extension) {
        this.extension = extension;
    }
    
    public Optional<Path> getPath() {
        Optional<Path> path = Optional.empty();
        
        if ( getDirectory().isPresent() && getBaseFilename().isPresent() ) {
            final Path value = Paths.get(getDirectory().get(), getBaseFilename().get());
            path = Optional.of(value);
        }
        
        return path;
    }
    
    public void setPath(Path path) {
        path = path.toAbsolutePath();
        setDirectory(path.getParent().toString());
        setBaseFilename(path.getFileName().toString());
    }
}