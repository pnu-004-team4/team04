package com.team04.musiccloud.audio;

import org.springframework.web.multipart.MultipartFile;

public interface AudioExtractable {
    Audio convertToAudio(MultipartFile multipartFile) throws ExtractorException;
}
