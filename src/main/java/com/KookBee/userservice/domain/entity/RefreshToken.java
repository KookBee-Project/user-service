package com.KookBee.userservice.domain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "refreshToken", timeToLive = 604800)
public class RefreshToken {
    @Id
    private String refreshToken;
    private Long userIdx;

    public RefreshToken(final String refreshToken, final Long userIdx) {
        this.refreshToken = refreshToken;
        this.userIdx = userIdx;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public Long getUserIdx() {
        return userIdx;
    }
}
