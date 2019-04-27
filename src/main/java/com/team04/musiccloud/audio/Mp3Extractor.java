package com.team04.musiccloud.audio;

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
import java.util.Optional;

public class Mp3Extractor implements AudioExtractor {
    private static final String XMP_DM = "xmpDM:";
    private static final String TITLE = "title";
    private static final String AUTHOR = "Author";
    private static final String RELEASE_DATE = XMP_DM + "releaseDate";
    private static final String ALBUM = XMP_DM + "album";
    
    @Override
    public Audio convertToAudio(MultipartFile multipartFile) throws ExtractorException {
        AudioMeta audioMeta;
        
        try {
            Metadata metadata = getMetadata(multipartFile);
    
            final LocalDateTime localDateTime =
                    DateTimeUtilities.getLocalDateTime(metadata.get(RELEASE_DATE)).orElse(null);
            final String baseFilename =
                    FileSystemUtilities.getBaseFilename(multipartFile).orElse(null);
            final String extension =
                    FileSystemUtilities.getExtension(multipartFile).orElse(null);
            
            audioMeta = AudioMetaBuilder.builder()
                    .setTitle(metadata.get(TITLE))
                    .setAuthor(metadata.get(AUTHOR))
                    .setAlbum(metadata.get(ALBUM))
                    .setReleaseDate(localDateTime)
                    .setBaseFilename(baseFilename)
                    .setExtension(extension)
                    .build();
            
        } catch ( IOException | TikaException | SAXException e ) {
            throw new ExtractorException(e);
        }
        
        return new Mp3Audio(audioMeta);
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
