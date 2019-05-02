package com.team04.musiccloud.auth;

public class invalidEmailException extends RuntimeException{
    public invalidEmailException() {
    }

    public invalidEmailException(String message) {
        super(message);
    }

    public invalidEmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public invalidEmailException(Throwable cause) {
        super(cause);
    }
}
