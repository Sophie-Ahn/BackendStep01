<%--
  Created by IntelliJ IDEA.
  User: seulkian
  Date: 3/29/24
  Time: 11:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="_54_DI_Dao.vo.Member"%>
<%--<%--%>
<%--    Member member = (Member)session.getAttribute("member");--%>
<%--%>--%>
<div style="background-color:#00008b;color:white;height:20px;padding:5px;">
    SPMS(Simple Project Management System)

    <c:if test="${!empty sessionScope.member and !empty sessionScope.member.email }">

		<span style="float:right">${sessionScope.member.name }
			<a style="color:white;" href="<%=request.getContextPath() %>/authDi/logout.do">로그아웃</a>
		</span>

    </c:if>
</div>
