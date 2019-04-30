package com.team04.musiccloud.data_structure;

public class OverSizeLimitException extends RuntimeException {
    public OverSizeLimitException() {
    }
    
    public OverSizeLimitException(String message) {
        super(message);
    }
    
    public OverSizeLimitException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public OverSizeLimitException(Throwable cause) {
        super(cause);
    }
}
