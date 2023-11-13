package com.dev.gdmainservice.repositories;

import com.dev.gdmainservice.models.entity.GdPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GdPersonRepository extends JpaRepository<GdPerson, Integer> {

    Optional<GdPerson> findById(long id);

    @Query("select p.extraInfo from GdPerson p where p.id = :id")
    GdPerson.ExtraInfo getExtraInfoById(@Param("id") Long id);
}
