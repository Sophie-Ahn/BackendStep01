<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: seulkian
  Date: 3/28/24
  Time: 5:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="_27_MemberListServlet_Including.vo.Member" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>회원목록</title>
</head>
<body>
<%
//<jsp:forward page="/Forward.jsp" />
%>

    <jsp:include page="/Header.jsp" />

    <h1>회원 목록</h1>
    <p><a href="add">신규회원</a></p>
    <%
//        list로 형변환
        List<Member> members = (List<Member>)request.getAttribute("members");
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
