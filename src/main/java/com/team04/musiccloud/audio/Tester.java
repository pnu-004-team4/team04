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
    
    
    // ------------ load tester
    
    public static void testLoader(String mongoId) throws IOException {
        FileMeta fileMeta = getAudioFromDB(mongoId);
        TempManager tempManager = new TempManager();

        if ( !tempManager.exists(fileMeta) ) {
            tempManager.loadFrom(fileMeta);
        }
        
        final String userTempDirectory = tempManager.getUserTemp(fileMeta.getUser()).toString();
        fileMeta.setDirectory(userTempDirectory);
        FileSystemUtilities.updateModifiedDate(fileMeta.getFullPathAsFile());
        sendToTranscoding(fileMeta);
    }
    
    /*
     * @param mongoId Used as a test parameter
     */
    private static FileMeta getAudioFromDB(String mongoId) {
        final Path path = Paths.get(StaticPaths.storage.toString(), "test");
        return new FileMeta(path.toString(), "sample", "mp3", "test");
    }

    private static void sendToTranscoding(FileMeta fileMeta) {
        // just a test method
    }
    
    
    // ------------- upload tester
    
    public static void testUploader()
            throws IOException, ExtractorException, InvalidFileFormat {
        
        MultipartFile multipartFile = getMockMultipartFile();
        upload(multipartFile, "CSK");
    }
    
    public static void upload(MultipartFile multipartFile, String user)
            throws IOException, ExtractorException, InvalidFileFormat {
        
        final AudioExtractor extractor = ExtractorFactory.getInstance(multipartFile);
        Audio audio = extractor.getAudio(multipartFile, user);
        
        saveAudioToStorage(audio);
        saveMetaToDB(audio.getAudioMeta(), audio.getFileMeta());
    }
    
    private static MultipartFile getMockMultipartFile() throws IOException {
        final String fileName = "sample.mp3";
        final Path filePath = testDirectory.resolve("test").resolve(fileName).toAbsolutePath();
        
        return new MockMultipartFile(filePath.toString(), fileName, null, new FileInputStream(filePath.toFile()));
    }
    
    private static void saveAudioToStorage(Audio audio) throws IOException {
        try ( FileOutputStream fileOutputStream = new FileOutputStream(audio.getFileMeta().getFullPathAsFile()) ) {
            fileOutputStream.write(audio.getBytes());
        }
    }
    
    private static void saveMetaToDB(AudioMeta audioMeta, FileMeta fileMeta) {
        System.out.println("--------------------");
        System.out.println("DB received: ");
        System.out.println("\tTitle: " + audioMeta.getTitle());
        System.out.println("\tAuthor: " + audioMeta.getAuthor());
        System.out.println("\tReleaseDate: " + audioMeta.getReleaseDate());
        System.out.println("---");
        System.out.println("\tDirectory: " + fileMeta.getDirectory());
        System.out.println("\tName: " + fileMeta.getName());
        System.out.println("\tExtension: " + fileMeta.getExtension());
        System.out.println("--------------------");
    }

}
