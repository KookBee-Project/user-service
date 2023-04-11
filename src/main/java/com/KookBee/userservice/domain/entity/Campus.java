package com.KookBee.userservice.domain.entity;

import com.KookBee.userservice.domain.dto.CampusDTO;
import com.KookBee.userservice.domain.dto.EStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
public class Campus {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campus_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;
    private String campusAddress;
    private String campusTelNumber;
    private String campusName;
    @Enumerated(EnumType.STRING)
    private EStatus campusStatus;

    @OneToMany(mappedBy = "campus", fetch = FetchType.LAZY)
    private List<ManagerCampus> managerCampusList;

    public Campus(CampusDTO dto) {
        this.company = dto.getCompany();
        this.campusAddress = dto.getCampusAddress();
        this.campusTelNumber = dto.getCampusTelNumber();
        this.campusName = dto.getCampusName();
        this.campusStatus = EStatus.AVAILABLE;
    }
}
