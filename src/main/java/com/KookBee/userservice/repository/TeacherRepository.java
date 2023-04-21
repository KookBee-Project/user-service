package com.KookBee.userservice.repository;

import com.KookBee.userservice.domain.entity.Teacher;
import com.KookBee.userservice.domain.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Optional<Teacher> findByUsers(Users users);
}
