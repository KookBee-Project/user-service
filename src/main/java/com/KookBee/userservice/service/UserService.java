package com.KookBee.userservice.service;

import com.KookBee.userservice.domain.dto.UserDTO;
import com.KookBee.userservice.domain.entity.Users;
import com.KookBee.userservice.domain.request.ManagerSignUpRequest;
import com.KookBee.userservice.repository.CompanyRepository;
import com.KookBee.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    public void managerSignUp(ManagerSignUpRequest request) {
        // 1. save in User / User에 저장
        UserDTO userDTO = new UserDTO(request);
        Users users = new Users(userDTO);
        userRepository.save(users);
        // 2. check is companyCode / companyCode가 있는지 확인
//        if (companyRepository.findByCompanyCode(request.getCompanyCode()){
//
//        }
        // 3-1 if true find campus of company / 있다면 company의 campus목록을 불러옴
        // 4. save campusList in manager_campus / 캠퍼스 목록을 manager_campus에 저장함
    }
}
