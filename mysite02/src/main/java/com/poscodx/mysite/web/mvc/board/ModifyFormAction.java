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

public class ModifyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String boardNo = request.getParameter("no");
		BoardDao boardDao = new BoardDao();
		BoardVo boardVo;

		try {
			boardVo = boardDao.findByNo(Long.parseLong(boardNo));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			WebUtil.redirect(request.getContextPath() + "/board", request, response);
			return;
		}

		if (!BoardVo.userHasPermission(request.getSession(), boardVo)) {
			WebUtil.redirect(request.getContextPath() + "/board", request, response);
        }

	}
}
