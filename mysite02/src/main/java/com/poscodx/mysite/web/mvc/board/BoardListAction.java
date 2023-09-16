package com.poscodx.mysite.web.mvc.board;


import com.poscodx.mysite.dao.BoardDao;
import com.poscodx.mysite.vo.BoardVo;
import com.poscodx.mysite.vo.PaginationVo;
import com.poscodx.mysite.vo.UserVo;
import com.poscodx.mysite.web.utils.WebUtil;
import com.poscodx.web.mvc.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BoardListAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PaginationVo paging = new PaginationVo();

		String keyword = request.getParameter("kwd");
		if (keyword == null || keyword.isEmpty()) {
			keyword = "";
		}

		int currentPage = 0;
		String cp = request.getParameter("page");
		if (cp != null && !cp.equals("null")) {
			currentPage = Integer.parseInt(cp);
			paging.setGroup(currentPage);
		}

		BoardDao boardDao = new BoardDao();

		int pageSize = PaginationVo.getPagecount();

		paging.setLastPageNum(keyword);
		int firstPageItemIndex = currentPage * pageSize;
		if (currentPage != 0) {
			firstPageItemIndex -= pageSize;
		}

		List<BoardVo> list = boardDao.findAllSearch(keyword, firstPageItemIndex, pageSize);

		request.setAttribute("curPageNum", currentPage);
		request.setAttribute("groupStartNum", paging.getGroupStartNum());
		request.setAttribute("groupLastNum", paging.getGroupLastNum());
		request.setAttribute("lastPageNum", paging.getLastPageNum());
		request.setAttribute("list", list);
		request.setAttribute("kwd", keyword);

		WebUtil.forward("/board/list", request, response);
	}
}

