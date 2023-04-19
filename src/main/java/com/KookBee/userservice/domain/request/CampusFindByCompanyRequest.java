package com.KookBee.userservice.domain.request;

import com.KookBee.userservice.domain.entity.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CampusFindByCompanyRequest {
    private Long companyId;
}
