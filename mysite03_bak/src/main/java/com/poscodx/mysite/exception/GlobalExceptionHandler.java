package com.poscodx.mysite.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IOException.class)
    public String handlerException(Exception e){
        //1. 로깅(Loggin)
        System.out.println(e);
        //2. 사과페이지
        return "error/exception";
    }
}
