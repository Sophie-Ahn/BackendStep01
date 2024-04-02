<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: seulkian
  Date: 3/28/24
  Time: 5:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>회원목록</title>
</head>
<body>

    <jsp:include page="/Header.jsp" />

    <h1>회원 목록</h1>
    <p><a href="add.do">신규회원</a></p>

    <c:forEach var="member" items="${members}">
        ${member.no},
        <a href="update.do?no=${member.no}">${member.name}</a>,
        ${member.email}
        ${member.createDate}
        <a href="delete.do?no=${member.no}">[삭제]</a><br>
    </c:forEach>

    <jsp:include page="/Tail.jsp" />
</body>
</html>
