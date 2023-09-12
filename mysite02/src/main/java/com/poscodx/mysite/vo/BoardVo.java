package com.poscodx.mysite.vo;

public class BoardVo {
    private Long no;
    private String title;
    private String contents;
    private int hit;
    private String regDate;
    private Integer groupNo;
    private Integer orderNo;
    private Integer depth;
    private Long userNo;

    public BoardVo(Long no) {
        this.no = no;
    }
}
