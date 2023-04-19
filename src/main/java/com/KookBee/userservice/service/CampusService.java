package com.KookBee.userservice.service;

import com.KookBee.userservice.domain.dto.CampusDTO;
import com.KookBee.userservice.domain.entity.Campus;
import com.KookBee.userservice.domain.entity.Company;
import com.KookBee.userservice.domain.request.CampusFindByCompanyRequest;
import com.KookBee.userservice.domain.request.CampusInsertRequest;
import com.KookBee.userservice.repository.CampusRepository;
import com.KookBee.userservice.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CampusService {
    private final CampusRepository campusRepository;
    private final CompanyRepository companyRepository;

    public void insertCampus(CampusInsertRequest request){
        CampusDTO dto = new CampusDTO(request);
        Optional<Company> byId = companyRepository.findById(request.getCompanyId());
        dto.setCompany(byId.get());
        Campus campus = new Campus(dto);
        campusRepository.save(campus);
    }
    public List<String> findCampus(CampusFindByCompanyRequest request) {
        return campusRepository.findCampusNameByCompanyId(request.getCompanyId());
    }

}
