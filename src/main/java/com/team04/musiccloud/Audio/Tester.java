package com.team04.musiccloud.Audio;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Tester {
    private static Path testDirectory = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "media", "audios");
    
    public static void test() {
        try {
            MultipartFile multipartFile = getMockMultipartFile();
            upload(multipartFile, "CSK");
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }
    
    private static void upload(MultipartFile multipartFile, String userName) {
        try {
            final String originalName = multipartFile.getOriginalFilename();
            final Path userDirectory = testDirectory.resolve(userName);
            final Path filePath = userDirectory.resolve(originalName).toAbsolutePath();
            final AudioExtractor extractor = new Mp3Extractor();
            
            Audio audio = extractor.convertToAudio(multipartFile);
            audio.setPath(filePath);
            audio.setUser(userName);
            saveAudioToStorage(filePath, audio);
            saveMetaToDB(audio);
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }
    
    private static void saveAudioToStorage(Path filePath, Audio audio) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(filePath.toFile());
        fileOutputStream.write(audio.getBytes());
    }
    
    private static MultipartFile getMockMultipartFile() throws IOException {
        final String fileName = "18 Taylor Swift - Style.mp3";
        final Path filePath = testDirectory.resolve("test").resolve(fileName).toAbsolutePath();
        
        return new MockMultipartFile(filePath.toString(), fileName, null, new FileInputStream(filePath.toFile()));
    }
    
    private static void saveMetaToDB(Keyable keyable) {
        System.out.println("--------------------");
        System.out.println("DB received: ");
        System.out.println("\tTitle: " + keyable.getTitle());
        System.out.println("\tAuthor: " + keyable.getAuthor());
        System.out.println("\tReleaseDate: " + keyable.getReleaseDate());
        System.out.println("\tFileName: " + keyable.getFileName());
        System.out.println("\tUser: " + keyable.getUser());
        System.out.println("--------------------");
    }
    
    private static void printMeta(InputStream streamInput) throws TikaException, IOException, SAXException {
        BodyContentHandler contentHandler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        ParseContext parseContext = new ParseContext();
        
        Mp3Parser mp3Parser = new Mp3Parser();
        mp3Parser.parse(streamInput, contentHandler, metadata, parseContext);
        
        for ( String name : metadata.names() )
            System.out.println(name + " [:] " + metadata.get(name));
    }
}
