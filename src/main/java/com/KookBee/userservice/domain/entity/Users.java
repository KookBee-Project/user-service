package com.KookBee.userservice.domain.entity;

import com.KookBee.userservice.domain.enums.EStatus;
import com.KookBee.userservice.domain.enums.EUserType;
import com.KookBee.userservice.domain.dto.UserDTO;
import com.KookBee.userservice.domain.request.UserChangeInfoRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class    Users {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String userEmail;
    private String userPw;
    private String userName;
    private String userBirth;
    private String userPhoneNumber;
    @Enumerated(EnumType.STRING)
    private EUserType userType;
    @Enumerated(EnumType.STRING)
    private EStatus userStatus;
    @JsonIgnore
    @OneToOne(mappedBy = "users",fetch = FetchType.LAZY)
    private Manager manager;
    @JsonIgnore
    @OneToOne(mappedBy = "users",fetch = FetchType.LAZY)
    private Teacher teacher;
    private String saltCode;

    public Users(UserDTO dto) {
        this.userEmail = dto.getUserEmail();
        this.userPw = dto.getUserPw();
        this.userName = dto.getUserName();
        this.userBirth = dto.getUserBirth();
        this.userPhoneNumber = dto.getUserPhoneNumber();
        this.userType = dto.getUserType();
        this.userStatus = EStatus.AVAILABLE;
    }

    public Users(Long userId) {
        this.id = userId;
    }

    public Users updateUserInfo(UserChangeInfoRequest request){
        this.id = request.getUserId();
        this.userPw = request.getUserPw();
        this.userPhoneNumber = request.getUserPhoneNumber();
        return this;
    }

}
