package com.dev.gdmainservice.repositories;

import com.dev.gdmainservice.models.entity.GdNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface GdNoteRepository extends JpaRepository<GdNote, Integer> {

    Optional<GdNote> findById(int id);

    GdNote findNoteById(Integer id);

    List<GdNote> findAllByStatusEquals(String status);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(nativeQuery = true, value = "update note set status = :status where id = :id")
    void accept(@Param("id") Integer id, @Param("status") String status);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(nativeQuery = true, value = "update note set status = :status where id = :id")
    void reject(@Param("id") Integer id, @Param("status") String status);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(nativeQuery = true, value = "INSERT INTO note_history SELECT section, advice, date_of_creation, who_created, :status FROM note WHERE id = :id; " +
            "DELETE FROM note WHERE id = :id")
    void delete(@Param("id") Integer id, @Param("status") String status);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(nativeQuery = true, value = "INSERT INTO note SELECT section, advice, date_of_creation, who_created, :status FROM note WHERE id = :id; " +
            "DELETE FROM note_history WHERE id = :id")
    void recover(@Param("id") Integer id, @Param("status") String status);
}
