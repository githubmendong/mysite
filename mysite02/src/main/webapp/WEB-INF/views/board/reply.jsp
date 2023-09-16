<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <%@ include file="/WEB-INF/views/includes/header.jsp"%>

    <div id="content">
        <div id="board">
            <form class="board-form"
                  method="post"
                  action="${pageContext.request.contextPath}/board?a=modify">

                <input type='hidden' name='no' value='${vo.no}' />
                <input type='hidden' name='gNo' value='${vo.gNo}' />
                <input type='hidden' name='oNo' value='${vo.oNo}' />
                <input type='hidden' name='depth' value='${vo.depth}' />

                <table class="tbl-ex">
                    <tr>
                        <th colspan=2>글보기</th>
                    </tr>
                    <tr>
                        <td class="label">제목</td>
                        <td><input type="text" name="title" value="${fn:escapeXml(vo.title)}"></td>
                    </tr>
                    <tr>
                        <td class="label">내용</td>
                        <td><textarea id="content" name="contents">${fn:escapeXml(vo.contents)}</textarea></td>
                    </tr>
                </table>

                <div class="bottom">
                    <a href="${pageContext.request.contextPath}/board?a=board">취소</a>
                    <input type='submit' value='수정' />
                </div>

            </form>

        </div>

    </div>

    <%@ include file="/WEB-INF/views/includes/navigation.jsp"%>
    <%@ include file="/WEB-INF/views/includes/footer.jsp"%>

</div>

</body>
</html>
