package com.team04.musiccloud.audio.extractor;

import com.team04.musiccloud.audio.Audio;
import com.team04.musiccloud.audio.AudioMeta;
import com.team04.musiccloud.audio.AudioMetaBuilder;
import com.team04.musiccloud.audio.FileMeta;
import com.team04.musiccloud.utilities.DateTimeUtilities;
import com.team04.musiccloud.utilities.FileSystemUtilities;
import com.team04.musiccloud.utilities.StaticPaths;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

public abstract class AudioExtractor {
    
    private Path baseDirectory = StaticPaths.storage;
    
    public Audio getAudio(MultipartFile multipartFile, String user)
            throws IOException, ExtractorException {
        final byte[] fileBytes = multipartFile.getBytes();
        final AudioMeta audioMeta = getAudioMeta(fileBytes);
        final FileMeta fileMeta = getFileMeta(multipartFile.getOriginalFilename(), user);
        
        return new Audio(audioMeta, fileMeta, fileBytes);
    }
    
    private FileMeta getFileMeta(String filename, String user) throws ExtractorException {
        return new FileMeta(getFileDirectory(user), getFileName(filename), getFileExtension(filename),
                user);
    }
    
    public void setBaseDirectory(Path path) {
        baseDirectory = path;
    }
    
    protected String getFileDirectory(String user) {
        return Paths.get(baseDirectory.toString(), user).toString();
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
