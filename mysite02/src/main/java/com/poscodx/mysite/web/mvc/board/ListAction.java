package com.poscodx.mysite.web.mvc.board;

import com.poscodx.mysite.dao.BoardDao;
import com.poscodx.mysite.dao.GuestBookDao;
import com.poscodx.mysite.vo.BoardVo;
import com.poscodx.mysite.vo.GuestBookVo;
import com.poscodx.mysite.web.utils.WebUtil;
import com.poscodx.web.mvc.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class ListAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<BoardVo> list = new BoardDao().findAll();
		
		request.setAttribute("list", list);
		WebUtil.forward("board/list", request, response);
	}
}
