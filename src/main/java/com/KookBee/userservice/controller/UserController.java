package com.KookBee.userservice.controller;


import com.KookBee.userservice.domain.request.UserLoginRequest;
import com.KookBee.userservice.domain.response.UserLoginResponse;
import com.KookBee.userservice.domain.entity.Users;
import com.KookBee.userservice.exception.EmailCheckException;
import com.KookBee.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public UserLoginResponse login(@RequestBody UserLoginRequest request){
        return userService.login(request);
    }
        
    @PostMapping("/signup")
    public String StudentSignUp(@RequestBody Users users) throws EmailCheckException {
        return userService.studentSignUpService(users);
    }
}
