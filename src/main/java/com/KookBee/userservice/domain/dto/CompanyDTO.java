package com.KookBee.userservice.domain.dto;

import com.KookBee.userservice.domain.request.CompanyInsertRequest;
import jakarta.persistence.Column;
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
    @Column(unique = true)
    private String companyCode;

    public CompanyDTO(CompanyInsertRequest request, String companyCode) {
        this.companyName = request.getCompanyName();
        this.companyAddress = request.getCompanyAddress();
        this.companyTelNumber = request.getCompanyTelNumber();
        this.companyBusinessNumber = request.getCompanyBusinessNumber();
        this.companyCode = companyCode;
    }
}
