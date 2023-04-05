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
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/tableTop.css?after">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/buttonSubmit.css?after">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<style>
tr.t1:hover td{font-weight: bold;
        background-color:#f3e9e9 !important;
        color:#303f39 !important;
        }
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
 	function popup(a,b,c){
 		  let options = "toolbar=no,scrollbars=no,resizable=yes,status=no,menubar=no,width=500, height=500, top=0,left=0";
 		  window.open("<%=request.getContextPath()%>/messageread.aa?no="+a+"&pageNo=${messgaePage.currentPage}&rowSize=${rsize}","_blank", options);
 		}
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
   
 <form name="rowSizeFrm" id="rowSizeFrm" 
       action="<%=request.getContextPath()%>/MessageShowController.aa" method="get"> 
   <input type="hidden" name="mypagesearch" id="mypagesearch" value="${messgaePage.keyword}">
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
  	 <th width="12%">삭제하기</th>
  	 <th width="12%">메세지 번호</th>
  	 <th width="19%">보낸사원번호</th>
  	 <th width="19%">보낸 사원이름</th>
  	 <th width="19%">제목</th>
  	 <th width="19%">보낸 일자</th>
  	</tr>
  </thead>
  
  <tbody>
   <c:if test="${messgaePage.hasNoMessage()}">
   <form name="frm" id="frm" action="<%=request.getContextPath()%>/MessageShowController.aa" method="post">
   <input type="submit" class="w-btn w-btn-gray"  name="operate" value="삭제하기">
   <input type="search" name="mypagesearch" id="mypagesearch">
   <input type="submit" class="w-btn w-btn-gray"  name="operate" value="사원번호로 찾기">
   <tr>
  	 <td colspan="5" style="text-align:center;">메세지가 없습니다.</td>
   </tr>
   </form>
   </c:if>
   
   <c:if test="${messgaePage.hasMessage()}">
   
   <form name="frm" id="frm" action="<%=request.getContextPath()%>/MessageShowController.aa" method="post">
   <input type="submit" class="w-btn w-btn-gray"  name="operate" value="삭제하기">
   <input type="search" name="mypagesearch" id="mypagesearch">
   <input type="submit" class="w-btn w-btn-gray"  name="operate" value="사원번호로 찾기">
   
   <c:forEach  var="item" items="${messgaePage.content}">
 	 <tr class="t1">
 	  <td width="12%">
			<input type="checkbox" name="delemessageno" id="delemessageno" value="${item.messageno}" onclick="selectAllf(this)"/><label for="selectAll"></label><br/>
 	  </td>
 	  <td width="12%">${item.messageno}</td>
 	  <td width="19%">${item.sendempno}</td>
 	  <td width="19%">${item.sendempname}</td>
 	  <td width="19%"><a href="#" onclick="popup(${item.messageno},${messgaePage.currentPage},${rsize})">${item.title}</a></td>                 
 	  <td width="19%">
 	     <fmt:formatDate pattern="yyyy.MM.dd" value="${item.send_time}"></fmt:formatDate><br/>
 	  </td>
 	 </tr>
 	 </c:forEach>
 	 
 	 </form> 
 	 </c:if>
   <tr>
  	<td colspan="6" style="text-align:center;">
     <%-- JSTL if조건문: 이전출력 --%>
     <c:if test="${messgaePage.startPage>5}">
       <a href="<%=request.getContextPath()%>/MessageShowController.aa?pageNo=${messgaePage.startPage-5}&rowSize=${rsize}">prev</a>
     </c:if>  
     <%-- JSTL forEch조건문: 페이지번호출력 --%>  
     <c:forEach var="pNo"           
     			begin="${messgaePage.startPage}" 
     			end="${messgaePage.endPage}">
      <a href="<%=request.getContextPath()%>/MessageShowController.aa?pageNo=${pNo}&rowSize=${rsize}&mypagesearch=${messgaePage.keyword}">${pNo}</a> 
     </c:forEach> 
     <%-- JSTL if조건문: 다음출력 --%>  
     <c:if test="${messgaePage.endPage<messgaePage.totalPages}">
       <a href="<%=request.getContextPath()%>/MessageShowController.aa?pageNo=${messgaePage.startPage+5}&rowSize=${rsize}">next</a>
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