package com.KookBee.userservice.domain.dto;

import com.KookBee.userservice.domain.request.ManagerSignUpRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class UserDTO {
    private String userEmail;
    private String userPw;
    private String userName;
    private String userBirth;
    private String userPhoneNumber;
    private EUserType userType;

    public UserDTO(ManagerSignUpRequest request) {
        this.userEmail = request.getUserEmail();
        this.userPw = request.getUserPw();
        this.userName = request.getUserName();
        this.userBirth = request.getUserBirth();
        this.userPhoneNumber = request.getUserPhoneNumber();
        this.userType = request.getUserType();
    }
}
