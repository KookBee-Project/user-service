package com.KookBee.userservice.domain.dto;


import com.KookBee.userservice.domain.entity.Company;
import com.KookBee.userservice.domain.entity.ManagerCampus;
import com.KookBee.userservice.domain.entity.Users;
import com.KookBee.userservice.domain.request.ManagerSignUpRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.User;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ManagerDTO {

    private Users users;
    private List<String> campusList;
    private Company company;

}
