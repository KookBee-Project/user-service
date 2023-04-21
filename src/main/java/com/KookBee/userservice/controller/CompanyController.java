package com.KookBee.userservice.controller;

import com.KookBee.userservice.domain.entity.Company;
import com.KookBee.userservice.domain.request.CompanyFindRequest;
import com.KookBee.userservice.domain.request.CompanyInsertRequest;
import com.KookBee.userservice.domain.response.CompanyCodeResponse;
import com.KookBee.userservice.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("user/company")
public class CompanyController {
    private final CompanyService companyService;
    @PostMapping
    public void insertCompany(@RequestBody CompanyInsertRequest request){
        companyService.insertCompany(request);
    }

    @PostMapping("/companycode")
    public CompanyCodeResponse findCompany (@RequestBody CompanyFindRequest request) {

        return companyService.findCompanyByCompanyCode(request);


    }
}
