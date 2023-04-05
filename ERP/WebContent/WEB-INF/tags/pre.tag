<%@ tag body-content="empty" language="java" pageEncoding="UTF-8"%>
<%@ tag trimDirectiveWhitespaces="true" %>
<%@ attribute name="value" type="java.lang.String" required="true"%>
<%
	value = value.replace("\n","<br>");
	value = value.replace("&","&amp;");

%>
<%=value%>