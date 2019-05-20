package com.team04.musiccloud.audio.extractor;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;

public class ExtractorFactoryTest {
    
    @Test(expected = InvalidFileFormat.class)
    public void invalidFileFormat() throws InvalidFileFormat {
        ExtractorFactory.getInstance(new MockMultipartFile("test file.egg", new byte[]{}));
    }
}