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
        Long no = Long.parseLong(request.getParameter("no"));
        BoardVo boardvo = new BoardDao().findByNo(no);
        new BoardDao().updateHit(no);

        request.setAttribute("title", boardvo.getTitle());
        request.setAttribute("contents", boardvo.getContents());
        request.setAttribute("no", no);
        request.setAttribute("userNo", boardvo.getUserNo());
        WebUtil.forward("board/view", request, response);

    }
}

