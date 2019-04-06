package com.team04.musiccloud.audio;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;

public abstract class Audio implements Keyable {
    private String title;
    private String author;
    private String album;
    private LocalDateTime releaseDate;
    private String directory;
    private String fileName;
    //-- extra
    private String user;
    private byte[] bytes;
    
    @Override
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    @Override
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public String getAlbum() {
        return album;
    }
    
    public void setAlbum(String album) {
        this.album = album;
    }
    
    @Override
    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }
    
    public void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }
    
    public String getDirectory() {
        return directory;
    }
    
    public void setDirectory(String directory) {
        this.directory = directory;
    }
    
    @Override
    public String getFileName() {
        return fileName;
    }
    
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    public Path getPath() {
        return Paths.get(getDirectory(), getFileName());
    }
    
    public void setPath(Path path) {
        path = path.toAbsolutePath();
        setDirectory(path.getParent().toString());
        setFileName(path.getFileName().toString());
    }
    
    public void setPath(String directory, String fileName) {
        Path path = Paths.get(directory, fileName);
        setPath(path);
    }
    
    public String getUser() {
        return user;
    }
    
    public void setUser(String user) {
        this.user = user;
    }
    
    public byte[] getBytes() {
        return bytes;
    }
    
    public void setBytes(byte[] bytes) {
        this.bytes = Arrays.copyOf(bytes, bytes.length);
    }
}
