package com.team04.musiccloud.audio;

public class InvalidFileFormat extends Exception {
    public InvalidFileFormat() {
    }
    
    public InvalidFileFormat(String message) {
        super(message);
    }
    
    public InvalidFileFormat(String message, Throwable cause) {
        super(message, cause);
    }
    
    public InvalidFileFormat(Throwable cause) {
        super(cause);
    }
}
