package com.KookBee.userservice.security;

import com.KookBee.userservice.domain.entity.RefreshToken;
import com.KookBee.userservice.repository.RefreshTokenRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.security.Key;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final RefreshTokenRepository refreshTokenRepository;
    @Value("${jwt.ACCESS_SECRET_KEY}")
    private String AccessSecret;
    @Value("${jwt.REFRESH_SECRET_KEY}")
    private String RefreshSecret;



    public String[] createTokenWhenLogin(Long userId){
        String refreshToken = createRefreshToken(userId);
        String accessToken = createAccessToken(userId);

        String[] tokenList = {refreshToken, accessToken};
        return tokenList;
    }


    public String createAccessToken(Long userIdx){
        byte[] keyBytes = Decoders.BASE64.decode(AccessSecret);
        Key key = Keys.hmacShaKeyFor(keyBytes);
        Date now = new Date();
        String accessToken = Jwts.builder()
                .setHeaderParam("type","jwt")
                .claim("userIdx",userIdx)
                .setIssuedAt(now)
                .setExpiration(new Date(System.currentTimeMillis()+1*(1000*60*30))) // 만료기간은 30분으로 설정
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
        setAccessTokenInHttpOnlyCookie(response, accessToken);
        return accessToken;
    }


    public String createRefreshToken(Long userIdx){
        byte[] keyBytes = Decoders.BASE64.decode(RefreshSecret);
        Key key = Keys.hmacShaKeyFor(keyBytes);
        Date now = new Date();
        String jwtToken =  Jwts.builder()
                .setHeaderParam("type","jwt")
                .claim("userIdx",userIdx)
                .setIssuedAt(now)
                .setExpiration(new Date(System.currentTimeMillis()+1*(1000*60*30))) // 만료기간은 1시간으로 설정
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        RefreshToken refreshToken = new RefreshToken(jwtToken, userIdx);
        refreshTokenRepository.save(refreshToken);
        return refreshToken.getRefreshToken();
    }


    public String getAccessToken(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return request.getHeader("Authorization");
    }


    public String getRefreshToken(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return request.getHeader("RefreshToken");
    }

    // JWT 토큰을 HTTPOnly 쿠키에 설정하는 메소드
    public void setAccessTokenInHttpOnlyCookie(HttpServletResponse response, String accessToken) {
        // 쿠키 생성 및 값 설정
        Cookie cookie = new Cookie("accessToken", accessToken);
//        cookie.setMaxAge(7 * 24 * 60 * 60); // 쿠키 만료일 설정 (예: 7일 뒤)
        cookie.setHttpOnly(true); // HTTPOnly 쿠키 설정
        cookie.setPath("/"); // 쿠키 경로 설정 (옵션)

        // HTTP 응답 헤더에 쿠키 설정
        response.addCookie(cookie);
    }

    public String getCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("accessToken")) {
                    // accessToken이라는 이름의 쿠키에서 값 읽어오기
                    String accessToken = cookie.getValue();
                    // 값 처리 로직
                    return accessToken;
                }
            }
        }
        // 쿠키가 없을 경우 예외 처리 또는 기본값 설정
        return null;
    }
}
