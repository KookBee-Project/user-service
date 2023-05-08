package com.KookBee.userservice.controller;


import com.KookBee.userservice.domain.enums.EUserType;
import com.KookBee.userservice.domain.request.UserLoginRequest;
import com.KookBee.userservice.domain.response.UserLoginResponse;
import com.KookBee.userservice.domain.entity.Users;
import com.KookBee.userservice.domain.response.UserResponse;
import com.KookBee.userservice.exception.EmailCheckException;
import com.KookBee.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/{userId}")
    public Users getUserById(@PathVariable("userId") Long userId){
        return userService.getUser(userId);
    }

    @GetMapping("/usertype")
    public EUserType getUserType(){
        return userService.getUserType();
    }

    @GetMapping
    public UserResponse getMe(){
        return userService.getMe();
    }
}
