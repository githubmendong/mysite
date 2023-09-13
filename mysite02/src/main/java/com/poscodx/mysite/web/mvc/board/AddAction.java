package com.poscodx.mysite.web.mvc.board;

import com.poscodx.mysite.dao.BoardDao;
import com.poscodx.mysite.vo.BoardVo;
import com.poscodx.web.mvc.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AddAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("a");
		String suserno = request.getParameter("no");
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");

		Long hit = 0L;
		Integer groupNo = 1;
		Integer orderNo = 1;
		Integer depth = 0;
		System.out.println("1");
		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContents(contents);
		vo.setHit(hit);
		vo.setGroupNo(groupNo);
		vo.setOrderNo(orderNo);
		vo.setDepth(depth);
		new BoardDao().write(vo);
		response.sendRedirect(request.getContextPath() + "/board");

		List<BoardVo> list = new BoardDao().findAll();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/board/list.jsp").forward(request, response);
	}
}
