package com.KookBee.userservice.domain.entity;

import com.KookBee.userservice.domain.dto.ManagerDTO;
import com.KookBee.userservice.domain.dto.UserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Manager {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manager_id")
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users users;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "manager",fetch = FetchType.LAZY)
    private List<ManagerCampus> managerCampusList;

    public Manager(ManagerDTO dto) {
        this.users = dto.getUsers();
        this.company = dto.getCompany();
    }
}
