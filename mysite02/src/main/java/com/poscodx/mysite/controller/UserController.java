package com.poscodx.mysite.controller;

import com.poscodx.mysite.dao.UserDao;
import com.poscodx.mysite.vo.UserVo;

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

        String action = request.getParameter("a");

        if("joinform".equals(action)) {
            request
                    .getRequestDispatcher("/WEB-INF/views/user/joinform.jsp")
                    .forward(request, response);
        } else if("join".equals(action)){
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String gender = request.getParameter("gender");



            UserVo userVo = new UserVo();
            userVo.setName(name);
            userVo.setEmail(email);
            userVo.setPassword(password);
            userVo.setGender(gender);

            new UserDao().insert(userVo);

            System.out.println(name + " : " + email + " : " + "password" + " : " + password + gender);

            response.sendRedirect(request.getContextPath() + "/user?a=joinsuccess");
        } else if("joinsuccess".equals(action)) {
            request
                    .getRequestDispatcher("/WEB-INF/views/user/joinsuccess.jsp")
                    .forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}