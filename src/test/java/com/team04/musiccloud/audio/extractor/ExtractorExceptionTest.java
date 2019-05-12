package com.team04.musiccloud.audio.extractor;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ExtractorExceptionTest {
    @Test(expected = ExtractorException.class)
    public void noParameter() throws ExtractorException {
        ExtractorException exception = new ExtractorException();
        
        assertNotNull(exception);
        
        throw exception;
    }
    
    @Test
    public void messageParameter() {
        final String message = "This is a exception message.";
        
        try {
            throw new ExtractorException(message);
        } catch ( ExtractorException e ) {
            assertEquals(message, e.getMessage());
        }
    }
    
    @Test
    public void messageWithCauseParameter() {
        final String message = "This is a exception message.";
        
        try {
            throw new ExtractorException(message, new TestException());
        } catch ( ExtractorException e ) {
            assertEquals(message, e.getMessage());
            assertEquals(TestException.class, e.getCause().getClass());
        }
    }
    
    @Test
    public void causeParameter() {
        try {
            throw new ExtractorException(new TestException());
        } catch ( ExtractorException e ) {
            assertEquals(TestException.class, e.getCause().getClass());
        }
    }
    
    private class TestException extends Exception {
    }
}