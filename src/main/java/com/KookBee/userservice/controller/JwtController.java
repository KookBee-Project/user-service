package com.KookBee.userservice.controller;

import com.KookBee.userservice.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/token")
public class JwtController {
    private final JwtService jwtService;
    @GetMapping
    public ResponseEntity<String> jwtCheck(){
        String check = jwtService.isValidTokens();
        if(check != null) return new ResponseEntity<>(check, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
