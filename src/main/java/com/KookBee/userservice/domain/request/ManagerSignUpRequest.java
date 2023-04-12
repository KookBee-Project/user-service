package com.KookBee.userservice.domain.request;

import com.KookBee.userservice.domain.enums.EUserType;
import com.KookBee.userservice.domain.entity.Company;
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
    private Long companyId;
    private List<String> campusList;
}
