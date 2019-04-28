package com.team04.musiccloud.audio;

import org.springframework.data.annotation.Id;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class FileMeta {
    @Id
    private String dbId;
    
    private String directory;
    private String name;
    private String extension;
    private String user;
    
    public FileMeta(String directory, String name, String extension, String user) {
        setDirectory(directory);
        setName(name);
        setExtension(extension);
        setUser(user);
    }
    
    public FileMeta(FileMeta other) {
        this(other.directory, other.name, other.extension, other.user);
    }
    
    public String getDbId() {
        return dbId;
    }
    
    protected void setDbId(String dbId) {
        this.dbId = dbId;
    }
    
    public String getDirectory() {
        return directory;
    }
    
    protected void setDirectory(String directory) {
        this.directory = directory;
    }
    
    public String getName() {
        return name;
    }
    
    protected void setName(String name) {
        this.name = name;
    }
    
    public String getExtension() {
        return extension;
    }
    
    protected void setExtension(String extension) {
        this.extension = extension;
    }
    
    public Path getFullPath() {
        if ( directory == null ) throw new NullPointerException("Directory cannot be null.");
        if ( name == null ) throw new NullPointerException("File name cannot be null.");
        if ( extension == null ) throw new NullPointerException("File extension cannot be null.");
        
        return Paths.get(directory, name + "." + extension).toAbsolutePath();
    }
    
    public File getFullPathAsFile() {
        return getFullPath().toFile();
    }
    
    public Path getNameExtension() {
        return Paths.get(getName(), getExtension());
    }
    
    public String getUser() {
        return user;
    }
    
    private void setUser(String user) {
        this.user = user;
    }
    
    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        
        FileMeta fileMeta = (FileMeta) o;
        
        return Objects.equals(directory, fileMeta.directory) &&
                Objects.equals(name, fileMeta.name) &&
                Objects.equals(extension, fileMeta.extension);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(directory, name, extension);
    }
}
