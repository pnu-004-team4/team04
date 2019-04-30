package com.team04.musiccloud.audio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class CacheManager {
    public static final Path TEMP_DIRECTORY = Paths.get(
            StaticPaths.staticResources.toString(), "temp"
    ).toAbsolutePath();
    
    public Path getUserTemp(String user) {
        return Paths.get(TEMP_DIRECTORY.toString(), user);
    }
    
    public boolean exists(FileMeta fileMeta) {
        final Path userTemp = TEMP_DIRECTORY.resolve(fileMeta.getUser());
        return Files.exists(userTemp.resolve(fileMeta.getNameExtension()));
    }
    
    public void loadFrom(FileMeta fileAudio) throws IOException {
        createUserTemp(fileAudio.getUser());
    
        final Path userTemp = getUserTemp(fileAudio.getUser()).resolve(fileAudio.getNameExtension());
    
        Files.copy(fileAudio.getFullPath(), userTemp, StandardCopyOption.REPLACE_EXISTING);
    }
    
    public void createUserTemp(String user) throws UserTempAlreadyExists {
        final Path userTemp = TEMP_DIRECTORY.resolve(user);
    
        if ( Files.notExists(userTemp) ) {
            userTemp.toFile().mkdirs();
        }
    }
}
