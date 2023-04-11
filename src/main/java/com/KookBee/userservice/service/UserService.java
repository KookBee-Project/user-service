package com.KookBee.userservice.service;

import com.KookBee.userservice.domain.entity.Users;
import com.KookBee.userservice.domain.request.UserLoginRequest;
import com.KookBee.userservice.domain.response.UserLoginResponse;
import com.KookBee.userservice.exception.LoginException;
import com.KookBee.userservice.repository.UserRepository;
import com.KookBee.userservice.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    public UserLoginResponse login(UserLoginRequest request) {
        Optional<Users> findByLogin = userRepository.findByUserEmailAndUserPw(request.getUserEmail(), request.getUserPw());
        Users users = findByLogin.orElseThrow(LoginException::new);
        // 토큰 생성
        String accessToken = jwtService.createAccessToken(users.getId());
        String refreshToken = jwtService.createRefreshToken(users.getId());
        return new UserLoginResponse(users.getId(), accessToken, refreshToken);
    }
}
