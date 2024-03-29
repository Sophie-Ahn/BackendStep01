<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: seulkian
  Date: 3/28/24
  Time: 5:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="_33_Question.vo.Member" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<html>
<head>
    <title>회원목록</title>
</head>
<body>

    <jsp:include page="/Header.jsp" />

    <h1>회원 목록</h1>
    <p><a href="add">신규회원</a></p>

<%--    되도록 jsp에서 java코드를 배제함으로써 이질감을 없애고, 일관성을 유지하게 하여 협업작업에서 불필요한 분노심을 방지하기 위함--%>
    <jsp:useBean id="members" scope="request" class="java.util.ArrayList" type="java.util.List<_33_Question.vo.Member>" />
    <%
//        list로 형변환
//        <jsp:useBean id="members" scope="request" class="java.util.ArrayList" type="java.util.List<_33_Question.vo.Member>" />

        for(Member member : members){
    %>
        <%=member.getNo() %>,
        <a href="update?no=<%=member.getNo() %>"><%=member.getName() %></a>,
        <%=member.getEmail() %>,
        <%=member.getCreateDate() %>
        <a href="delete?no=<%=member.getNo() %>">[삭제]</a><br>
    <%
        }
    %>

    <jsp:include page="/Tail.jsp" />
</body>
</html>
