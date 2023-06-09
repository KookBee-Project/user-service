package com.KookBee.userservice.security;

import com.KookBee.userservice.domain.entity.RefreshToken;
import com.KookBee.userservice.repository.RefreshTokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.security.Key;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final RefreshTokenRepository refreshTokenRepository;
    @Value("${jwt.ACCESS_SECRET_KEY}")
    private String AccessSecret;
    @Value("${jwt.REFRESH_SECRET_KEY}")
    private String RefreshSecret;

//    public void setAccessTokenInHttpOnlyCookie(HttpServletResponse response, String accessToken) {
//        // 쿠키 생성 및 값 설정
//        response.addHeader("Set-Cookie", ResponseCookie.from("accessToken", accessToken)
//                .maxAge(60 * 30)// 쿠키 만료일 설정 (예: 30분)
//                .sameSite("None")
//                .secure(true)
//                .path("/") // 쿠키 경로 설정 (옵션)
//                .httpOnly(true) // HTTPOnly 쿠키 설정
//                .build().toString());
//    }

//    public String getAccessToken() {
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("accessToken")) {
//                    // accessToken이라는 이름의 쿠키에서 값 읽어오기
//                    String accessToken = cookie.getValue();
//                    // 값 처리 로직
//                    return accessToken;
//                }
//            }
//        }
//        // 쿠키가 없을 경우 예외 처리 또는 기본값 설정
//        return null;
//    }


    public String createAccessToken(Long userId){
        byte[] keyBytes = Decoders.BASE64.decode(AccessSecret);
        Key key = Keys.hmacShaKeyFor(keyBytes);
        Date now = new Date();
        String accessToken = Jwts.builder()
                .setHeaderParam("type","jwt")
                .claim("userId",userId)
                .setIssuedAt(now)
                .setExpiration(new Date(System.currentTimeMillis()+ (1000 * 60 * 30))) // 만료기간은 30분으로 설정
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        return accessToken;
    }


    public String createRefreshToken(Long userIdx){
        byte[] keyBytes = Decoders.BASE64.decode(RefreshSecret);
        Key key = Keys.hmacShaKeyFor(keyBytes);
        Date now = new Date();
        String jwtToken =  Jwts.builder()
                .setHeaderParam("type","jwt")
                .setIssuedAt(now)
                .setExpiration(new Date(System.currentTimeMillis()+ (1000 * 3600 * 24 * 7))) // 만료기간은 1주일로 설정
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        RefreshToken refreshToken = new RefreshToken(jwtToken, userIdx);
        refreshTokenRepository.save(refreshToken);
        return refreshToken.getRefreshToken();
    }


    public String getRefreshToken(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return request.getHeader("RefreshToken");
    }

    public String getAccessToken() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return request.getHeader("AccessToken");
    }

    public TokenInfo tokenToDTO(String accessToken){
        try{
            Claims claims = Jwts
                    .parserBuilder()
                    .setSigningKey(AccessSecret)
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
            TokenInfo info = new TokenInfo().tokenToDTO(claims);
            return info;
        }catch (Exception e){
            return null;
        }
    }
    public String isValidTokens(){ //엑세스 토큰과 리프레쉬 토큰의 유효성을 둘다 검사한다
        //check both refresh AND access token
        String accessToken = getAccessToken();
        String refreshToken = getRefreshToken();
        if(!isValidAccessToken(accessToken)){
            return isValidRefreshToken(refreshToken);
        }
        return "OK";



    }
    public boolean isValidAccessToken(String accessToken){
        if(accessToken == null) return false;
        // Access Token이 유효하지 않으면
        // is access token is not valid
        if(tokenToDTO(accessToken) == null) return false;

        return true;
    }

    private String isValidRefreshToken(String refreshToken) {
        try {
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
            Optional<RefreshToken> redisToken = refreshTokenRepository.findById(refreshToken);
            if(redisToken.isPresent()) {
                // Refresh Token이 있다면 새로운 Access Token을 생성하여 HTTPOnly 쿠키에 설정하고 반환한다
                // if refresh token exists create new access token and set pm HTTPOnly cookie
                return refreshAccessToken(response, redisToken.get());
            }
            return null;
        } catch (Exception e){
            return null;
        }
    }

    private String refreshAccessToken(HttpServletResponse response, RefreshToken redisToken) {
        //새로운 엑세스 토큰 생성
        // create new access token
        String newAccessToken = createAccessToken(redisToken.getUserIdx());
//        response.addHeader("accessToken", newAccessToken);
        return newAccessToken;
    }
}
