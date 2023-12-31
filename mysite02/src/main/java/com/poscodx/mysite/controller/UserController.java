package com.poscodx.mysite.controller;

import com.poscodx.mysite.web.mvc.user.UserActionFactory;
import com.poscodx.web.mvc.Action;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet ("/user")

public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String actionName = request.getParameter("a");
        Action action = new UserActionFactory().getAction(actionName);
        action.execute(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}