package com.dev.gdmainservice.repositories;

import com.dev.gdmainservice.models.entity.GdPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface GdPersonRepository extends JpaRepository<GdPerson, Long> {

    @Query("select p.extraInfo from GdPerson p where p.id = :id")
    @Transactional(readOnly = true)
    GdPerson.ExtraInfo getExtraInfoById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update person set extra_info = :extraInfo where id = :id")
    void saveExtraInfo(@Param("id") Long id, @Param("extraInfo") String extraInfo);
}
