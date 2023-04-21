package com.KookBee.userservice.service;

import com.KookBee.userservice.domain.entity.*;
import com.KookBee.userservice.domain.response.CampusListResponse;
import com.KookBee.userservice.repository.CampusRepository;
import com.KookBee.userservice.repository.ManagerCampusRepository;
import com.KookBee.userservice.repository.ManagerRepository;
import com.KookBee.userservice.security.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ManagerService {
    private final JwtService jwtService;
    private final ManagerRepository managerRepository;
    private final ManagerCampusRepository managerCampusRepository;

    public List<CampusListResponse> getCampusList(){
        Long userId = jwtService.tokenToDTO(jwtService.getAccessToken()).getId();
        Users users = new Users(userId);
        Optional<Manager> manager = managerRepository.findByUsers(users);
        Manager manager1 = manager.orElseThrow(NullPointerException::new);
//        List<ManagerCampus> byManager = managerCampusRepository.findByManager(manager1);
//        List<CampusListResponse> responses = byManager.stream()
//                .map(CampusListResponse::new)
//                .collect(Collectors.toList());
//          List<CampusListResponse> responses = manager1.getManagerCampusList().stream()
//                .map(CampusListResponse::new)
//                .collect(Collectors.toList());
        List<CampusListResponse> responses2 = managerRepository.findCampusByUsers(users).stream()
                .map(CampusListResponse::new)
                .collect(Collectors.toList());
        return responses2;
    }

}
