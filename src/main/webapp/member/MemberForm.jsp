<%--
  Created by IntelliJ IDEA.
  User: seulkian
  Date: 3/29/24
  Time: 2:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원등록</title>
</head>
<body>
    <jsp:include page="/Header.jsp" />
    <h1>회원 등록</h1>
    <form action="add" method="post">
        이름: <input type='text' name='name'><br>
        이메일: <input type='text' name='email'><br>
        암호: <input type='password' name='password'><br>
        <input type='submit' value='추가'>
        <input type='reset' value='취소' onclick='location.href="list"'>
    </form>
    <jsp:include page="/Tail.jsp" />
</body>
</html>
