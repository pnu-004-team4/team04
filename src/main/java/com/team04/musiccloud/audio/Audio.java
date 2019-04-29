package com.team04.musiccloud.audio;

import java.util.Arrays;
import java.util.Objects;

public class Audio {
    private AudioMeta audioMeta;
    private FileMeta fileMeta;
    private byte[] bytes;
    
    public Audio(AudioMeta audioMeta, FileMeta fileMeta, byte[] bytes) {
        setAudioMeta(audioMeta);
        setFileMeta(fileMeta);
        setBytes(bytes);
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
    
    @Override
    public boolean equals(Object obj) {
        if ( this == obj ) return true;
        if ( obj == null || getClass() != obj.getClass() ) return false;
        
        Audio audio = (Audio) obj;
        
        return Objects.equals(audioMeta, audio.audioMeta) &&
                Objects.equals(fileMeta, audio.fileMeta) &&
                Arrays.equals(bytes, audio.bytes);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(audioMeta, fileMeta, bytes);
    }
    
}
