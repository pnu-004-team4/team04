package com.team04.musiccloud.Audio;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Mp3Extractor extends AudioExtractor {
    private static final String XMP_DM = "xmpDM:";
    private static final String TITLE = "title";
    private static final String AUTHOR = "Author";
    private static final String RELEASE_DATE = XMP_DM + "releaseDate";
    private static final String ALBUM = XMP_DM + "album";
    
    @Override
    public Audio convertToAudio(MultipartFile multipartFile) throws ExtractorException {
        Mp3Audio audio = new Mp3Audio();
    
        try {
            Metadata metadata = getMetadata(multipartFile);
        
            audio.setTitle(metadata.get(TITLE));
            audio.setAuthor(metadata.get(AUTHOR));
            audio.setAlbum(metadata.get(ALBUM));
            if ( isLocalDateTime(metadata.get(RELEASE_DATE)) )
                audio.setReleaseDate(LocalDateTime.parse(metadata.get(RELEASE_DATE)));
            audio.setFileName(multipartFile.getOriginalFilename());
            audio.setBytes(multipartFile.getBytes());
        
        } catch ( IOException | TikaException | SAXException e ) {
            throw new ExtractorException(e);
        }
    
        return audio;
    }
    
    
    private boolean isLocalDateTime(String dateString) {
        boolean isDateTime = true;
        
        try {
            LocalDateTime.parse(dateString);
        } catch ( DateTimeParseException e ) {
            isDateTime = false;
        }
        
        return isDateTime;
    }
    
    private Metadata getMetadata(MultipartFile multipartFile) throws TikaException, SAXException, IOException {
        InputStream inputStream = new ByteArrayInputStream(multipartFile.getBytes());
        BodyContentHandler contentHandler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        ParseContext parseContext = new ParseContext();
        
        new Mp3Parser().parse(inputStream, contentHandler, metadata, parseContext);
        return metadata;
    }
}
