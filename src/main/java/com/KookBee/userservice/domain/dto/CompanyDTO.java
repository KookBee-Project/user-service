package com.KookBee.userservice.domain.dto;

import com.KookBee.userservice.domain.request.CompanyFindRequest;
import com.KookBee.userservice.domain.request.CompanyInsertRequest;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
public class CompanyDTO {
    private String companyName;
    private String companyAddress;
    private String companyTelNumber;
    private String companyBusinessNumber;
    private String companyCode;

    public CompanyDTO(CompanyInsertRequest request, String companyCode) {
        this.companyName = request.getCompanyName();
        this.companyAddress = request.getCompanyAddress();
        this.companyTelNumber = request.getCompanyTelNumber();
        this.companyBusinessNumber = request.getCompanyBusinessNumber();
        this.companyCode = companyCode;
    }

    public CompanyDTO(CompanyFindRequest request) {
        this.companyCode = request.getCompanyCode();
    }
}
