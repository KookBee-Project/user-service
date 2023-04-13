package com.KookBee.userservice.security;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class TokenInfo {
    private Long id;

    public TokenInfo tokenToDTO(Claims claims) {
        Long id = Long.parseLong(String.valueOf(claims.get("userId")));
        return new TokenInfo(id);
    }
}
