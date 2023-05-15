package com.KookBee.userservice.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class UserChangeInfoRequest {
    private Long userId;
    private String userPw;
    private String userPhoneNumber;
}
