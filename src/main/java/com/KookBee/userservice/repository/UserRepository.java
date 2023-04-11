package com.KookBee.userservice.repository;

import com.KookBee.userservice.domain.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<Users,Long> {
}
