package com.dev.gdstoreservice.minio;

import com.dev.gdstoreservice.configs.minio.MinioClientConfig;
import com.dev.gdstoreservice.exceptions.GdRuntimeException;
import io.minio.*;
import io.minio.http.Method;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Slf4j
@Component
public class MinioUtil {

    public void uploadFile(MultipartFile file, String bucketName) {
        MinioClient minioClient = MinioClientConfig.getMinioClient();
        if (minioClient == null) {
            throw new GdRuntimeException("minioUtil.uploadFile.client.null", "MinioClient не доступен");
        }

        String filename = file.getName();
        try {
            InputStream inputStream = file.getInputStream();
            PutObjectArgs objectArgs = PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(file.getOriginalFilename())
                    .stream(inputStream, file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build();

            minioClient.putObject(objectArgs);
        } catch (Exception e) {
            log.error("Ошибка при загрузке файла с названием {}: {}", filename, e.getMessage());
            throw new GdRuntimeException("minioUtil.uploadFile.error", "Ошибка при загрузке файла");
        }
    }

    public InputStream downloadFile(String bucketName, String filename, HttpServletResponse response) {
        MinioClient minioClient = MinioClientConfig.getMinioClient();
        if (minioClient == null) {
            throw new GdRuntimeException("minioUtil.downloadFile.client.null", "MinioClient не доступен");
        }

        if (StringUtils.isBlank(filename)) {
            throw new GdRuntimeException("minioUtil.downloadFile.filename.null", "Отсутствует имя файла");
        }

        try {
            InputStream file = minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(bucketName)
                            .object(filename)
                            .build()
            );

            response.setHeader("Content-Disposition", "attachment;filename=" + filename);
            ServletOutputStream servletOutputStream = response.getOutputStream();

            int len;
            byte[] buffer = new byte[1024];
            while ((len = file.read(buffer)) > 0) {
                servletOutputStream.write(buffer, 0, len);
            }
            servletOutputStream.flush();
            file.close();
            servletOutputStream.close();

            log.info("Файл с названием {} успешно скачен", filename);
            return file;
        } catch (Exception e) {
            log.error("Ошибка при скачивании файла с названием {}: {}", filename, e.getMessage());
            throw new GdRuntimeException("minioUtil.downloadFile.error", "Ошибка при скачивании файла");
        }
    }

    public String getPreviewFileUrl(String bucketName, String filename) {
        MinioClient minioClient = MinioClientConfig.getMinioClient();
        if (minioClient == null) {
            throw new GdRuntimeException("minioUtil.getPreviewFileUrl.client.null", "MinioClient не доступен");
        }

        if (StringUtils.isBlank(filename)) {
            throw new GdRuntimeException("minioUtil.downloadFile.filename.null", "Отсутствует имя файла");
        }

        try {
            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .bucket(bucketName)
                            .object(filename)
                            .method(Method.GET)
                            .build()
            );
        } catch (Exception e) {
            log.error("Ошибка при получении URL файла с названием {}: {}", filename, e.getMessage());
            throw new GdRuntimeException("minioUtil.getPreviewFileUrl.error", "Ошибка при получении URL файла");
        }
    }

    public void deleteFile(String bucketName, String filename) {
        MinioClient minioClient = MinioClientConfig.getMinioClient();
        if (minioClient == null) {
            throw new GdRuntimeException("minioUtil.deleteFile.client.null", "MinioClient не доступен");
        }

        if (StringUtils.isBlank(filename)) {
            throw new GdRuntimeException("minioUtil.downloadFile.filename.null", "Отсутствует имя файла");
        }

        try {
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(bucketName)
                            .object(filename)
                            .build()
            );

            log.info("Файл с названием {} успешно удален", filename);
        } catch (Exception e) {
            log.error("Ошибка при удалении файла с названием {}: {}", filename, e.getMessage());
            throw new GdRuntimeException("minioUtil.deleteFile.error", "Ошибка при удалении файла");
        }
    }
}
