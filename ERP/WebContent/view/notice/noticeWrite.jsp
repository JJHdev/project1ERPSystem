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
      	$("#reset").on("click",function(){
      		location.href="<%=request.getContextPath()%>/notice/list.aa"
      		
      	});
    });//
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
   
 <h3>공지사항 작성페이지</h3>
 
 <form name="writeFrm" id="writeFrm" 
       method="post" action="<%=request.getContextPath()%>/notice/write.aa">
 <table border="1">
	<tr>
     <th class="thead">작성 사원번호</th>
     <td>${EMP_USER.empno}</td>
    </tr>
    <tr>
     <th class="thead">제목</th>
     <td>
      <input type="text" name="title" id="title"  placeholder="제목을 입력해주세요"/>
      <span class="error"><c:if test="${errors.title}">제목을 입력하세요</c:if></span>
     </td>
    </tr>
    <tr>
     <th class="thead">내용</th>
     <td>
       <textarea name="content" id="content" rows="5" cols="50" placeholder="내용을 입력해주세요"></textarea>
         <span class="error"><c:if test="${errors.content}">내용을 입력하세요</c:if></span>
     </td>
    </tr>
    <tr>          
     <td colspan="4" style="text-align:center;">
      <input type="submit" class="w-btn w-btn-gray" value="작성완료"/>
      <input type="reset" class="w-btn w-btn-gray" value="취소하기" id="reset">
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