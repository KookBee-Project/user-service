package com.KookBee.userservice.service;

import com.KookBee.userservice.domain.dto.CompanyDTO;
import com.KookBee.userservice.domain.entity.Company;
import com.KookBee.userservice.domain.request.CompanyFindRequest;
import com.KookBee.userservice.domain.request.CompanyInsertRequest;
import com.KookBee.userservice.domain.response.CompanyCodeResponse;
import com.KookBee.userservice.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    public void insertCompany(CompanyInsertRequest request){
        StringBuilder randomCode = new StringBuilder();
        for (int i = 1;i<=10;i++){
            if(i<=6){
                char ch = (char) ((Math.random() * 26) + 65);
                randomCode.append(Character.toString(ch));
            }else {
                char ch = (char)((Math.random() * 10) + 48);
                randomCode.append(Character.toString(ch));
            }
        }

        // insert company
        CompanyDTO dto = new CompanyDTO(request, randomCode.toString());
        Company company = new Company(dto);
        companyRepository.save(company);

    }
    //find company by company code
    public CompanyCodeResponse findCompanyByCompanyCode(CompanyFindRequest request) {
        Optional<Company> byCompanyCode = companyRepository.findByCompanyCode(request.getCompanyCode());
        CompanyCodeResponse companyCodeResponse
                = new CompanyCodeResponse
                (byCompanyCode.orElse(null).getId()
                        ,byCompanyCode.orElse(null).getCompanyName());
        return companyCodeResponse;
    }
}
