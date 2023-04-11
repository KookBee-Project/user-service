package com.KookBee.userservice.domain.dto;

import com.KookBee.userservice.domain.entity.Company;
import com.KookBee.userservice.domain.request.CampusInsertRequest;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
public class CampusDTO {
    private Long companyId;
    private Company company;
    private String campusAddress;
    private String campusTelNumber;
    private String campusName;

    public CampusDTO(CampusInsertRequest request) {
        this.campusAddress = request.getCampusAddress();
        this.campusTelNumber = request.getCampusTelNumber();
        this.campusName = request.getCampusName();
    }
}
