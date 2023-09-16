package com.poscodx.mysite.web.mvc.board;


import com.poscodx.mysite.dao.BoardDao;
import com.poscodx.mysite.vo.BoardVo;
import com.poscodx.mysite.web.utils.WebUtil;
import com.poscodx.web.mvc.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BoardRelpyFromAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long no = Long.valueOf(request.getParameter("no"));
		BoardVo vo = new BoardDao().findByNo(no);
		request.setAttribute("vo", vo);
		WebUtil.forward("board/reply", request, response);
	}
}
