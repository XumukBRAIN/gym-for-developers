package com.dev.gdstoreservice.repositories;

import com.dev.gdstoreservice.models.entity.GdBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GdBookRepository extends JpaRepository<GdBook, Integer> {

    /**
     * Получение списка книг по топику
     *
     * @param topic Топик
     * @return Список книг
     */
    List<GdBook> getAllByTopic(String topic);
}
