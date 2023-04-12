package com.KookBee.userservice.service;

import com.KookBee.userservice.domain.dto.UserDTO;
import com.KookBee.userservice.domain.entity.Users;
import com.KookBee.userservice.domain.request.ManagerSignUpRequest;
import com.KookBee.userservice.repository.CompanyRepository;
import com.KookBee.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.KookBee.userservice.domain.request.UserLoginRequest;
import com.KookBee.userservice.domain.response.UserLoginResponse;
import com.KookBee.userservice.exception.LoginException;
import com.KookBee.userservice.security.JwtService;
import com.KookBee.userservice.converter.Encrypt;
import com.KookBee.userservice.exception.EmailCheckException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final Encrypt encrypt;
    private final CompanyRepository companyRepository;

    public void managerSignUp(ManagerSignUpRequest request) {
        // 1. save in User / User에 저장
        UserDTO userDTO = new UserDTO(request);
        Users users = new Users(userDTO);
        userRepository.save(users);
        // 2. check is companyCode / companyCode가 있는지 확인
//        if (companyRepository.findByCompanyCode(request.getCompanyCode()){
//
//        }
        // 3-1 if true find campus of company / 있다면 company의 campus목록을 불러옴
        // 4. save campusList in manager_campus / 캠퍼스 목록을 manager_campus에 저장함
    }

    private final JwtService jwtService;
    public UserLoginResponse login(UserLoginRequest request) {
        Optional<Users> findByLogin = userRepository.findByUserEmail(request.getUserEmail());
        Users users = findByLogin.orElseThrow(LoginException::new);
        String salt = users.getSaltCode();
        String encoded = encrypt.getEncrypt(request.getUserPw(), salt);
        if(encoded.equals(users.getUserPw())) {
            // 토큰 생성
            String accessToken = jwtService.createAccessToken(users.getId());
            String refreshToken = jwtService.createRefreshToken(users.getId());
            return new UserLoginResponse(users.getId(), accessToken, refreshToken);
        }
        return null;
    }

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
