package com.team04.musiccloud.audio;

import org.springframework.data.annotation.Id;

import java.util.Arrays;
import java.util.Objects;

public class Audio {
    @Id
    private String _id;
    private AudioMeta audioMeta;
    private FileMeta fileMeta;
    private byte[] bytes;
    private String owner;
    
    public Audio(AudioMeta audioMeta, FileMeta fileMeta, byte[] bytes, String owner) {
        audioMeta.setId(_id);
        setAudioMeta(audioMeta);
        fileMeta.setId(_id);
        setFileMeta(fileMeta);
        setBytes(bytes);
        setOwner(owner);
    }
    
    public AudioMeta getAudioMeta() {
        return audioMeta;
    }
    
    private void setAudioMeta(AudioMeta audioMeta) {
        this.audioMeta = new AudioMeta(audioMeta);
    }
    
    public FileMeta getFileMeta() {
        return fileMeta;
    }
    
    private void setFileMeta(FileMeta fileMeta) {
        this.fileMeta = new FileMeta(fileMeta);
    }
    
    public byte[] getBytes() {
        return Arrays.copyOf(bytes, bytes.length);
    }
    
    private void setBytes(byte[] bytes) {
        this.bytes = Arrays.copyOf(bytes, bytes.length);
    }
    
    public String getOwner() {
        return owner;
    }
    
    private void setOwner(String owner) {
        this.owner = owner;
    }
    
    @Override
    public boolean equals(Object obj) {
        if ( this == obj ) return true;
        if ( obj == null || getClass() != obj.getClass() ) return false;
        
        Audio audio = (Audio) obj;
        
        return Objects.equals(audioMeta, audio.audioMeta) &&
                Objects.equals(fileMeta, audio.fileMeta) &&
                Objects.equals(owner, audio.owner);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(audioMeta, fileMeta, owner);
    }
    
}
