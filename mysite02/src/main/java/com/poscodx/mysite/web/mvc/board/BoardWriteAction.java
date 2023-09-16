package com.poscodx.mysite.web.mvc.board;

import com.poscodx.mysite.dao.BoardDao;
import com.poscodx.mysite.vo.BoardVo;
import com.poscodx.mysite.vo.UserVo;
import com.poscodx.mysite.web.utils.WebUtil;
import com.poscodx.web.mvc.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class BoardWriteAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");

		if (authUser == null) {
			WebUtil.redirect(request.getContextPath() + "/board", request, response);
			return;
		}

		BoardDao boardDao = new BoardDao();
		int maxGroupNo = boardDao.getMaxGroup();

		BoardVo boardVo = new BoardVo();
		boardVo.setTitle(request.getParameter("title"));
		boardVo.setContents(request.getParameter("contents"));
		boardVo.setHit(0);
		boardVo.setDepth(1);
		boardVo.setgNo(maxGroupNo + 1);
		boardVo.setoNo(1);
		boardVo.setUserNo(authUser.getNo());

		try {
			boardDao.write(boardVo);
		} catch (Exception e) {
			System.err.println("Error writing to the board: " + e.getMessage());
			WebUtil.redirect(request.getContextPath() + "/board", request, response);
			return;
		}

		WebUtil.redirect(request.getContextPath() + "/board?a=board", request, response);
	}
}
