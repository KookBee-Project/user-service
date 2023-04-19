package com.KookBee.userservice.repository;

import com.KookBee.userservice.domain.dto.CompanyDTO;
import com.KookBee.userservice.domain.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByCompanyCode(String companyCode);
}
