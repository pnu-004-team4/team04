package com.team04.musiccloud.audio;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;

public abstract class AudioExtractor {
    public Audio getAudio(MultipartFile multipartFile, String owner) throws IOException, ExtractorException {
        final byte[] fileBytes = multipartFile.getBytes();
        final AudioMeta audioMeta = getAudioMeta(fileBytes);
        final FileMeta fileMeta = getFileMeta(multipartFile.getOriginalFilename(), owner);
        
        return new Audio(audioMeta, fileMeta, fileBytes, owner);
    }
    
    private FileMeta getFileMeta(String filename, String owner) throws ExtractorException {
        return new FileMeta(getFileDirectory(owner), getFileName(filename), getFileExtension(filename));
    }
    
    protected String getFileDirectory(String owner) {
        return Paths.get(StaticPaths.storage.toString(), owner).toString();
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
