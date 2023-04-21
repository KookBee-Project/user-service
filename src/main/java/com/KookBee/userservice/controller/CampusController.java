package com.KookBee.userservice.controller;

import com.KookBee.userservice.domain.entity.Campus;
import com.KookBee.userservice.domain.request.CampusFindByCompanyRequest;
import com.KookBee.userservice.domain.request.CampusInsertRequest;
import com.KookBee.userservice.domain.request.CompanyFindRequest;
import com.KookBee.userservice.domain.response.ManagerCampusResponse;
import com.KookBee.userservice.domain.response.ManagerCompanyResponse;
import com.KookBee.userservice.service.CampusService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user/campus")
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

    @GetMapping("/manager")
    public ManagerCompanyResponse findByManagerId(){
        return campusService.findCampusByManagerId();
    }
    @GetMapping("/bootcamp/{campusId}")
    public Campus getCampusById(@PathVariable("campusId") Long campusId){
        return campusService.getCampusById(campusId);
    }

//    @GetMapping("/bootcamp/{managerId}")
//    public List<Campus> getCampusListByManagerId(@PathVariable("managerId") Long managerId) {
//        return campusService.getCampushByManger(managerId);
//    }
}
