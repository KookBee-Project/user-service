package com.KookBee.userservice.repository;

import com.KookBee.userservice.domain.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Long> {
    Optional<Users> findByUserEmailAndUserPw(String userEmail, String userPw);
    Optional<Users> findByUserEmail(String UserEmail);
}
