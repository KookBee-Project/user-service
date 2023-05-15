package com.KookBee.userservice.domain.response;

import com.KookBee.userservice.domain.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class UserResponse {
    private Long userId;
    private String userEmail;
    private String userName;
    private String userBirth;
    private String userPhoneNumber;

    public UserResponse(Users users) {
        this.userId = users.getId();
        this.userEmail = users.getUserEmail();
        this.userName = users.getUserName();
        this.userBirth = users.getUserBirth();
        this.userPhoneNumber = users.getUserPhoneNumber();
    }
}
