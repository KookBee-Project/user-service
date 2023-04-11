package com.KookBee.userservice.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bouncycastle.crypto.PasswordConverter;
import org.springframework.data.domain.Auditable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Users {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String userEmail;
    private String userPw;
    private String userName;
    private String userBirth;
    private String userPhoneNumber;
    private String userType;
    private String userStatus;
    private String saltCode;

}
