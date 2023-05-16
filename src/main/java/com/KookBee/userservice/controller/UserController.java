package com.KookBee.userservice.controller;


import com.KookBee.userservice.domain.enums.EUserType;
import com.KookBee.userservice.domain.request.UserChangeInfoRequest;
import com.KookBee.userservice.domain.request.UserEmailRequest;
import com.KookBee.userservice.domain.request.UserLoginRequest;
import com.KookBee.userservice.domain.response.PortPolioStudyFindUserResponse;
import com.KookBee.userservice.domain.response.UserLoginResponse;
import com.KookBee.userservice.domain.entity.Users;
import com.KookBee.userservice.domain.response.UserResponse;
import com.KookBee.userservice.exception.EmailCheckException;
import com.KookBee.userservice.exception.NotFoundUserByEmailException;
import com.KookBee.userservice.exception.TokenExpirationException;
import com.KookBee.userservice.security.JwtService;
import com.KookBee.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public UserResponse getMe() throws TokenExpirationException {
        return userService.getMe();
    }

    @PostMapping("/portfolio/study/finduser")
    public PortPolioStudyFindUserResponse postFindUser(@RequestBody UserEmailRequest request) throws NotFoundUserByEmailException {
        return userService.findUserByEmail(request);
    }
    // id리스트를 받아서 이름 리스트를 반환해주는 기능입니다.
    @PostMapping("/namelist")
    public List<UserResponse> getUserNameList(@RequestBody List<Long> userIds){
        return userService.getUserNameList(userIds);
    }

    @PutMapping("/my")
    public HttpStatus putUserInfo(@RequestBody UserChangeInfoRequest request){
        userService.putUserInfo(request);
        return HttpStatus.OK;
    }
}
