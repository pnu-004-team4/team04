package com.team04.musiccloud.audio;

public class FileMetaBuilder {
    private String dbId;
    
    private String directory;
    private String name;
    private String extension;
    private String user;
    
    private FileMetaBuilder() {
    }
    
    private FileMetaBuilder(FileMeta fileMeta) {
        if ( fileMeta.isEmpty() ) return;
        
        if ( fileMeta.hasDbId() ) {
            dbId = fileMeta.getDbId();
        }
        if ( fileMeta.hasDirectory() ) {
            directory = fileMeta.getDirectory();
        }
        if ( fileMeta.hasName() ) {
            name = fileMeta.getName();
        }
        if ( fileMeta.hasExtension() ) {
            extension = fileMeta.getExtension();
        }
        if ( fileMeta.hasName() ) {
            user = fileMeta.getUser();
        }
    }
    
    public static FileMetaBuilder builder() {
        return new FileMetaBuilder();
    }
    
    public static FileMetaBuilder builder(FileMeta fileMeta) {
        return new FileMetaBuilder(fileMeta);
    }
    
    public FileMetaBuilder setDbId(String dbId) {
        this.dbId = dbId;
        return this;
    }
    
    public FileMetaBuilder setDirectory(String directory) {
        this.directory = directory;
        return this;
    }
    
    public FileMetaBuilder setName(String name) {
        this.name = name;
        return this;
    }
    
    public FileMetaBuilder setExtension(String extension) {
        this.extension = extension;
        return this;
    }
    
    public FileMetaBuilder setUser(String user) {
        this.user = user;
        return this;
    }
    
    public FileMeta build() {
        return new FileMeta(dbId, directory, name, extension, user);
    }
}
