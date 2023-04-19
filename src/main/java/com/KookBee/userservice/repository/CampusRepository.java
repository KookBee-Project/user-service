package com.KookBee.userservice.repository;

import com.KookBee.userservice.domain.entity.Campus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CampusRepository extends JpaRepository<Campus,Long> {
    Optional<Campus> findByCampusName(String el);
    @Transactional
    @Modifying
    @Query(value = "select c.campusName from Campus as c " +
            "inner join c.company as cc " +
            "on cc.id = :id")
    List<String> findCampusNameByCompanyId(@Param("id") Long companyId);


}
