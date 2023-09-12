<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시판 페이징처리</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js" integrity="sha512-aVKKRRi/Q/YV+4mjoKBsE4x3H+BkegoM/em46NNlCqNTmUYADjBbeNefNxYV7giUp0VxICtqdrbqU7iVaeZNXA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div>
    시작 버튼 : ${p.startPage}
    마지막 버튼 : ${p.endPage}
</div>

<div class="jumbotron">
    <h1>페이징 처리 연습</h1>

</div>
<div class="container">
    <table class="table">
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
        </tr>
        <c:forEach items="${list}" var="b">
            <tr>
                <td>${b.bno}</td>
                <td>${b.title}</td>
                <td>${b.writer}</td>
                <td>${b.writedate}</td>
            </tr>
        </c:forEach>
    </table>
    <ul class="pagination d-flex justify-content-center">
        <c:if test="${p.prev}">
            <li class="page-item"><a class="page-link" href="?pageNum =${p.startPage-1}">Previous</a></li>
        </c:if>

        <c:forEach begin="${p.startPage}" end="${p.endPage}" var="pageBtn">
            <li class="page-item ${pageBtn==param.pageNum?'active' : ''}">
                <a class="page-link" href="?pageNum=${pageBtn}">${pageBtn}</a>
            </li>
        </c:forEach>
        <c:if test="${p.next}">
            <li class="page-item"><a class="page-link" href="?pageNum=${p.endPage+1}">Next</a></li>
        </c:if>
    </ul>
</div>
현재페이지 : ${param.pageNum}

</body>
</html>