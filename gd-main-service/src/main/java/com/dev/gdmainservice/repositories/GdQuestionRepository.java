package com.dev.gdmainservice.repositories;

import com.dev.gdmainservice.models.entity.GdQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GdQuestionRepository extends JpaRepository<GdQuestion, Integer> {

    GdQuestion findById(Long questionId);

}
