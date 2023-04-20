package com.KookBee.userservice.service;

import com.KookBee.userservice.domain.dto.CampusDTO;
import com.KookBee.userservice.domain.entity.*;
import com.KookBee.userservice.domain.request.CampusFindByCompanyRequest;
import com.KookBee.userservice.domain.request.CampusInsertRequest;
import com.KookBee.userservice.domain.response.ManagerCampusResponse;
import com.KookBee.userservice.domain.response.ManagerCompanyResponse;
import com.KookBee.userservice.repository.*;
import com.KookBee.userservice.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CampusService {
    private final CampusRepository campusRepository;
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final ManagerRepository managerRepository;
    private final ManagerCampusRepository managerCampusRepository;
    private final JwtService jwtService;

    public void insertCampus(CampusInsertRequest request){
        CampusDTO dto = new CampusDTO(request);
        Optional<Company> byId = companyRepository.findById(request.getCompanyId());
        dto.setCompany(byId.get());
        Campus campus = new Campus(dto);
        campusRepository.save(campus);
    }
    public List<String> findCampus(CampusFindByCompanyRequest request) {
        return campusRepository.findCampusNameByCompanyId(request.getCompanyId());
    }

    public ManagerCompanyResponse findCampusByManagerId() {
        Long userId = jwtService.tokenToDTO(jwtService.getAccessToken()).getId();
        Users users = new Users(userId);
        Optional<Manager> findByUser = managerRepository.findByUsers(users);
        Manager manager = findByUser.orElseThrow(NullPointerException::new);
        List<ManagerCampusResponse> campusList = manager.getManagerCampusList().stream().map(el ->
                        new ManagerCampusResponse(el.getCampus().getId(), el.getCampus().getCampusName()))
                .collect(Collectors.toList());
        ManagerCompanyResponse managerCompanyResponse = new ManagerCompanyResponse(campusList, manager.getCompany().getId());

        return managerCompanyResponse;
    }

    public Campus getCampusById(Long campusId) {
        Optional<Campus> findById = campusRepository.findById(campusId);
        Campus campus = findById.orElseThrow(NullPointerException::new);
        return campus;
    }

//    public List<String > getCampushByManger(Long managerId) {
//        Optional<Users> users = userRepository.findById(managerId);
//        Users users1 = users.get();
//        List<ManagerCampus> managerCampuses = managerCampusRepository.findByManager(users1.getManager());
//        List<String > campusList = managerCampuses.stream().map(el -> {
//           return el.getCampus().getCampusName();
//        }).collect(Collectors.toList());
//        return campusList;
//    }
}
