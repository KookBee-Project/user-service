package com.KookBee.userservice.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ManagerCampus {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manager_campus_id")
    private Long id;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Manager manager;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campus_id")
    private Campus campus;

    public ManagerCampus(Manager manager, Campus campus) {
        this.manager = manager;
        this.campus = campus;
    }
}
