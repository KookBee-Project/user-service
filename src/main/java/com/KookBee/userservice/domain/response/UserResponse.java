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
    private String name;

    public UserResponse(Users users) {
        this.name = users.getUserName();
    }
}
