<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="description" content="member board Web Application with MVC">
<meta name="keywords" content="member, board, aeticle, mvc">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/tableTop.css"><!-- 테이블 -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/buttonSubmit.css"><!--버튼  -->
<title>ERP인사관리</title>
<script src="https://code.jquery.com/jquery-2.2.4.js" ></script>
  <style>
  </style>
  <script>
  $(document).ready( function(){
	  $("#btn2").click( function() {
			if(confirm("삭제하시겠습니까?") === true) {
				location.href='/selectEmp_Delete.aa?empno=${SelectEmp.empno}';
       	alert("삭제하였습니다.")
	    }
		});
  });
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
   
		<form action="/selectEmp_Modify.aa" method="get">
			<h2>상세조회</h2>
			<table>
				<thead>
					<tr>
						<th>사원번호</th><th>비밀번호</th><th>부서번호</th><th>부서명</th>
						<th>사원명</th><th>직급</th><th>이메일</th>
						<th>전화번호</th><th>입사일</th><th>연봉</th>
						<th>권한등급</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><c:out value="${SelectEmp.empno}"/></td>
						<td><c:out value="${SelectEmp.emppwd}"/></td>
						<td><c:out value="${SelectEmp.deptno}"/></td>
						<td><c:out value="${SelectEmp.deptname}"/></td>
						<td><c:out value="${SelectEmp.ename}"/></td>
						<td><c:out value="${SelectEmp.erprank}"/></td>
						<td><c:out value="${SelectEmp.email}"/></td>
						<td><c:out value="${SelectEmp.tel}"/></td>
						<td><fmt:formatDate pattern="yyyy-MM-dd" value="${SelectEmp.hiredate}" /></td>
						<td><c:out value="${SelectEmp.sal}"/></td>
						<td><c:out value="${SelectEmp.grade}"/></td>
						<input type="text" name = "empno" value="${SelectEmp.empno}" hidden="hidden" />
					</tr>
					<tr>
						<td colspan="11"><input type="submit" value="수정" class="w-btn w-btn-gray"/>
						<input type="button" value="삭제" id="btn2" name="btn2" class="w-btn w-btn-gray"/>
					</tr>
				</tbody>
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