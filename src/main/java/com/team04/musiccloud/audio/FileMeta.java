package com.team04.musiccloud.audio;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileMeta {
    private String directory;
    private String name;
    private String extension;
    
    public FileMeta(String directory, String name, String extension) {
        setDirectory(directory);
        setName(name);
        setExtension(extension);
    }
    
    public FileMeta(FileMeta other) {
        this(other.directory, other.name, other.extension);
    }
    
    public String getDirectory() {
        return directory;
    }
    
    public void setDirectory(String directory) {
        this.directory = directory;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getExtension() {
        return extension;
    }
    
    public void setExtension(String extension) {
        this.extension = extension;
    }
    
    public Path getFullPath() {
        if ( directory == null ) throw new NullPointerException("Directory cannot be null.");
        if ( name == null ) throw new NullPointerException("File name cannot be null.");
        if ( extension == null ) throw new NullPointerException("File extension cannot be null.");
        
        return Paths.get(directory, name, extension).toAbsolutePath();
    }
    
    public File getFullPathAsFile() {
        return getFullPath().toFile();
    }
    
    public Path getNameExtension() {
        return Paths.get(getName(), getExtension());
    }
}
