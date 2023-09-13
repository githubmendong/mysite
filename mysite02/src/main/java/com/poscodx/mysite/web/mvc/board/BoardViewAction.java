package com.poscodx.mysite.web.mvc.board;


import com.poscodx.mysite.dao.BoardDao;
import com.poscodx.mysite.vo.BoardVo;
import com.poscodx.mysite.web.utils.WebUtil;
import com.poscodx.web.mvc.Action;
import com.poscodx.web.mvc.ActionFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class BoardViewAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sno = request.getParameter("no");
        Long no = Long.parseLong(sno);

        BoardVo vo = new BoardDao().findByNo(no);

        request.setAttribute("BoardVo", vo);
        request.getRequestDispatcher("WEB-INF/views/board/view.jsp").forward(request, response);

    }
}

