package com.poscodx.mysite.web.mvc.board;

import com.poscodx.mysite.vo.UserVo;
import com.poscodx.mysite.web.utils.WebUtil;
import com.poscodx.web.mvc.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class BoardWriteFromAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");

		if(authUser == null) {
			response.sendRedirect(request.getContextPath()+"/board");
			return;
		}
		WebUtil.forward("board/write", request, response);
	}
}
