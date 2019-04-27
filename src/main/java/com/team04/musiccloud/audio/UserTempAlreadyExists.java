package com.team04.musiccloud.audio;

public class UserTempAlreadyExists extends RuntimeException {
    public UserTempAlreadyExists() {
    }
    
    public UserTempAlreadyExists(String message) {
        super(message);
    }
    
    public UserTempAlreadyExists(String message, Throwable cause) {
        super(message, cause);
    }
    
    public UserTempAlreadyExists(Throwable cause) {
        super(cause);
    }
}
