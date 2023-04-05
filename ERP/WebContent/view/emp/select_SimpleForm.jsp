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
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/tableTop.css?after"><!-- 테이블 -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/buttonSubmit.css?after"><!--버튼  -->
<title>ERP인사관리</title>
<script src="https://code.jquery.com/jquery-2.2.4.js" ></script>
<style>
  tr.t1:hover td{
		background-color:#f3e9e9 !important;
		color:#303f39 !important;
	}
</style>
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
	<h2>기본조회</h2>
	<c:if test="${empty selectEmpPage.selectEmp}">
		<table>
			<thead> 
				<tr>
					<th>해당하는 사원이 없습니다.</th>
				</tr>
			</thead> 
		</table>
	</c:if>
  <input type="hidden" name="rowSize" id="rowSize" />
	<c:if test="${!empty selectEmpPage.selectEmp}">
		<table>
			<thead>
				<tr>
					<th>사원번호</th><th>부서번호</th>
					<th>부서명</th><th>사원명</th><th>직급</th>
					<th>이메일</th><th>전화번호</th><th>입사일</th>
						<c:if test="${EMP_USER.grade eq 999}" >
							<th>상세조회</th> 
						</c:if> 
				</tr>
			</thead>
		<tbody>
	 		<c:forEach var="selectEmp" items="${selectEmpPage.selectEmp}">
				<tr class="t1">
					<td><c:out value="${selectEmp.empno}"/></td>
					<td><c:out value="${selectEmp.deptno}"/></td>
					<td><c:out value="${selectEmp.deptname}"/></td>
					<td><c:out value="${selectEmp.ename}"/></td>
					<td><c:out value="${selectEmp.erprank}"/></td>
					<td><c:out value="${selectEmp.email}"/></td>
					<td><c:out value="${selectEmp.tel}"/></td>
					<td><fmt:formatDate pattern="yyyy-MM-dd" value="${selectEmp.hiredate}" /></td>
	 					<c:if test="${EMP_USER.grade eq 999}" > 
							<td><input type="button" value="상세조회" class="w-btn w-btn-gray" 
							onclick="location.href='/selectEmp_Detail.aa?empno=${selectEmp.empno}'"></td>
						</c:if> 
				</tr>
			</c:forEach>
		</tbody>	
			<tr>
				<c:if test="${EMP_USER.grade eq 999}" >
					<td colspan="10" style="text-align:center;">
				</c:if>
				<c:if test="${EMP_USER.grade != 999}" >
					<td colspan="9" style="text-align:center;">
				</c:if>
				<c:if test="${selectEmpPage.startPage>5}">
					<a href="/selectEmp_Simple.aa?pageNo=${selectEmpPage.startPage-5}&rowSize=${size}&deptname=${deptname}&erprank=${erprank}&ename=${ename}">prev</a>
				</c:if>
				<c:forEach var="pNo"
					begin="${selectEmpPage.startPage}" 
				 	end="${selectEmpPage.endPage}">
					<a href="/selectEmp_Simple.aa?pageNo=${pNo}&rowSize=${size}&deptname=${deptname}&erprank=${erprank}&ename=${ename}">${pNo}</a>
				</c:forEach>
				<c:if test="${selectEmpPage.endPage<selectEmpPage.totalPages}">
					<a href="/selectEmp_Simple.aa?pageNo=${selectEmpPage.startPage+5}&rowSize=${size}&deptname=${deptname}&erprank=${erprank}&ename=${ename}">next</a>
				</c:if>
				</td>
			</tr>
		</table>
	</c:if>

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
