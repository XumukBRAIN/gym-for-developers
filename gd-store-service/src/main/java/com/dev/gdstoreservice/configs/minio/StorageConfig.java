package com.dev.gdstoreservice.configs.minio;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "s3")
public class StorageConfig {
    private String url;
    private String accessKey;
    private String secretKey;
}
