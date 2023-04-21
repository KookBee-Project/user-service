package com.KookBee.userservice.exception;

public class LoginException extends IllegalArgumentException{
    public LoginException() {
        super("아이디 혹은 비밀번호가 잘못되었습니다!");
    }
}
