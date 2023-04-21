package com.KookBee.userservice.domain.response;

import com.KookBee.userservice.domain.entity.Campus;
import com.KookBee.userservice.domain.entity.ManagerCampus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CampusListResponse {
    private Long id;
    private String campusName;

    public CampusListResponse(ManagerCampus ManagerCampus){
        this.id = ManagerCampus.getCampus().getId();
        this.campusName = ManagerCampus.getCampus().getCampusName();
    }
}
