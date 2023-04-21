package com.KookBee.userservice.repository;

import com.KookBee.userservice.domain.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ManagerRepository extends JpaRepository <Manager, Long> {
    Optional<Manager> findByUsers(Users users);
    @Query("select cl from Manager as m " +
            "join m.managerCampusList as cl " +
            "where m.users =:users ")
    List<ManagerCampus> findCampusByUsers(@Param("users") Users users);
}
