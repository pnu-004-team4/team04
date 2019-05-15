package com.team04.musiccloud.audio;

import com.team04.musiccloud.utilities.StringUtilities;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class FileMeta {
    private String dbId;
    
    private String directory;
    private String name;
    private String extension;
    private String user;
    
    public FileMeta(String dbId, String directory, String name, String extension, String user) {
        this(directory, name, extension, user);
        setDbId(dbId);
    }
    
    public FileMeta(String directory, String name, String extension, String user) {
        setDirectory(directory);
        setName(name);
        setExtension(extension);
        setUser(user);
    }
    
    public FileMeta(FileMeta other) {
        this(other.directory, other.name, other.extension, other.user);
        setDbId(other.dbId);
    }
    
    public boolean isEmpty() {
        return !(hasDbId() || hasDirectory() || hasName() || hasExtension() || hasUser());
    }
    
    public boolean hasDbId() {
        return StringUtilities.hasContentAfterTrim(dbId);
    }
    
    public boolean hasDirectory() {
        return StringUtilities.hasContentAfterTrim(directory);
    }
    
    public boolean hasName() {
        return StringUtilities.hasContentAfterTrim(name);
    }
    
    public boolean hasExtension() {
        return StringUtilities.hasContentAfterTrim(extension);
    }
    
    public boolean hasUser() {
        return StringUtilities.hasContentAfterTrim(user);
    }
    
    public String getDbId() {
        return dbId;
    }
    
    private void setDbId(String dbId) {
        this.dbId = dbId;
    }
    
    public String getDirectory() {
        return directory;
    }
    
    public Path getDirectoryAsPath() {
        return Paths.get(this.getDirectory());
    }
    
    private void setDirectory(String directory) {
        this.directory = directory;
    }
    
    public String getName() {
        return name;
    }
    
    private void setName(String name) {
        this.name = name;
    }
    
    public String getExtension() {
        return extension;
    }
    
    private void setExtension(String extension) {
        this.extension = extension;
    }
    
    public Path getFullPath() {
        if ( directory == null ) throw new IllegalStateException("Directory cannot be null.");
        if ( name == null ) throw new IllegalStateException("File name cannot be null.");
        if ( extension == null ) throw new IllegalStateException("File extension cannot be null.");
    
        return Paths.get(directory, this.getNameExtension()).toAbsolutePath();
    }
    
    public File getFullPathAsFile() {
        return getFullPath().toFile();
    }
    
    public String getNameExtension() {
        return getName() + "." + getExtension();
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
