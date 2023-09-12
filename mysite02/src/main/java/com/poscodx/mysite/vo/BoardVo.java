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
    private String userName;
    /**
     * @return the no
     */
    public Long getNo() {
        return no;
    }
    /**
     * @param no the no to set
     */
    public void setNo(Long no) {
        this.no = no;
    }
    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }
    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * @return the contents
     */
    public String getContents() {
        return contents;
    }
    /**
     * @param contents the contents to set
     */
    public void setContents(String contents) {
        this.contents = contents;
    }
    /**
     * @return the hit
     */
    public int getHit() {
        return hit;
    }
    /**
     * @param hit the hit to set
     */
    public void setHit(int hit) {
        this.hit = hit;
    }
    /**
     * @return the regDate
     */
    public String getRegDate() {
        return regDate;
    }
    /**
     * @param regDate the regDate to set
     */
    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }
    /**
     * @return the groupNo
     */
    public Integer getGroupNo() {
        return groupNo;
    }
    /**
     * @param groupNo the groupNo to set
     */
    public void setGroupNo(Integer groupNo) {
        this.groupNo = groupNo;
    }
    /**
     * @return the orderNo
     */
    public Integer getOrderNo() {
        return orderNo;
    }
    /**
     * @param orderNo the orderNo to set
     */
    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }
    /**
     * @return the depth
     */
    public Integer getDepth() {
        return depth;
    }
    /**
     * @param depth the depth to set
     */
    public void setDepth(Integer depth) {
        this.depth = depth;
    }
    /**
     * @return the userNo
     */
    public Long getUserNo() {
        return userNo;
    }
    /**
     * @param userNo the userNo to set
     */
    public void setUserNo(Long userNo) {
        this.userNo = userNo;
    }
    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }
    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    @Override
    public String toString() {
        return "BoardVo [no=" + no + ", title=" + title + ", contents=" + contents + ", hit=" + hit + ", regDate="
                + regDate + ", groupNo=" + groupNo + ", orderNo=" + orderNo + ", depth=" + depth + ", userNo=" + userNo
                + ", userName=" + userName + "]";
    }

}