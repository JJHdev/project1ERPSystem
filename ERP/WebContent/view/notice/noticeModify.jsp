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
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/tableLeft.css?after">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/buttonSubmit.css?after">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<style>
.btnu{
  color: #fff;
  background: #73685d;
  font-size:1.1em;
  border: 0;
  background-color: transparent;
  cursor:pointer;
}
.btnu:hover{font-weight: bold;}
.btnd{
  color: #73685d;
  background: #fff;
  font-size:1.1em;
  border: 0;
  background-color: transparent;
  cursor:pointer;
}
.btnd:hover{font-weight: bold;}
.thead2 {
  color:#fff;
  background:#73685d;
  text-align:right;
  white-space: nowrap;
  }
.tbody2 {
  color:#73685d;
  background:#fff;
	text-align:right;
	white-space: nowrap;
  }
.title1{color: #73685d;  background: #fff;}
.a1{text-align: right;}
.wrt{font-size:17px;}

tr.td{style=text-align:right;}
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
  	textarea{
  		resize: none;
  		border:none;
  	}

</style>
<script>
$(document).ready(function(){
	$("#submit").on("click",function(){
		  //필수입력체크
		  let titleVal = $("#title").val();
		  let contentVal = $("#content").val();
		  if(titleVal==""){
			  alert("제목을 입력하세요");
			  $("#title").focus();
			  return false;
		  }
		  if(contentVal==""){
			  alert("내용을 입력하세요");
			  $("#content").focus();
			  return false;
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

 <h2>공지사항 수정하기</h2>
 <form name="modifyFrm" id="modifyFrm" method="post" action="/notice/modify.aa">

 <table class="table1" border="1">
 	<tr>
 	 <th class="thead">글번호</th>
 	 <td>${modReq.notice_no} 번째 글</td>
 	</tr>
 	<tr>
 	 <th class="thead">작성자명</th>
 	 <td> ${modReq.empno}</td>
 	</tr>
 	 <tr>
 	 <th class="thead">제목</th>
 	 <td>
 	 	<input type="text" name="title" id="title" value="${modReq.title}">
 	 </td>
 	</tr>
 	<tr>
 	 <th class="thead">내용</th>
 	 <td>
 	 <!-- ${articleData.content.content} -->
 	<pre><textarea name="content" id="content" rows="10" cols="50"><u:pre value="${modReq.content}"/></textarea></pre>
 	 </td>
 	</tr>
 	<tr>
 	 <td colspan ="5" style="text-align:center;">
 	 	<input type="submit"  class="w-btn w-btn-gray" id="submit" value="수정완료">
 	 </td>
 	</tr>
 	<tr>
 	 <td colspan="2" style="text-align:center;">
 	 	<input type="hidden" name="no" id="no" value="${modReq.notice_no}">
 	 	<input type="hidden" name="rowSize" id="rowSize" value="${rowSize}">
 	 	<input type="hidden" name="pageNo" id="pageNo" value="${pageNo}">
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