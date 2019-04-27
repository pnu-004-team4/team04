package com.team04.musiccloud.audio;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

public class ExtractorFactory {
    private ExtractorFactory() {}
    
    public static AudioExtractor getInstance(MultipartFile multipartFile) throws InvalidFileFormat {
        AudioExtractor audioExtractor = null;
        final String extension =
                FileSystemUtilities.getExtension(multipartFile).orElseThrow(InvalidFileFormat::new);
        
        switch ( extension ) {
            case "mp3":
                audioExtractor = new Mp3Extractor();
                break;
            default:
                throw new InvalidFileFormat("No extractor for this extension: " + extension);
        }
        
        return audioExtractor;
    }
}
