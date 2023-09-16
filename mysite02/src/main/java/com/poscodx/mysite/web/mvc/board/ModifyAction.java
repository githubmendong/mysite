package com.poscodx.mysite.web.mvc.board;

import com.poscodx.mysite.dao.BoardDao;
import com.poscodx.mysite.vo.BoardVo;
import com.poscodx.mysite.web.utils.WebUtil;
import com.poscodx.web.mvc.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("no");

		String boardNo = request.getParameter("no");
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");

		BoardVo vo = new BoardVo();
		vo.setNo(Long.parseLong(boardNo));
		vo.setTitle(title);
		vo.setContents(contents);

		new BoardDao().updateView(Long.parseLong(no) , title , contents);
		request.setAttribute("BoardVo", vo);
		WebUtil.redirect(request.getContextPath() + "/board?a=board", request, response);
	}

}
