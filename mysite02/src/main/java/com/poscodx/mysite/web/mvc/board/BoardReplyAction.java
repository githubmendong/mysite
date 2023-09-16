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

import static com.poscodx.mysite.vo.BoardVo.*;


public class BoardReplyAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserVo authUser = (UserVo) session.getAttribute("authUser");

        int gNo = getIntParameter(request, PARAM_G_NO);
        int oNo = getIntParameter(request, PARAM_O_NO);
        int depth = getIntParameter(request, PARAM_DEPTH);
        String title = request.getParameter(PARAM_TITLE);
        String content = request.getParameter(PARAM_CONTENTS);

        BoardVo vo = new BoardVo();

        if (depth == INITIAL_DEPTH) {
            vo.setDepth(INITIAL_DEPTH + 1);
        } else {
            BoardVo.handleSameDepthCase(gNo, depth);
        }

        if (hasSameOrPlus1Depth(gNo, depth)) {
            new BoardDao().setOrder(gNo, oNo, depth);
            vo.setoNo(oNo + 1);
        } else {
            vo.setoNo(oNo + 1);
        }

        vo.setTitle(title);
        vo.setContents(content);
        vo.setHit(0);
        vo.setgNo(gNo);
        vo.setDepth(depth + 1);
        vo.setUserNo(authUser.getNo());

        new BoardDao().write(vo);

        WebUtil.redirect(request.getContextPath() + "/board?a=board", request, response);
    }


}
