<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="description" content="member board Web Application with MVC">
<meta name="keywords" content="member, board, aeticle, mvc">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/tableLeft.css"><!-- 테이블 -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/buttonSubmit.css"><!--버튼  -->
<title>ERP인사관리</title>
	<script src="https://code.jquery.com/jquery-2.2.4.js" ></script>
	
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
	
	<h2>사원조회</h2>
		<form action="/selectEmp_Simple.aa" id="selectFrm" name="selectFrm" method="post">
		<table border="1">
			<tr>
		 	  <th class="thead">부서명</th>
		 	  <td>
					<select name="deptname" id="deptname">
						<option value="all" disabled>선택</option>  
		 				<option value="all">모두</option>  
		 					<c:forEach var="deptname" items="${select_deptname}">
				 				<option value="${deptname.deptname}">${deptname.deptname}</option>
		 					</c:forEach>
					</select>		
		 	  </td>
			</tr>
			<tr>
		 	  <th class="thead">직급</th>
		 	  <td>
					<select name="erprank" id="erprank">
						<option value="all" disabled>선택</option>  
		 				<option value="all">모두</option>  
		 				<c:forEach var="erprank" items="${select_erprank}">
				 			<option value="${erprank.deptname}">${erprank.deptname}</option>
		 				</c:forEach>
					</select>		
				</td>
			</tr>
			<tr>
				<th class="thead">성명</th>
				<td>
					<input type="text" name="ename" id="ename" size="12"/>
					<input type="submit" value="조회" class="w-btn w-btn-gray"/>
		 	  </td>
			</tr>
		</table>
		</form>
		
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