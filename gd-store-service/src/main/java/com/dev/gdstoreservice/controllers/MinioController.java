package com.dev.gdstoreservice.controllers;

import com.dev.gdstoreservice.minio.MinioUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@RestController
@RequestMapping("/minio")
public class MinioController {
    private MinioUtil minioUtil;

    @Autowired
    private void setMinioUtil(MinioUtil minioUtil) {
        this.minioUtil = minioUtil;
    }

    @PostMapping("/uploadFile")
    public ResponseEntity<Integer> uploadFile(@RequestParam("file") MultipartFile file) {
        minioUtil.uploadFile(file, "gd-store");
        return ResponseEntity.ok(HttpStatus.OK.value());
    }

    @GetMapping("/getFile")
    public ResponseEntity<String> getFile(@RequestParam String filename) {
        return ResponseEntity.ok(minioUtil.getPreviewFileUrl("gd-store", filename));
    }

    @PostMapping("/downloadFile")
    public InputStream downloadFile(@RequestParam String filename, HttpServletResponse response) {
        return minioUtil.downloadFile("gd-store", filename, response);
    }

    @DeleteMapping("/deleteFile")
    public ResponseEntity<Integer> deleteFile(@RequestParam("filename") String filename) {
        minioUtil.deleteFile("gd-store", filename);
        return ResponseEntity.ok(HttpStatus.OK.value());
    }
}
