package com.poscodx.mysite.web.mvc.board;

import com.poscodx.mysite.dao.GuestBookDao;
import com.poscodx.web.mvc.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BoardDeleteAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		String sno = request.getParameter("no");
//		Long no = Long.parseLong(sno);
//
//		new BoardDao().deleteByNo(no);
//
//		response.sendRedirect("./board");
		String sno = request.getParameter("no");
		Long no = Long.parseLong(sno);
		String password = request.getParameter("password");
		
		new GuestBookDao().delete(no, password);
		response.sendRedirect(request.getContextPath() + "/guestbook");
	}
}
