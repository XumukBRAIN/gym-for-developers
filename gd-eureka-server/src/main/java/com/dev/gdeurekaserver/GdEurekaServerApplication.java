package com.dev.gdeurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class GdEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GdEurekaServerApplication.class, args);
    }

}
