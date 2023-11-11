package com.dev.gdmainservice.repositories;

import com.dev.gdmainservice.models.entity.GdAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface GdAdminRepository extends JpaRepository<GdAdmin, UUID> {

    Optional<GdAdmin> findByName(String name);
}
