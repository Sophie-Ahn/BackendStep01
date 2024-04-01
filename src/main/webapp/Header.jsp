<%--
  Created by IntelliJ IDEA.
  User: seulkian
  Date: 3/29/24
  Time: 11:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="_37_MemberListServlet_Dao.vo.Member"%>
<%
    Member member = (Member)session.getAttribute("member");
%>
<div style="background: #00008b; color:#fff; height:20px;padding:5px;">
    SPMS(Simple Project Management System)
    <% if(member.getEmail() != null){%>
    <span style="float: right;"><%=member.getName() %>
        <a style="color: #fff;" href="<%=request.getContextPath() %>/authServletDao/logout">로그아웃</a>
    </span>
    <%}%>
</div>
