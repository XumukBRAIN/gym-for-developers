package com.dev.gdstoreservice.repositories;

import com.dev.gdstoreservice.models.entity.GdBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public interface GdBookRepository extends JpaRepository<GdBook, Integer> {

    /**
     * Получение списка книг по топику
     *
     * @param topic Топик
     * @return Список книг
     */
    List<GdBook> getAllByTopic(String topic);
}
