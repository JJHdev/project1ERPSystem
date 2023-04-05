<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="description" content="ConferenceRoom reservation">
  <meta name="keywords" content="room,resv">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/buttonSubmit.css">
<title>회의실 예약 확인하기</title>
 <script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<style>
tr.t1:hover td{
        background-color:#f3e9e9 !important;
        color:#303f39 !important;
        }
body {
  padding:1.5em;
  background: #f5f5f5
}

table {
  border: 1px #a39485 solid;
  font-size: 1em;
  box-shadow: 0 2px 5px rgba(0,0,0,.25);
  width: 100%;
  border-collapse: collapse;
  border-radius: 5px;
  overflow: hidden;
}

th {
  text-align: left;
}
  
thead {
  font-weight: bold;
  color: #fff;
  background: #73685d;
}
  
 td, th {
  padding: 1em .5em;
  vertical-align: top;
}

.td1{
	vertical-align: middle;
}
.td1:hover{
	background-color: #fff5f5;
	vertical-align: middle;
}
  
 td {
  border-bottom: 1px solid rgba(0,0,0,.1);
  background: #fff;
}

a {
  color: #73685d;
}
  
 @media all and (max-width: 768px) {
    
  table, thead, tbody, th, td, tr {
    display: block;
  }
  
  th {
    text-align: right;
  }
  
  table {
    position: relative; 
    padding-bottom: 0;
    border: none;
    box-shadow: 0 0 10px rgba(0,0,0,.2);
  }
  
  thead {
    float: left;
    white-space: nowrap;
  }
  
  tbody {
    overflow-x: auto;
    overflow-y: hidden;
    position: relative;
    white-space: nowrap;
  }
  
  tr {
    display: inline-block;
    vertical-align: top;
  }
  
  th {
    border-bottom: 1px solid #a39485;
  }
  
  td {
    border-bottom: 1px solid #e5e5e5;
  }
  }

</style>
<script>
	$(document).ready(function(){

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
   
   <h2>${EMP_USER.ename}님의 회의실 예약정보</h2>
<%-- ${resvList} --%>
	<sup>최근 예약스케줄 순으로 정렬됩니다</sup>
	<table>
	<thead>
		 	<tr>
		 		<th>예약번호</th>
		 		<th>예약일</th>
		 		<th>회의실</th>
		 		<th>예약관련메모</th>
		 		<th>사용자</th>
		 		<th>사용자 연락처</th>
		 		<th>사용자 이메일</th>
		 		<th>예약수정</th>
		 		<th>예약삭제</th>
		 	</tr>
		</thead>
		<tbody>
			<%-- 게시글이 없는 경우 --%>
		 	<%-- JSTL if조건문 이용하여 출력 --%>
		 	<c:if test="${empty resvList}">
		 	<tr>
	  		<td colspan="10" style="text-align:center;">예약한 회의실이 없습니다</td>
  		</tr>
		 	</c:if>
		 	
		 	<%-- 게시글이 있는 경우 --%>
		 	<c:if test="${not empty resvList}">
			<c:forEach var="item" items="${resvList}">
  		<tr class="t1">
	  		<td class="td1">R100${item.resvno}</td>
	  		<td class="td1">${item.resvdate}</td>
	  		<td class="td1">${item.roomname}</td>
	  		<td class="td1">${item.resvmemo}</td>
	  		<td class="td1">${item.resvname}</td>
	  		<td class="td1">${item.resvtel}</td>
	  		<td class="td1">${item.resvemail}</td>
	  		<td><button class="w-btn w-btn-gray" onclick="location.href='<%=request.getContextPath()%>/resvUpdate.aa?empno=${empno}&resvno=${item.resvno}&resvdate=${item.resvdate}&roomname=${item.roomname}&resvmemo=${item.resvmemo}&resvname=${item.resvname}&resvtel=${item.resvtel}&resvemail=${item.resvemail}'">수정</button></td>
		 		<td><button class="w-btn w-btn-gray" onclick="location.href='<%=request.getContextPath()%>/resvDelete.aa?resvno=${item.resvno}'">삭제</button></td>
  		</tr>
			</c:forEach>
			</c:if>
	  	<%-- 반복문이용하여 출력끝 --%>
  	</tbody>

</table>   
  
       
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