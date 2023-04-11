package com.KookBee.userservice.domain.request;

import com.KookBee.userservice.domain.dto.EUserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ManagerSignUpRequest {
    private String userEmail;
    private String userPw;
    private String userName;
    private String userBirth;
    private String userPhoneNumber;
    private EUserType userType;
    private String companyCode;
    private List<String> campusList;
}
