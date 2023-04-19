package com.KookBee.userservice.domain.request;

import com.KookBee.userservice.domain.enums.EUserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class TeacherSignUpRequest {
    private String userEmail;
    private String userPw;
    private String userName;
    private String userBirth;
    private String userPhoneNumber;
    private EUserType userType;
}
