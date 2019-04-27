package com.team04.musiccloud.audio;

import java.time.LocalDateTime;

public interface Tuplable {
    String getTitle();
    
    String getAuthor();
    
    LocalDateTime getReleaseDate();
    
    String getDirectory();
    
    String getFileName();
    
    String getUser();
}
