package com.dev.gdmainservice.repositories;

import com.dev.gdmainservice.models.entity.GdComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GdCommentRepository extends JpaRepository<GdComment, Integer> {
}
