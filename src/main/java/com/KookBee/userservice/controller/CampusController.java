package com.KookBee.userservice.controller;

import com.KookBee.userservice.domain.request.CampusFindByCompanyRequest;
import com.KookBee.userservice.domain.request.CampusInsertRequest;
import com.KookBee.userservice.domain.request.CompanyFindRequest;
import com.KookBee.userservice.service.CampusService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("campus")
@RequiredArgsConstructor
public class CampusController {
    private final CampusService campusService;

    @PostMapping
    public void insertCampus(@RequestBody CampusInsertRequest request){
        campusService.insertCampus(request);
    }

    @PostMapping("/companyname")
    public List<String> findByCompanyName(@RequestBody CampusFindByCompanyRequest request){
        return campusService.findCampus(request);
    }
}
