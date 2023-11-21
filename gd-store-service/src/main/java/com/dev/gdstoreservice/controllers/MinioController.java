package com.dev.gdstoreservice.controllers;

import com.dev.gdstoreservice.configs.minio.MinioClientConfig;
import com.dev.gdstoreservice.minio.MinioUtil;
import io.minio.MinioClient;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/minio")
public class MinioController {

    MinioUtil minioUtil = new MinioUtil();

    @PostMapping("/uploadFile")
    public void uploadFile(@RequestParam("file") MultipartFile file) {
        MinioClient minioClient = MinioClientConfig.getMinioClient();
        if (minioClient == null) {
            return;
        }

        minioUtil.minioUpload(file, file.getOriginalFilename(), "gd-store");
    }

    @RequestMapping("/getRedFile")
    public String getRedFile(@RequestBody String fileName) {
        MinioClient minioClient = MinioClientConfig.getMinioClient();
        if (minioClient == null) {
            return "Minio client is null";
        }

        return minioUtil.getPreviewFileUrl("gd-store", fileName);
    }

    @RequestMapping("/downloadFile")
    public String downloadFile(@RequestParam String fileName, HttpServletResponse response) {
        MinioClient minioClient = MinioClientConfig.getMinioClient();
        if (minioClient == null) {
            return "Fail to connect to MinIO server";
        }
        MinioUtil.downloadFile("gd-store", fileName, response);
        return "Ok";
    }

//    @PostMapping("/deleteFile")
//    public String deleteFile(String fileName) {
//        MinioClient minioClient = MinioClientConfig.getMinioClient();
//        if (minioClient == null) {
//            return  "Fail to connect to MinIO server";
//        }
//
//        boolean flag = minioUtil.deleteFile("gd-store",fileName);
//        return flag ? "Delete successful": "Delete failed";
//    }
}
