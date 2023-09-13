<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<!DOCTYPE html>
<html>
<head>
    <title>mysite</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="container">
    <c:import url="/WEB-INF/views/includes/header.jsp"/>

    <div id="content">
        <div id="board" class="board-form">
            <table class="tbl-ex">
                <tr>
                    <th colspan="2">글보기</th>
                </tr>
                <tr>
                    <td class="label">제목</td>
                    <td>${BoardVo.title }</td>
                </tr>
                <tr>
                    <td class="label">내용</td>
                    <td>
                        <div class="view-content">
                            ${BoardVo.contents }
                        </div>
                    </td>
                </tr>
            </table>
            <div class="bottom">
<%--                로그인 해야 사용가능--%>
                <a href="${pageContext.request.contextPath }/board">글목록</a>
                <c:if test="${authUser.no == BoardVo.userNo }">
                    <a href="${pageContext.request.contextPath }/board">글수정</a>
                </c:if>
            </div>
        </div>
    </div>
    <c:import url = "/WEB-INF/views/includes/navigation.jsp" />
    <c:import url = "/WEB-INF/views/includes/footer.jsp" />
</div>
</body>
</html>