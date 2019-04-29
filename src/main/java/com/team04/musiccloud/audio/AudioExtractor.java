package com.team04.musiccloud.audio;

import com.team04.musiccloud.utilities.DateTimeUtilities;
import com.team04.musiccloud.utilities.FileSystemUtilities;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;

public abstract class AudioExtractor {
    public Audio getAudio(MultipartFile multipartFile, String user) throws IOException, ExtractorException {
        final byte[] fileBytes = multipartFile.getBytes();
        final AudioMeta audioMeta = getAudioMeta(fileBytes);
        final FileMeta fileMeta = getFileMeta(multipartFile.getOriginalFilename(), user);
        
        return new Audio(audioMeta, fileMeta, fileBytes);
    }
    
    private FileMeta getFileMeta(String filename, String user) throws ExtractorException {
        return new FileMeta(getFileDirectory(user), getFileName(filename), getFileExtension(filename), user);
    }
    
    protected String getFileDirectory(String user) {
        return Paths.get(StaticPaths.storage.toString(), user).toString();
    }
    
    protected String getFileName(String fullFilename) throws ExtractorException {
        return FileSystemUtilities.getName(fullFilename).orElseThrow(ExtractorException::new);
    }
    
    protected String getFileExtension(String filename) throws ExtractorException {
        return FileSystemUtilities.getExtension(filename).orElseThrow(ExtractorException::new);
    }
    
    private AudioMeta getAudioMeta(byte[] bytes) throws ExtractorException {
        final LocalDateTime localDateTime =
                DateTimeUtilities.getLocalDateTime(getAudioReleaseDate(bytes)).orElse(null);
        
        return AudioMetaBuilder.builder()
                .setTitle(getAudioTitle(bytes))
                .setAuthor(getAudioAuthor(bytes))
                .setAlbum(getAudioAlbum(bytes))
                .setReleaseDate(localDateTime)
                .build();
    }
    
    protected abstract String getAudioAlbum(byte[] bytes) throws ExtractorException;
    
    protected abstract String getAudioAuthor(byte[] bytes) throws ExtractorException;
    
    protected abstract String getAudioTitle(byte[] bytes) throws ExtractorException;
    
    protected abstract String getAudioReleaseDate(byte[] bytes) throws ExtractorException;
    
}
