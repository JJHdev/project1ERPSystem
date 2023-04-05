<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="description" content="member board Web Application">
  <meta name="keywords" content="member, board, article, mvc">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<style></style>
<script>
</script>
</head>
<body>
 
<table width="95%" border="1" cellpadding="0" cellspacing="0">
<tr>
  <td colspan="16">
      <jsp:include page="../module/top.jsp" flush="false"/>
   </td>
</tr>
 
<tr>
  <td width="17%" valign="top">
      <jsp:include page="../module/left.jsp" flush="false"/>
   </td>
   <td width="78%" valign="top">
   
   
   
   
   
   
       <!--  내용 부분 : 시작 -->
       <c:if test="${cnt==1}">
		       <script>       
							alert("삭제성공");
						</script>
				</c:if>
       <c:if test="${cnt==0}">
		       <script>       
						alert("삭제실패");
						</script>
				</c:if>
				
				<script>
				location.href="<%=request.getContextPath() %>/hpdesk/listhpdesk.aa"; 
				</script>
       
       <br><br><br>    
       <!-- 내용 부분: 끝 -->
       </td>
       
       
       
       
       
       
</tr>
<tr>
  <td colspan="16">
    <jsp:include page="../module/bottom.jsp" flush="false"/>
  </td>
</tr>
</table>
 
</body>
</html>