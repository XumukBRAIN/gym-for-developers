package com.dev.gdmainservice.repositories;

import com.dev.gdmainservice.models.entity.GdPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GdPersonRepository extends JpaRepository<GdPerson, Integer> {

    Optional<GdPerson> findById(long id);
}
