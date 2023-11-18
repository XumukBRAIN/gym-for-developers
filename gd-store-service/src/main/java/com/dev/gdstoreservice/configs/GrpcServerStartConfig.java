package com.dev.gdstoreservice.configs;

import com.dev.gdstoreservice.services.GdBookServiceGrpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Slf4j
@Configuration
public class GrpcServerStartConfig {

    private GdBookServiceGrpc bookService;

    @Autowired
    public void setBookService(GdBookServiceGrpc bookService) {
        this.bookService = bookService;
    }

    @Bean
    public void startGrpcService() {
        Thread thread = new Thread(() -> {
            Server server = ServerBuilder.forPort(8090)
                    .addService(bookService)
                    .build();
            try {
                server.start();
                server.awaitTermination();
                log.info("gRPC-сервер запущен успешно");
            } catch (IOException | InterruptedException e) {
                log.error("Ошибка в ходе запуска gRPC-сервера: {}", e.getMessage());
                throw new RuntimeException(e);
            }
        });

        thread.start();
    }
}
