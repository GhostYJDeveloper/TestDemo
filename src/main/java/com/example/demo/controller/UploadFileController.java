package com.example.demo.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;

@Controller
public class UploadFileController {
    @PostMapping(value = "upload")
    public @ResponseBody String uploadFile(MultipartFile file) {
        try{
            FileUtils.writeByteArrayToFile(new File("c:/upload/"+file.getOriginalFilename()),file.getBytes());
            return "Ok";
        }catch (IOException e){
            e.printStackTrace();
            return "wrong";
        }
    }
}
