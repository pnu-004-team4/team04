package com.team04.musiccloud.controller;

import com.team04.musiccloud.audio.AudioHandler;
import com.team04.musiccloud.audio.extractor.ExtractorException;
import com.team04.musiccloud.audio.extractor.InvalidFileFormat;
import com.team04.musiccloud.auth.Account;
import com.team04.musiccloud.utilities.AccountRepositoryUtil;
import java.util.logging.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class UploadController {
    private final static Logger logger = Logger.getGlobal();

    @PostMapping("/upload/{useremail:.+}")
    @ResponseBody
    public ResponseEntity<?> uploadFile(
            @RequestParam("file") MultipartFile uploadFile, @PathVariable String useremail) throws ExtractorException, InvalidFileFormat {

        logger.info(useremail + "is successfully called!");
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

    @PostMapping("/delete/{deletedbID}")
    @ResponseBody
    public ResponseEntity<?> uploadFile(
            @PathVariable String deletedbID) {

        AccountRepositoryUtil accountRepositoryUtil = AccountRepositoryUtil.getInstance();
        Account account = accountRepositoryUtil.getCurrentAccount();

        try {
            AudioHandler audioHandler = new AudioHandler(account.getEmail());
            audioHandler.requestDelete(deletedbID);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Successfully Deleted", new HttpHeaders(), HttpStatus.OK);
    }


}