package com.KookBee.userservice.domain.response;

import com.KookBee.userservice.domain.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class PortPolioStudyFindUserResponse {
    private Long userId;
    private String userName;

    public PortPolioStudyFindUserResponse(Users users) {
        this.userId = users.getId();
        this.userName = users.getUserName();
    }
}
