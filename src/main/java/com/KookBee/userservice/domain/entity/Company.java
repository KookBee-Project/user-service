package com.KookBee.userservice.domain.entity;

import com.KookBee.userservice.domain.dto.CompanyDTO;
import com.KookBee.userservice.domain.enums.EStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Company {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long id;
    private String companyName;
    private String companyAddress;
    private String companyTelNumber;
    @Column(unique = true)
    private String companyBusinessNumber;
    @Enumerated(EnumType.STRING)
    private EStatus companyStatus;
    private String companyCode;
    @JsonIgnore
    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
    private List<Campus> campuses;
    @JsonIgnore
    @OneToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private List<Manager> managers;

    public Company(CompanyDTO dto) {
        this.companyName = dto.getCompanyName();
        this.companyAddress = dto.getCompanyAddress();
        this.companyTelNumber = dto.getCompanyTelNumber();
        this.companyBusinessNumber = dto.getCompanyBusinessNumber();
        this.companyStatus = EStatus.AVAILABLE;
        this.companyCode = dto.getCompanyCode();
    }
}
