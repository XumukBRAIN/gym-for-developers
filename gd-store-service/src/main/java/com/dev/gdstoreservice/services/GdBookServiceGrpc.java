package com.dev.gdstoreservice.services;

import com.dev.gdstoreservice.converters.GdBookConverter;
import com.dev.gdstoreservice.enums.Topic;
import com.dev.gdstoreservice.exceptions.GdRuntimeException;
import com.dev.gdstoreservice.repositories.GdBookRepository;
import com.dev.grpc.gdstore.book.BookServiceGrpc;
import com.dev.grpc.gdstore.book.GdBookService;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class GdBookServiceGrpc extends BookServiceGrpc.BookServiceImplBase {

    private GdBookRepository repository;

    @Autowired
    public void setRepository(GdBookRepository repository) {
        this.repository = repository;
    }

    @Override
    public void getAllBooks(GdBookService.getAllBooksRq request, StreamObserver<GdBookService.getAllBooksRs> responseObserver) {
        try {
            log.info("Запрос принят!");
            List<GdBookService.Book> books = repository.findAll().stream()
                    .map(GdBookConverter::toGrpcObject)
                    .toList();

            if (CollectionUtils.isEmpty(books)) {
                log.warn("В базе данных отсутствуют книги!");
                return;
            }

            GdBookService.getAllBooksRs response = GdBookService.getAllBooksRs.newBuilder()
                    .addAllBooks(books)
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();

            log.info("Ответ отправлен!");
        } catch (Exception e) {
            log.error("Ошибка в работе метода getAllBooks: {}", e.getMessage());
            throw new GdRuntimeException("gdStoreService.services.grpc", "Ошибка в работе метода getAllBooks");
        }
    }

    @Override
    public void getAllBooksByTopic(GdBookService.getAllBooksByTopicRq request, StreamObserver<GdBookService.getAllBooksByTopicRs> responseObserver) {
        String topic = request.getTopic();
        if (StringUtils.isBlank(topic)) {
            throw new GdRuntimeException("gdStoreService.services.grpc", "Не передан топик");
        }

        if (!Topic.contains(topic)) {
            throw new GdRuntimeException("gdStoreService.services.grpc", "Был передан несуществующий топик");
        }

        try {
            MDC.put("atTime", String.valueOf(LocalDateTime.now()));
            log.info("Запрос на получение всех книг с топиком {} принят", topic);
            List<GdBookService.Book> books = repository.getAllByTopic(topic).stream()
                    .map(GdBookConverter::toGrpcObject)
                    .toList();

            if (CollectionUtils.isEmpty(books)) {
                log.warn("В базе данных отсутствуют книги с топиком {}!", topic);
                return;
            }

            GdBookService.getAllBooksByTopicRs response = GdBookService.getAllBooksByTopicRs.newBuilder()
                    .addAllBooks(books)
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();

            log.info("Ответ отправлен!");
        } catch (Exception e) {
            log.error("Ошибка в работе метода getAllByTopic: {}", e.getMessage());
            throw new GdRuntimeException("gdStoreService.services.grpc", "Ошибка в работе метода getAllByTopic");
        } finally {
            MDC.clear();
        }
    }
    //TODO проверить config (Может можно как-то по другому), разобраться с полем id
}
