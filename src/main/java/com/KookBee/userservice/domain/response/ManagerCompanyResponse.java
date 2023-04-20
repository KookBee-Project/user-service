package com.KookBee.userservice.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ManagerCompanyResponse {
    private List<ManagerCampusResponse> managerCampusList;
    private Long companyId;
}
