package com.dev.gdauthservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GdAuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GdAuthServiceApplication.class, args);
    }

}
