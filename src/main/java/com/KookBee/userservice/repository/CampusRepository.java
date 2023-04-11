package com.KookBee.userservice.repository;

import com.KookBee.userservice.domain.entity.Campus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampusRepository extends JpaRepository<Campus,Long> {
}
