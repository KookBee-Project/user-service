package com.KookBee.userservice.domain.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
public class CompanyInsertRequest {
    private String companyName;
    private String companyAddress;
    private String companyTelNumber;
    private String companyBusinessNumber;
}
