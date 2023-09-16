<%@ page import="com.poscodx.mysite.vo.GuestBookVo" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
    <title>mysite</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet">

</head>
<body>

<div id="container">
    <%@ include file="/WEB-INF/views/includes/header.jsp" %>

    <div id="content">
        <div id="board">
            <form id="search_form"
                  action="${pageContext.request.contextPath}/board"
                  method="post">

                <input type='text' id='kwd' name='kwd' value='${kwd}'/>
                <input type='submit' value='찾기'/>

            </form>

            <table class='tbl-ex'>
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>글쓴이</th>
                    <th>조회수</th>
                    <th>작성일</th>
                    <th>&nbsp;</th>
                </tr>

                <c:set var="count" value="${fn:length(list)}"/>

                <c:forEach items="${list}" var="vo" varStatus="status" begin="0">
                    <tr>
                        <td><c:choose><c:when
                                test="${curPageNum eq 0}">${status.index + 1}</c:when><c:otherwise>${status.index + (curPageNum * 5) - 4}</c:otherwise></c:choose></td>

                        <td style="text-align:left; padding-left:${vo.depth * 20}px;">
                            <c:choose>
                                <c:when test="${vo.depth eq 1}">
                                    <a href="${pageContext.request.contextPath}/board?a=view&no=${vo.no}">${vo.title}</a>
                                </c:when>
                                <c:otherwise>
                                    <img src='${pageContext.servletContext.contextPath}/assets/images/reply.png'/>
                                    <a href="${pageContext.request.contextPath}/board?a=view&no=${vo.no}">${vo.title}</a>
                                </c:otherwise>
                            </c:choose>

                        </td>

                        <td>${vo.userName}</td>
                        <td>${vo.hit}</td>
                        <td>${vo.regDate}</td>

                            <%-- 삭제 링크에 대한 조건문 --%>

                            <%-- 로그인 사용자와 글 작성자가 동일한 경우에만 삭제 링크를 표시합니다. --%>

                        <c:set var="isSameUser" value="${authUser.no eq vo.userNo}"/>
                        <c:set var="isAuthUser" value="${not empty authUser}"/>

                        <c:choose>
                            <c:when test="${isSameUser && isAuthUser}">
                                <td><a href="${pageContext.request.contextPath}/board?a=delete&no=${vo.no}" class="del">삭제</a>
                                </td>
                            </c:when>
                        </c:choose>

                    </tr>
                </c:forEach>

            </table>

            <%-- pager 추가 --%>

            <div class="pager">
                <ul>

                    <%-- 이전 페이지로 이동하는 링크 --%>
                    <%-- kwd 파라미터가 있을 경우에는 kwd 파라미터를 함께 전달합니다. --%>

                    <c:if test="${curPageNum > 5 && !empty kwd}">
                        <li>
                            <a href="${pageContext.request.contextPath}/board?&a=board&page=${groupStartNum - 1}&kwd=${kwd}">◀</a>
                        </li>
                    </c:if>

                    <%-- 일반적인 이전 페이지로 이동하는 링크 --%>

                    <c:if test="${curPageNum > 5}">
                        <li><a href="${pageContext.request.contextPath}/board?a=board&page=${groupStartNum - 1}">◀</a>
                        </li>
                    </c:if>


                    <%-- 페이지 번호 출력 --%>

                        <c:forEach var="i" begin="${groupStartNum}" end="${groupLastNum}">
                            <li>
                                <c:choose>
                                    <c:when test="${i > lastPageNum}">
                                        ${i}
                                    </c:when>
                                    <c:when test="${i == curPageNum}">
                                        <a href="${pageContext.request.contextPath}/board?a=board&page=${i}&kwd=${fn:escapeXml(kwd)}" class="selected">${i}</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="${pageContext.request.contextPath}/board?a=board&page=${i}&kwd=${fn:escapeXml(kwd)}">${i}</a>
                                    </c:otherwise>
                                </c:choose>
                            </li>
                        </c:forEach>

                    <%-- 다음 페이지로 이동하는 링크 --%>
                    <%-- kwd 파라미터가 있을 경우에는 kwd 파라미터를 함께 전달합니다. --%>

                    <c:if test="${lastPageNum > groupLastNum && !empty kwd}">
                        <li>
                            <a href="${pageContext.request.contextPath}/board?a=search&page=${groupLastNum + 1}&kwd=${kwd}">▶</a>
                        </li>
                    </c:if>

                    <%-- 일반적인 다음 페이지로 이동하는 링크 --%>

                    <c:if test="${lastPageNum > groupLastNum}">
                        <li><a href="${pageContext.request.contextPath}/board?a=board&page=${groupLastNum + 1}">▶</a>
                        </li>
                    </c:if>
                </ul>
            </div>

            <%-- 로그인 사용자인 경우에만 글쓰기 링크를 표시합니다. --%>

            <c:choose>
                <c:when test="${isAuthUser}">
                    <div class="bottom">
                        <a href="${pageContext.request.contextPath}/board?a=writeform&no=${authUser.no}" id="new-book">글쓰기</a>
                    </div>
                </c:when>
            </c:choose>

        </div>
    </div>

    <%@ include file="/WEB-INF/views/includes/navigation.jsp" %>
    <%@ include file="/WEB-INF/views/includes/footer.jsp" %>

</div>
</body>
</html>
