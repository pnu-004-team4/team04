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

public class Mp3Extractor extends AudioExtractor {
    @Override
    public Audio convertToAudio(MultipartFile multipartFile) throws ExtractorException {
        
        Mp3Audio audio;
        
        try {
            InputStream inputStream = new ByteArrayInputStream(multipartFile.getBytes());
            BodyContentHandler contentHandler = new BodyContentHandler();
            Metadata metadata = new Metadata();
            ParseContext parseContext = new ParseContext();
            
            new Mp3Parser().parse(inputStream, contentHandler, metadata, parseContext);
            
            audio = new Mp3Audio();
            audio.setTitle(metadata.get("title"));
            audio.setAuthor(metadata.get("Author"));
            audio.setAlbum(metadata.get("xmpDM:album"));
            audio.setYear(metadata.get("xmpDM:releaseDate"));
            audio.setFileName(multipartFile.getOriginalFilename());
            audio.setBytes(multipartFile.getBytes());
            
        } catch ( IOException | TikaException | SAXException e ) {
            throw new ExtractorException(e);
        }
        
        return audio;
    }
}
