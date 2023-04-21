package com.KookBee.userservice.controller;

import com.KookBee.userservice.domain.entity.Company;
import com.KookBee.userservice.domain.entity.Users;
import com.KookBee.userservice.domain.request.ManagerSignUpRequest;
import com.KookBee.userservice.domain.request.TeacherSignUpRequest;
import com.KookBee.userservice.domain.response.CampusListResponse;
import com.KookBee.userservice.exception.EmailCheckException;
import com.KookBee.userservice.service.ManagerService;
import com.KookBee.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    private final ManagerService managerService;
    @PostMapping("/manager/signup")
    public String managerSignUp(@RequestBody ManagerSignUpRequest request) throws EmailCheckException {
        return userService.managerSignUp(request);

    }
    @PostMapping("/teacher/signup")
    public String teacherSignUp(@RequestBody TeacherSignUpRequest request) throws EmailCheckException {
        return userService.teacherSignUp(request);

    }
    @GetMapping("/teacher/{email}")
    public Users findUserByTeacherEmail(@PathVariable String email) {
        return userService.getUserByTeacherEmail(email);
    }

    @GetMapping("/manager/campuslist")
    public List<CampusListResponse> getCampusList(){
        return managerService.getCampusList();
    }
}
