package com.KookBee.userservice.repository;

import com.KookBee.userservice.domain.entity.Manager;
import com.KookBee.userservice.domain.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ManagerRepository extends JpaRepository <Manager, Long> {
    Optional<Manager> findByUsers(Users users);
}
