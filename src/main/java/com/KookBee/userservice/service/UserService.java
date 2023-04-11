package com.KookBee.userservice.service;

import com.KookBee.userservice.converter.Encrypt;
import com.KookBee.userservice.domain.entity.Users;
import com.KookBee.userservice.exception.EmailCheckException;
import com.KookBee.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final Encrypt encrypt;
    public String studentSignUpService(Users users) throws EmailCheckException {
        // 이메일 중복체크
        Optional<Users> findByUserEmail = userRepository.findByUserEmail(users.getUserEmail());
        // 중복된 이메일이 있을 경우, EmailCheckException을 던진다.
        if(findByUserEmail.isPresent()){
            throw new EmailCheckException();
        }
        // 암호화에 사용된 salt를 변수로 저장한다.
        String salt = encrypt.getSalt();
        // 비밀번호 암호화
        String encoded = encrypt.getEncrypt(users.getUserPw(), salt);
        // salt와 암호화된 비밀번호를 users에 저장
        users.setUserPw(encoded);
        users.setSaltCode(salt);
        // DB에 저장
        userRepository.save(users);
        return "가입이 완료되었습니다.";
    };
}
