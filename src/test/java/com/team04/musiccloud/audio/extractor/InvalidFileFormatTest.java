package com.team04.musiccloud.audio.extractor;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class InvalidFileFormatTest {
    @Test(expected = InvalidFileFormat.class)
    public void noParameter() throws InvalidFileFormat {
        InvalidFileFormat exception = new InvalidFileFormat();
        
        assertNotNull(exception);
        
        throw exception;
    }
    
    @Test
    public void messageParameter() {
        final String message = "This is a exception message.";
        
        try {
            throw new InvalidFileFormat(message);
        } catch ( InvalidFileFormat e ) {
            assertEquals(message, e.getMessage());
        }
    }
    
    @Test
    public void messageWithCauseParameter() {
        final String message = "This is a exception message.";
        
        try {
            throw new InvalidFileFormat(message, new TestException());
        } catch ( InvalidFileFormat e ) {
            assertEquals(message, e.getMessage());
            assertEquals(TestException.class, e.getCause().getClass());
        }
    }
    
    @Test
    public void CauseParameter() {
        try {
            throw new InvalidFileFormat(new TestException());
        } catch ( InvalidFileFormat e ) {
            assertEquals(TestException.class, e.getCause().getClass());
        }
    }
    
    private class TestException extends Exception {
    }
}