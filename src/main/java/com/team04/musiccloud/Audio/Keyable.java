package com.team04.musiccloud.Audio;

import java.time.LocalDateTime;

public interface Keyable {
    String getTitle();
    
    String getAuthor();
    
    LocalDateTime getReleaseDate();
    
    String getFileName();
    
    String getUser();
}
