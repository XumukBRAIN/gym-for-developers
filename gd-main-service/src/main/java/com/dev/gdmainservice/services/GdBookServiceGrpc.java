package com.dev.gdmainservice.services;

import com.dev.gdmainservice.converters.GdBookConverter;
import com.dev.gdmainservice.models.dto.GdBookDto;
import com.dev.grpc.gdstore.book.BookServiceGrpc;
import com.dev.grpc.gdstore.book.GdBookService;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GdBookServiceGrpc {

    /**
     * Получить список всех книг
     *
     * @return Список книг
     */
    public List<GdBookDto> getAllBooks() {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8090")
                .usePlaintext()
                .build();

        BookServiceGrpc.BookServiceBlockingStub stub = BookServiceGrpc.newBlockingStub(channel);

        GdBookService.getAllBooksRq request = GdBookService.getAllBooksRq.newBuilder()
                .build();

        GdBookService.getAllBooksRs allBooks;
        try {
            MDC.put("atTime", String.valueOf(LocalDateTime.now()));
            log.info("Отправлен запрос на получение списка всех книг");
            allBooks = stub.getAllBooks(request);
            return allBooks.getBooksList().stream()
                    .map(GdBookConverter::toDto)
                    .toList();
        } catch (Exception e) {
            log.error("Ошибка при попытке получить список всех книг: {}", e.getMessage());
            throw e;
        } finally {
            MDC.clear();
        }
    }

    /**
     * Получить список книг по топику
     *
     * @param topic Топик
     * @return Список книг
     */
    public List<GdBookDto> getAllBooksByTopic(String topic) {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8090")
                .usePlaintext()
                .build();

        BookServiceGrpc.BookServiceBlockingStub stub = BookServiceGrpc.newBlockingStub(channel);

        GdBookService.getAllBooksByTopicRq request = GdBookService.getAllBooksByTopicRq.newBuilder()
                .setTopic(topic)
                .build();

        try {
            MDC.put("atTime", String.valueOf(LocalDateTime.now()));
            log.info("Отправлен запрос на получение списка книг по топику");
            GdBookService.getAllBooksByTopicRs allBooksByTopic = stub.getAllBooksByTopic(request);
            return allBooksByTopic.getBooksList().stream()
                    .map(GdBookConverter::toDto)
                    .toList();
        } catch (Exception e) {
            log.error("Ошибка при попытке получить список книг по топику {}: {}", topic, e.getMessage());
            throw e;
        }
    }
}
