package com.dev.gdstoreservice.configs.minio;

import io.minio.BucketExistsArgs;
import io.minio.MinioClient;
import io.minio.messages.Bucket;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Component
@Configuration
@RequiredArgsConstructor
public class MinioClientConfig {

    private final StorageConfig storageConfig;

    private static MinioClient minioClient;

    public static MinioClient getMinioClient() {
        return minioClient;
    }

    @SneakyThrows(Exception.class)
    public static boolean bucketExists(String bucketName) {
        return minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
    }

    @SneakyThrows(Exception.class)
    public static List<Bucket> getAllBuckets() {
        return minioClient.listBuckets();
    }

    @PostConstruct
    public void init() {
        try {
            minioClient = MinioClient.builder()
                    .endpoint("http://192.168.0.105:9000")
                    .credentials(storageConfig.getAccessKey(), storageConfig.getSecretKey())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Initiating Minio Configuration Anomalous: {}", e.getMessage());
        }
    }
}
