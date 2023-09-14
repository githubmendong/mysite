package com.poscodx.mysite.controller;

import com.poscodx.mysite.web.mvc.board.BoardActionFactory;
import com.poscodx.web.mvc.Action;
import com.poscodx.web.mvc.ActionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/board")
public class BoardController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.getRequestDispatcher("/WEB-INF/views/board/list.jsp").forward(request, response);
        String actionName = request.getParameter("a");
        System.out.println(actionName);
        ActionFactory af = new BoardActionFactory();
        Action action = af.getAction(actionName);
        action.execute(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}