package com.poscodx.web.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ServiceConfigurationError;

//com.poscodx.web.mvc
//        |--- Action
//
//        com.poscodx.mysite.web.mvc.user
//        |--- UserActionFactory
//        |--- JoinAction
//        |--- JoinFormAction
//        |--- JoinSuccessAction
//
//        com.poscodx.mysite.web.mvc.guestbook
//        |--- IndexAction
//        |--- AddAction
//        |--- DeleteFormAction
//        |--- DeleteAction
public interface ActionFactory {
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceConfigurationError;
}
