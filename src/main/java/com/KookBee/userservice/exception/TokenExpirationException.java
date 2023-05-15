package com.KookBee.userservice.exception;

public class TokenExpirationException extends Exception{
    public TokenExpirationException(){
        super("토큰이 만료되었습니다. 다시 로그인해주세요.");
    }
}
