package com.lcwd.todo.Todo_Application.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
public class FileController {

    Logger logger= LoggerFactory.getLogger(FileController.class);

    @PostMapping("/single")
    public ResponseEntity<String> uploadSingleFileHandler(@RequestParam("image") MultipartFile file)
    {
        logger.info("file size"+file.getSize());
        logger.info("file name"+file.getName());
        logger.info("file content"+file.getContentType());
       return new ResponseEntity<>("file tested", HttpStatus.OK);
    }

    @PostMapping("/multi")
    public ResponseEntity<String> uploadMultipleFileHandler(@RequestParam("images") MultipartFile[] files)
    {
        for(MultipartFile file:files)
        {
            logger.info(file.getName()+"  "+file.getSize());

        }
        return new ResponseEntity<>("multiple file tested", HttpStatus.OK);
    }
}
