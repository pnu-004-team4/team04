package com.team04.musiccloud.audio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TempManager {
    public static final Path temp = Paths.get(
            StaticPaths.system.toString(), "temp"
    ).toAbsolutePath();
    
    public Path getUserTemp(String user) {
        return Paths.get(temp.toString(), user);
    }
    
    public boolean exists(Audio audio) {
        final Path userTemp = temp.resolve(audio.getOwner());
        return Files.exists(userTemp.resolve(audio.getFileMeta().getNameExtension()));
    }
    
    public void loadFrom(Audio audio) throws IOException {
        createUserTemp(audio.getOwner());
        
        final Path userTemp = getUserTemp(audio.getOwner());
        Files.copy(audio.getFileMeta().getFullPath(), userTemp);
    }
    
    public void createUserTemp(String user) throws UserTempAlreadyExists {
        final Path userTemp = temp.resolve(user);
        
        if ( Files.notExists(userTemp) ) {
            userTemp.toFile().mkdir();
        } else {
            throw new UserTempAlreadyExists("User's temp space already exists: " + userTemp.toString());
        }
    }
}
