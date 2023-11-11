package com.dev.gdmainservice.repositories;

import com.dev.gdmainservice.models.entity.GdAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface GdAnswerRepository extends JpaRepository<GdAnswer, Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(nativeQuery = true, value = "UPDATE answer SET likes = :likes WHERE id = :id")
    int saveLikes(@Param("id") Long id, @Param("likes") Long likes);

    @Query(nativeQuery = true, value = "SELECT id, likes FROM answer")
    List<Long[]> loadLikes();

}
