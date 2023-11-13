package com.dev.gdmainservice.repositories;

import com.dev.gdmainservice.models.entity.GdNoteComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GdNoteCommentRepository extends JpaRepository<GdNoteComment, Integer> {
}
