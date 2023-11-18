package com.dev.gdstoreservice.services;

import com.dev.gdstoreservice.exceptions.GdRuntimeException;
import com.dev.gdstoreservice.repositories.GdBookRepository;
import com.dev.grpc.gdstore.book.BookServiceGrpc;
import com.dev.grpc.gdstore.book.GdBookService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(
        readOnly = true,
        propagation = Propagation.REQUIRED
)
public class GdBookServiceGrpc extends BookServiceGrpc.BookServiceImplBase {

    private final GdBookRepository repository;

    @Override
    public void getAllBooks(GdBookService.getAllBooksRq request, StreamObserver<GdBookService.getAllBooksRs> responseObserver) {
        try {
            log.info("Запрос принят!");
            List<GdBookService.Book> books = repository.findAll().stream()
                    .map(book -> GdBookService.Book.newBuilder()
                            .setTitle(book.getTitle())
                            .setAuthor(book.getAuthor())
                            .setTopic(book.getTopic())
                            .setPrice(book.getPrice().doubleValue())
                            .setPages(book.getPages())
                            .setCreationDate(String.valueOf(book.getCreationDate()))
                            .build())
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

        try {
            log.info("Запрос принят!");
            List<GdBookService.Book> books = repository.getAllByTopic(topic).stream()
                    .map(book -> GdBookService.Book.newBuilder()
                            .setTitle(book.getTitle())
                            .setAuthor(book.getAuthor())
                            .setTopic(topic)
                            .setPrice(book.getPrice().doubleValue())
                            .setPages(book.getPages())
                            .setCreationDate(String.valueOf(book.getCreationDate()))
                            .build())
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
        }
    }
}
