package com.lcwd.todo.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;

@RestController
@RequestMapping("/file")
public class FileController {


    Logger logger = LoggerFactory.getLogger(FileController.class);

    @PostMapping("/single")
    public String uploadSingle(@RequestParam("image") MultipartFile file) {

        logger.info("Name: {}", file.getName());
        logger.info("Content Type: {}", file.getContentType());
        logger.info("Original File Name: {}", file.getOriginalFilename());
        logger.info("File Size: {}", file.getSize());

        //Working with Image
//        InputStream inputStream = file.getInputStream();
//
//        FileOutputStream fileOutputStream = new FileOutputStream(data.png);
//        Byte[] data = new byte[inputStream.available()];
//        fileOutputStream.write(data);

        return "File Test";
    }

    @PostMapping("/multiple")
    public String uploadMultiple(@RequestParam("files") MultipartFile[] files) {
        Arrays.stream(files).forEach(file -> {
            logger.info(file.getOriginalFilename());
            logger.info(file.getContentType());
            System.out.println("+++++++++++++++++++++++++");

            //call service to upload files : and pass file object
        });
        return "Handling Multiple Files";
    }

    //serving image files in response
    @GetMapping("/serve-image")
    public void serveImageHandler(HttpServletResponse response) {
        //storing image
        try {
            InputStream fileInputStream = new FileInputStream("images/nike.png");
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);

            StreamUtils.copy(fileInputStream, response.getOutputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
