package com.KookBee.userservice.service;

import com.KookBee.userservice.domain.dto.CampusDTO;
import com.KookBee.userservice.domain.entity.Campus;
import com.KookBee.userservice.domain.entity.Company;
import com.KookBee.userservice.domain.request.CampusInsertRequest;
import com.KookBee.userservice.repository.CampusRepository;
import com.KookBee.userservice.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CampusService {
    private final CampusRepository campusRepository;
    private final CompanyRepository companyRepository;

    public void insertCampus(CampusInsertRequest request){
        CampusDTO dto = new CampusDTO(request);
        System.out.println(request);
        System.out.println(dto);
        Optional<Company> byId = companyRepository.findById(request.getCompanyId());
//        dto.setCompany(byId.orElse(null));
        dto.setCompany(byId.get());
        System.out.println(dto);
        Campus campus = new Campus(dto);
        System.out.println(campus);
        campusRepository.save(campus);
    }
}
