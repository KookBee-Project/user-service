package com.KookBee.userservice.exception;

public class NotFoundUserByEmailException extends Exception{
    public NotFoundUserByEmailException() {super("입력하신 이메일을 사용중인 수강생이 없습니다.");}
}
