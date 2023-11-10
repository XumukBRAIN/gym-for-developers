package com.dev.gdstoreservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GdStoreServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GdStoreServiceApplication.class, args);
    }

}
