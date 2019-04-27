package com.team04.musiccloud.audio;

import java.nio.file.Path;
import java.time.LocalDateTime;

public interface MetaReadOnly {
    String getTitle();
    
    String getAuthor();
    
    String getAlbum();
    
    LocalDateTime getReleaseDate();
    
    String getDirectory();
    
    String getFileName();
    
    Path getPath();
}
