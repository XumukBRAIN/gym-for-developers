package com.dev.gdmainservice.repositories;

import com.dev.gdmainservice.models.entity.GdQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GdQuestionRepository extends JpaRepository<GdQuestion, Integer> {

    GdQuestion findById(Long questionId);

    @Query(nativeQuery = true, value = "SELECT * FROM question ORDER BY RANDOM() LIMIT :count")
    List<GdQuestion> findRandomQuestions(@Param("count") Integer count);
}
