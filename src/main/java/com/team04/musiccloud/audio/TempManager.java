package com.team04.musiccloud.audio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TempManager {
    public static final Path TEMP_DIRECTORY = Paths.get(
            StaticPaths.system.toString(), "TEMP_DIRECTORY"
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
        
        final Path userTemp = getUserTemp(fileAudio.getUser());
        Files.copy(fileAudio.getFullPath(), userTemp);
    }
    
    public void createUserTemp(String user) throws UserTempAlreadyExists {
        final Path userTemp = TEMP_DIRECTORY.resolve(user);
        
        if ( Files.notExists(userTemp) ) {
            userTemp.toFile().mkdir();
        } else {
            throw new UserTempAlreadyExists("User's TEMP_DIRECTORY space already exists: " + userTemp.toString());
        }
    }
}
