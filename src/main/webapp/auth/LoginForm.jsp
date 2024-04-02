<%--
  Created by IntelliJ IDEA.
  User: seulkian
  Date: 3/29/24
  Time: 12:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>로그인</title>
</head>
<body>
    <h2>사용자 로그인</h2>
    <form action="login.do" method="post">
        이메일: <input type="text" name="email">
        암호: <input type="password" name="password">
        <input type="submit" value="로그인">
    </form>
</body>
</html>
