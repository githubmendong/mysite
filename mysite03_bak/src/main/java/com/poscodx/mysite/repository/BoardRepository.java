package com.poscodx.mysite.repository;

import com.poscodx.mysite.vo.BoardVo;
import com.poscodx.mysite.vo.GuestbookVo;
import com.poscodx.mysite.vo.UserVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BoardRepository {


    @Autowired
    private SqlSession sqlSession;

    public List<BoardVo> findAll() {
        return sqlSession.selectList("guestbook.findAll");
    }




    public static int getIntParameter(HttpServletRequest request, String paramName) {
        String paramValue = request.getParameter(paramName);
        try {
            return Integer.parseInt(paramValue);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static void handleSameDepthCase(int gNo, int depth) {
        System.out.println("Handling the case of same depth");
    }

//    public static boolean hasSameOrPlus1Depth(int gNo, int depth) {
////        int countSameDepth = BoardDao.findHasSameDepth(gNo, depth);
////        int countPlus1Depth = BoardDao.findHasPlus1Depth(gNo, depth);
////        return countSameDepth != 0 || (countPlus1Depth != 0 && countSameDepth == 0);
//    }

    public static boolean userHasPermission(HttpSession session, BoardVo boardVo) {
        UserVo authUser = (UserVo) session.getAttribute("authUser");

        if (authUser == null) {
            return false;
        }

        Long loggedInUserNo = authUser.getNo();
        Long boardWriterNo = boardVo.getUserNo();

        return loggedInUserNo.equals(boardWriterNo);
    }

}