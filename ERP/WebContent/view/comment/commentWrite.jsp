<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="description" content="member board Web Application">
  <meta name="keywords" content="member, board, article, mvc">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/buttonSubmit.css?after">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<style>
	body {
 		 background: #f5f5f5
	}

	table {
  		 border: 1px #a39485 solid;
  		 font-size: 1em;
 		 box-shadow: 0 2px 5px rgba(0,0,0,.25);
 		 width: 100%;
  		 border-collapse: collapse;
  	  	 border-radius: 7px;
  		 overflow: hidden;	
  	  	 text-align:center;
	}
	th {
  		
  		 vertical-align: middle;
		 background: #736658;
	 	 color: #fff;
	 	 font-weight: bold;
	}
  
 	td {
 		 border-bottom: 1px solid rgba(0,0,0,.1);
 	 	 background: #fff;
	}

	a {
 	 	 color: #73685d;
	}
  	
  	  	textarea{
  		resize: none;
  		border:none;
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
   

 <h3>댓글작성</h3>
<form name="cwriteFrm" id="cwriteFrm" method="post" action="/comment/write.aa">
	<table>
		<tr>
		<td>
		<textarea name="c_content" id="c_content" rows="5" cols="100" placeholder="내용을 입력해주세요"></textarea>
		<input type="hidden" id="cno" name="cno" value="${comReq.comment_no}">
		<input type="hidden" id="no" name="no" value="${no}">
		<input type="hidden" id="rowSize" name="rowSize" value="${rowSize}">
		<input type="hidden" id="pageNo" name="pageNo" value="${pageNo}">
		</td>
		<td>		 
		 <input type="submit" class="w-btn w-btn-gray" value="작성하기">
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