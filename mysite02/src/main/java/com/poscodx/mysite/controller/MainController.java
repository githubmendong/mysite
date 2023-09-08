package com.poscodx.mysite.controller;

import com.poscodx.mysite.web.mvc.main.MainActionFactory;
import com.poscodx.mysite.web.mvc.user.UserActionFactory;
import com.poscodx.web.mvc.Action;
import com.poscodx.web.mvc.ActionFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet ("/main")
public class MainController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String actionName = request.getParameter("a");
        Action action = new MainActionFactory().getAction(actionName);
        action.execute(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}