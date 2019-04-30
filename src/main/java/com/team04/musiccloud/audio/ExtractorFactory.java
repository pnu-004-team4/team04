package com.team04.musiccloud.audio;

import com.team04.musiccloud.utilities.FileSystemUtilities;
import org.springframework.web.multipart.MultipartFile;

public class ExtractorFactory {
    private ExtractorFactory() {
    }
    
    public static AudioExtractor getInstance(MultipartFile multipartFile) throws InvalidFileFormat {
        AudioExtractor audioExtractor;
        final String extension =
                FileSystemUtilities.getExtension(multipartFile).orElseThrow(InvalidFileFormat::new);
    
        if ( extension.equals("mp3") ) {
            audioExtractor = new Mp3Extractor();
        } else {
            throw new InvalidFileFormat("No extractor for this extension: " + extension);
        }
        
        return audioExtractor;
    }
}
