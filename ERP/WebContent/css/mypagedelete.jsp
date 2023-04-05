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
<title>Insert title here</title>
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
 .c1 {width:120px;}
 
 .error1 {color:Red;}
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


 <h3>사원삭제</h3>
 
 <form name="rowSizeFrm" id="rowSizeFrm" 
       action="/ERP/mypagedeletelist.do" method="get"> 
   게시물수:
  <select name="rowSize" id="rowSize">
		<option value="3">선택</option>
		<option value="3">3</option>
		<option value="5">5</option>
		<option value="10">10</option>
  </select>
 </form> 
 
 
 <table border="1">
  <thead>
  	<tr>
  	 <th>삭제할 사원</th>
  	 <th>사원번호</th>
  	 <th>사원이름</th>
  	 <th>부서이름</th>
  	 <th>직급</th>
  	 <th>입사일</th>
  	</tr>
  </thead>
  
  
  
  <tbody>
   <c:if test="${deletepage.hasNoMypageDelete()}">
   <tr>
  	 <td colspan="6" style="text-align:center;">사원이 없습니다.</td>
   </tr>
   </c:if>
    
   
   <c:if test="${deletepage.hasMypageDelete()}">
   <form name="frm1" id="frm1" action="/ERP/mypagedelete.do">
   <input type="submit" value="삭제하기">
   <c:forEach  var="item" items="${deletepage.content}">
 	 <tr>
 	  <td>
			<input type="checkbox" name="delempno" id="delempno" value="${item.empNo}" onclick="selectAllf(this)"/><label for="selectAll"></label><br/>
 	  </td>
 	  <td>${item.empNo}</td>
 	  <td>${item.eName}</td>
 	  <td>${item.dName}</td>
 	  <td>${item.empRank}</td>
 	  <td>
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
       <a href="/ERP/mypagedelete.do?pageNo=${deletepage.startPage-5}&rowSize=${rsize}">prev</a>
     </c:if>  
     <%-- JSTL forEch조건문: 페이지번호출력 --%>  
     <c:forEach var="pNo"                       
     			begin="${deletepage.startPage}" 
     			end="${deletepage.endPage}">
      <a href="/ERP/mypagedelete.do?pageNo=${pNo}&rowSize=${rsize}">${pNo}</a> 
     </c:forEach>  
                                      
     <%-- JSTL if조건문: 다음출력 --%>  
     <c:if test="${deletepage.endPage<deletepage.totalPages}">
       <a href="/ERP/mypagedelete.do?pageNo=${deletepage.startPage+5}&rowSize=${rsize}">next</a>
     </c:if> 
   </td>
   </tr>
  </tbody>
  
  
 </table>
 
 <br/><br/><br/><br/><br/><br/><br/>
 <br/><br/><br/><br/><br/><br/><br/>
</body>
</html>