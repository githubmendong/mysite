package com.poscodx.mysite.vo;

import com.poscodx.mysite.dao.BoardDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class BoardVo {
    public static final String PARAM_G_NO = "gNo";
    public static final String PARAM_O_NO = "oNo";
    public static final String PARAM_DEPTH = "depth";
    public static final String PARAM_TITLE = "title";
    public static final String PARAM_CONTENTS = "contents";
    public static final int INITIAL_DEPTH = 1;

    private Long no;
    private String title;
    private String contents;
    private String regDate;
    private int hit;
    private int gNo;
    private int oNo;
    private int depth;
    private Long userNo;
    private String userName;


    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public Long getNo() {
        return no;
    }
    public void setNo(Long no) {
        this.no = no;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContents() {
        return contents;
    }
    public void setContents(String contents) {
        this.contents = contents;
    }
    public String getRegDate() {
        return regDate;
    }
    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }
    public int getHit() {
        return hit;
    }
    public void setHit(int hit) {
        this.hit = hit;
    }
    public int getgNo() {
        return gNo;
    }
    public void setgNo(int gNo) {
        this.gNo = gNo;
    }
    public int getoNo() {
        return oNo;
    }
    public void setoNo(int oNo) {
        this.oNo = oNo;
    }
    public int getDepth() {
        return depth;
    }
    public void setDepth(int depth) {
        this.depth = depth;
    }
    public Long getUserNo() {
        return userNo;
    }
    public void setUserNo(Long userNo) {
        this.userNo = userNo;
    }
    @Override
    public String toString() {
        return "BoardVo [no=" + no + ", title=" + title + ", contents=" + contents + ", regDate=" + regDate + ", hit="
                + hit + ", gNo=" + gNo + ", oNo=" + oNo + ", depth=" + depth + ", userNo=" + userNo + ", userName="
                + userName + "]";
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

    public static boolean hasSameOrPlus1Depth(int gNo, int depth) {
        int countSameDepth = BoardDao.findHasSameDepth(gNo, depth);
        int countPlus1Depth = BoardDao.findHasPlus1Depth(gNo, depth);
        return countSameDepth != 0 || (countPlus1Depth != 0 && countSameDepth == 0);
    }

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