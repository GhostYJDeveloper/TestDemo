package com.example.demo.controller;

import com.example.demo.model.uploadFile.UploadFile;
import com.example.demo.property.FileProperties;
import com.example.demo.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/file")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);
    private final Path fileStorageLocation; // 文件在本地存储的地址
    @Autowired
    private FileService fileService;

    public FileController(FileProperties fileProperties) {
        this.fileStorageLocation = Paths.get(fileProperties.getUploadDir()).toAbsolutePath().normalize();
    }

    @RequestMapping(value = "gotoUpload")
    public ModelAndView gotoUpload() {
        return new ModelAndView("file/fileIndex");
    }

    @PostMapping("/uploadFile")
    public UploadFile uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = fileService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/file/downloadFile").toUriString() + "?fileName=" + fileName;

        Path targetLocation = fileStorageLocation.resolve(fileName);
        //C:/TestDemo/src/main/resources/static/uploads/测试.png
        String originalPath = targetLocation.toString();

        String[] originalPathList = originalPath.split("\\\\");
        int length = originalPathList.length;
        //uploads/测试.png
        String savePath = originalPathList[length - 2] + "/" + originalPathList[length - 1];

        return new UploadFile("User", 1L, fileName, fileDownloadUri,
                file.getContentType(), file.getSize(), "HeadPhoto", savePath, originalPath);
    }

    @PostMapping("/uploadMultipleFiles")
    public List<UploadFile> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.stream(files).map(this::uploadFile).collect(Collectors.toList());
    }

    @GetMapping("/downloadFile")
    public ResponseEntity<Resource> downloadFile(@RequestParam("fileName") String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}