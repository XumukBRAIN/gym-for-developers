package com.dev.gdmainservice.repositories;

import com.dev.gdmainservice.models.entity.GdNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface GdNoteRepository extends JpaRepository<GdNote, Integer> {

    Optional<GdNote> findById(int id);

    GdNote findNoteById(Integer id);

    @Modifying
    @Query(value = "UPDATE note SET status = :status WHERE id = :id", nativeQuery = true)
    @Transactional
    void changeStatus(@Param("id") Integer id, @Param("status") Integer status);
}
