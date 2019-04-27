package com.team04.musiccloud.audio;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Tester {
    private static Path testDirectory = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "static", "media", "audios");
    
    private Tester() {
    }
    
    public static void testTempManager(String mongoId) {
        Audio audio = getAudioFromDB(mongoId);
        
        TempManager tempManager = new TempManager();
    
        if ( !tempManager.exists(audio) ) {
            try {
                tempManager.loadFrom(audio);
            } catch ( IOException e ) {
                e.printStackTrace();
            }
        }
    
        audio.setPath(tempManager.getUserTemp(audio.getUser()));
        sendToTranscoding(audio);
    }
    
    private static Audio getAudioFromDB(String mongoId) {
        return new Audio() {};
    }
    
    private static void sendToTranscoding(Audio audio) {
    }
    
    public static void test()
            throws IOException, ExtractorException, InvalidFileFormat {
        
        MultipartFile multipartFile = getMockMultipartFile();
        upload(multipartFile, "CSK");
    }
    
    public static void upload(MultipartFile multipartFile, String userName)
            throws IOException, ExtractorException, InvalidFileFormat {
        
        final String originalName = multipartFile.getOriginalFilename();
        final Path userDirectory = testDirectory.resolve(userName);
        final Path filePath = userDirectory.resolve(originalName).toAbsolutePath();
        final AudioExtractor extractor = ExtractorFactory.getInstance(multipartFile);
        
        Audio audio = extractor.convertToAudio(multipartFile);
        audio.setPath(filePath);
        audio.setUser(userName);
        
        
        
        saveAudioToStorage(filePath, audio);
        saveMetaToDB(audio);
    }
    
    private static MultipartFile getMockMultipartFile() throws IOException {
        final String fileName = "sample.mp3";
        final Path filePath = testDirectory.resolve("test").resolve(fileName).toAbsolutePath();
        
        return new MockMultipartFile(filePath.toString(), fileName, null, new FileInputStream(filePath.toFile()));
    }
    
    private static void saveAudioToStorage(Path filePath, Audio audio) throws IOException {
        try ( FileOutputStream fileOutputStream = new FileOutputStream(filePath.toFile()) ) {
            fileOutputStream.write(audio.getBytes());
        }
    }
    
    private static void saveMetaToDB(Audio audio) {
        System.out.println("--------------------");
        System.out.println("DB received: ");
        System.out.println("\tTitle: " + audio.getTitle());
        System.out.println("\tAuthor: " + audio.getAuthor());
        System.out.println("\tReleaseDate: " + audio.getReleaseDate());
        System.out.println("\tFileName: " + audio.getFileName());
        System.out.println("\tUser: " + audio.getUser());
        System.out.println("--------------------");
    }

//    private static void printMeta(InputStream streamInput) throws TikaException, IOException, SAXException {
//        BodyContentHandler contentHandler = new BodyContentHandler();
//        Metadata metadata = new Metadata();
//        ParseContext parseContext = new ParseContext();
//
//        Mp3Parser mp3Parser = new Mp3Parser();
//        mp3Parser.parse(streamInput, contentHandler, metadata, parseContext);
//
//        for ( String name : metadata.names() )
//            System.out.println(name + " [:] " + metadata.get(name));
//    }
}
