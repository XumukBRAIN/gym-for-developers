package com.dev.gdstoreservice.configs.minio;

import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

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
