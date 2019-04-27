package com.team04.musiccloud.audio;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public abstract class Audio {
    private AudioMeta audioMeta = new AudioMeta();
    private byte[] bytes;
    private String user;
    
    public Audio() {
    }
    
    public Audio(Audio other) {
        setAudioMeta(other.audioMeta);
        setBytes(other.bytes);
        setUser(other.user);
    }
    
    public Audio(AudioMeta audioMeta) {
        setAudioMeta(audioMeta);
    }
    
    @Override
    public boolean equals(Object obj) {
        if ( this == obj ) return true;
        if ( obj == null || getClass() != obj.getClass() ) return false;
        
        Audio audio = (Audio) obj;
        
        return Objects.equals(audioMeta, audio.audioMeta)
                && Objects.equals(user, audio.user);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(audioMeta, user);
    }
    
    public AudioMeta getAudioMeta() {
        return new AudioMeta(audioMeta);
    }
    
    protected void setAudioMeta(AudioMeta audioMeta) {
        this.audioMeta = new AudioMeta(audioMeta);
    }
    
    public Optional<String> getTitle() {
        return audioMeta.getTitle();
    }
    
    public void setTitle(String title) {
        audioMeta.setTitle(title);
    }
    
    public Optional<String> getAuthor() {
        return audioMeta.getAuthor();
    }
    
    public void setAuthor(String author) {
        audioMeta.setAuthor(author);
    }
    
    public Optional<String> getAlbum() {
        return audioMeta.getAlbum();
    }
    
    public void setAlbum(String album) {
        audioMeta.setAlbum(album);
    }
    
    public Optional<LocalDateTime> getReleaseDate() {
        return audioMeta.getReleaseDate();
    }
    
    public void setReleaseDate(LocalDateTime releaseDate) {
        audioMeta.setReleaseDate(releaseDate);
    }
    
    public Optional<String> getDirectory() {
        return audioMeta.getDirectory();
    }
    
    public void setDirectory(String directory) {
        audioMeta.setDirectory(directory);
    }
    
    public Optional<String> getFileName() {
        return audioMeta.getBaseFilename();
    }
    
    public void setFileName(String fileName) {
        audioMeta.setBaseFilename(fileName);
    }
    
    public Optional<Path> getPath() {
        return audioMeta.getPath();
    }
    
    public void setPath(Path path) {
        audioMeta.setPath(path);
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
        if ( bytes.length > 0 ) {
            this.bytes = Arrays.copyOf(bytes, bytes.length);
        }
    }
}
