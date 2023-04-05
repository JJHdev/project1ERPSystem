<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="description" content="member board Web Application">
  <meta name="keywords" content="member, board, article, mvc">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/tableTop.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/buttonSubmit.css">
  
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<style>

 #memberFrm td, #memberFrm th{
  border: 1px solid #ddd;
  padding: 5px;
 }
 #memberFrm th{
  text-align:right;
  background-color:#555;
  color:white;
 }
 .error1 {color:Red;}
 .search{ float: inherit;}
 
</style>
<script>
 	$(document).ready(function(){
 		//게시물출력수 선택
 		//<select name="rowSize" id="rowSize">에 change이벤트
 		$("#rowSize").change(function(){
 			//let val = $("select#rowSize option:selected").val();
 			$("#rowSizeFrm").submit();
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
   
<h3>사원삭제</h3>
 
 <form name="rowSizeFrm" id="rowSizeFrm" action="<%=request.getContextPath()%>/mypagedeletelist.aa" method="get"> 
   게시물수:
  <select name="rowSize" id="rowSize">
		<option value="3">선택</option>
		<option value="3">3</option>
		<option value="5">5</option>
		<option value="10">10</option>
  </select>
 </form> 
 
 <table>
  <thead>
  	<tr>
  	 <th width="12%">삭제할 사원</th>
  	 <th width="12%">사원번호</th>
  	 <th width="19%">사원이름</th>
  	 <th width="19%">부서번호</th>
  	 <th width="19%">직급</th>
  	 <th width="19%">입사일</th>
  	</tr>
  </thead>
  
  <tbody>
   <c:if test="${deletepage.hasNoMypageDelete()}">
   <tr>
  	 <td colspan="6" style="text-align:center;">사원이 없습니다.</td>
   </tr>
   </c:if>
   
   <c:if test="${deletepage.hasMypageDelete()}">
   <form name="frm" id="frm" action="<%=request.getContextPath()%>/mypagedeleteshow.aa">
   <input type="submit" name="operate" value="삭제하기" class="w-btn w-btn-gray" >
   <span class="search">
   <input type="search" name="mypagesearch" id="mypagesearch">
   <input type="submit" name="operate" value="사원번호로 찾기" class="w-btn w-btn-gray" >
   </span>
   <c:forEach  var="item" items="${deletepage.content}">
 	 <tr>
 	  <td width="12%">
			<input type="checkbox" name="delempno" id="delempno" value="${item.empNo}" onclick="selectAllf(this)"/><label for="selectAll"></label><br/>
 	  </td>
 	  <td width="12%">${item.empNo}</td>
 	  <td width="19%">${item.eName}</td>
 	  <td width="19%">${item.dName}</td>
 	  <td width="19%">${item.empRank}</td>
 	  <td width="19%">
 	      <fmt:formatDate pattern="yyyy.MM.dd" value="${item.hiredate}"></fmt:formatDate><br/>
 	   </td>
 	 </tr>
 	 </c:forEach>
 	 </form> 
 	 </c:if>
 	 
   <tr>
  	<td colspan="6" style="text-align:center;">
     <%-- JSTL if조건문: 이전출력 --%>
     <c:if test="${deletepage.startPage>5}">
       <a href="<%=request.getContextPath()%>/mypagedeletelist.aa?pageNo=${deletepage.startPage-5}&rowSize=${rsize}">prev</a>
     </c:if>  
     <%-- JSTL forEch조건문: 페이지번호출력 --%>  
     <c:if test="${deletepage.total!=1}">
     <c:forEach var="pNo"                       
     			begin="${deletepage.startPage}" 
     			end="${deletepage.endPage}">
      <a href="<%=request.getContextPath()%>/mypagedeletelist.aa?pageNo=${pNo}&rowSize=${rsize}">${pNo}</a> 
     </c:forEach>  
     </c:if>  
                                      
     <%-- JSTL if조건문: 다음출력 --%>  
     <c:if test="${deletepage.endPage<deletepage.totalPages}">
       <a href="<%=request.getContextPath()%>/mypagedeletelist.aa?pageNo=${deletepage.startPage+5}&rowSize=${rsize}">next</a>
     </c:if> 
   </td>
   </tr>
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