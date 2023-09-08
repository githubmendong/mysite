package com.poscodx.web.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ServiceConfigurationError;

// com.poscodx.web.mvc
//    |-- Action
//    |-- ActionFactory

//com.poscodx.web.util
//    |-- WebUtuils



//com.poscodx.mysite.web.mvc
//    |-- Action
//    |-- JoinFormAction
//    |-- JoinSuccessAction

// com.poscodx.mysite.web.mvc.guestbook
//  |-- IndexAction
//  |-- IndexAction
//  |-- IndexAction
//  |-- Action
public interface Action {
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceConfigurationError;
}
