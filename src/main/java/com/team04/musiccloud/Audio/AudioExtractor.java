package com.team04.musiccloud.Audio;

import org.springframework.web.multipart.MultipartFile;

public abstract class AudioExtractor {
    public abstract Audio convertToAudio(MultipartFile multipartFile) throws ExtractorException;
}
