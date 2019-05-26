package com.team04.musiccloud.controller;

import com.team04.musiccloud.audio.AudioHandler;
import com.team04.musiccloud.audio.extractor.ExtractorException;
import com.team04.musiccloud.audio.extractor.InvalidFileFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class UploadController {

    @PostMapping("/upload/{useremail:.+}")
    @ResponseBody
    public ResponseEntity<?> uploadFile(
            @RequestParam("file") MultipartFile uploadFile, @PathVariable String useremail) throws ExtractorException, InvalidFileFormat {

        System.out.println(useremail + "is successfully called!");
        if (uploadFile.isEmpty()) {
            return new ResponseEntity("Please select a File!", HttpStatus.OK);
        }

        try {
            AudioHandler audioHandler = new AudioHandler(useremail);
            audioHandler.requestUpload(uploadFile);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Successfully uploaded - " +
                uploadFile.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK);
    }
}