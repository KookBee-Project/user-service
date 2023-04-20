package com.KookBee.userservice.repository;

import com.KookBee.userservice.domain.entity.Manager;
import com.KookBee.userservice.domain.entity.ManagerCampus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManagerCampusRepository extends JpaRepository <ManagerCampus,Long> {
    List<ManagerCampus> findByManager(Manager manager);
}
