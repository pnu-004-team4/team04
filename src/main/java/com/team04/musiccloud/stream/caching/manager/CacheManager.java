package com.team04.musiccloud.stream.caching.manager;

import com.team04.musiccloud.utilities.FileSystemUtilities;
import com.team04.musiccloud.utilities.StaticPaths;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class CacheManager {
    private String user;
    
    public CacheManager(String user) {
        this.user = user;
    }
    
    public Path getUserCachePath() {
        return Paths.get(StaticPaths.tempStorage.toString(), user);
    }
    
    public boolean exists(String fileFullName) {
        final Path fileInCache = this.getUserCachePath().resolve(fileFullName);
        return Files.exists(fileInCache);
    }
    
    public void createCacheOf(Path source) throws IOException {
        createUserTemp();
        Files.copy(source, this.getUserCachePath(), StandardCopyOption.REPLACE_EXISTING);
    }
    
    public boolean createUserTemp() throws UserTempAlreadyExists {
        boolean created = false;
        
        if ( Files.notExists(this.getUserCachePath()) ) {
            created = getUserCachePath().toFile().mkdirs();
        }
        
        return created;
    }
    
    public void updateOrLoad(Path sourceDirectory, String fileFullName) throws IOException {
        if ( this.exists(fileFullName) ) {
            final Path userTemp = getUserCachePath();
            FileSystemUtilities.updateModifiedDate(userTemp);
        } else {
            createCacheOf(sourceDirectory.resolve(fileFullName));
        }
    }
}
