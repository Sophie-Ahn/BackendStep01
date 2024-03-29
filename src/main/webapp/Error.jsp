<%--
  Created by IntelliJ IDEA.
  User: seulkian
  Date: 3/29/24
  Time: 10:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>시스템오류</title>
</head>
<body>
    <%
        Exception error = (Exception)request.getAttribute("error");
        String err = error.getMessage();
    %>
    <p>Error: <%=err %></p>
    <p>
        요청을 처리하는 중에 문제가 발생하였습니다.<br>
        잠시 후에 다시 요청 부탁드립니다.<br>
        계속 문제가 생긴다면 시스템 운영팀(02-1111-2222)에 연락하시길 바랍니다.
    </p>
</body>
</html>
