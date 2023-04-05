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

</style>
<script>
function deleteChk(){
	let del=confirm("삭제하시겠습니까?");
	 if(del){
			location.href="<%=request.getContextPath()%>/notice/delete.aa?no=${noticeRead.notice_no}&pageNo=${pageNo}&rowSize=${rowSize}";
	 }
}

function modifyChk(){
	location.href="<%=request.getContextPath()%>/notice/modify.aa?no=${noticeRead.notice_no}&pageNo=${pageNo}&rowSize=${rowSize}";
}

function cDeleteChk(commentNo){
	let del=confirm("삭제하시겠습니까?");
	if(del){
	location.href="<%=request.getContextPath()%>/comment/delete.aa?no=${noticeRead.notice_no}&pageNo=${pageNo}&rowSize=${rowSize}&cno="+commentNo;
	}
}

function cModifyChk(commentNo){
	location.href="<%=request.getContextPath()%>/comment/modify.aa?no=${noticeRead.notice_no}&pageNo=${pageNo}&rowSize=${rowSize}&cno="+commentNo;
}

$(document).ready(function(){
    $("#btnList").on("click",function(){
    	location.href="<%=request.getContextPath()%>/notice/list.aa?pageNo=${pageNo}&rowSize=${rowSize}";
    });
    
    $("#btnCWrite").on("click",function(){
    	location.href="<%=request.getContextPath()%>/comment/write.aa?no=${noticeRead.notice_no}&pageNo=${pageNo}&rowSize=${rowSize}";
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

 <h2>공지사항 상세보기</h2>
 <table class="noticeTable" border="1">
 	
 	<tr>
 	 	<th class="thead">글번호</th>
 	 	<td class="td"> ${noticeRead.notice_no}</td>
 	</tr>
 	<tr>
 	 	<th class="thead">작성자</th>	
 	 	<td class="td"> ${noticeRead.empno}</td>
 	</tr>
 	<tr>
 	 	<th class="thead">작성일</th>
 	 	<td class="td"><fmt:formatDate pattern="yyyy.MM.dd" value="${noticeRead.regdate}"/></td>
 	</tr>
 	<tr>
 	 	<th class="thead">글제목</th>
		<td class="td"> ${noticeRead.title}</td>
 	</tr>
 	<tr>
 	 	<th class="thead">글내용</th>
 	 	<td> <u:pre value="${noticeRead.content}"/></td>
 	</tr>
 	</table>
 	<div style="text-align:right;"><br>
 	 	<input type="button" class="w-btn w-btn-gray" name="btnList" id="btnList" value="목록보기">
 		<c:if test="${EMP_USER.empno==noticeRead.empno || EMP_USER.grade ==999}">
 	 	<input type="button" class="w-btn w-btn-gray" name="nModify" class="ab"id="nModify" value="수정하기" onclick="modifyChk()">
 	 	<input type="button" class="w-btn w-btn-gray" name="nDelete" class="ab"id="nDelete" value="삭제하기" onclick="deleteChk()">
 	 	</c:if>
 	</div>
 	<br><br>
 	<table>
 	<tr>
 	<th class="thead" height="70">댓글목록</th>
 	</tr>
 	<c:if test="${empty commentRead.comments}">
 	<tr>
 		<td class="center">댓글이 없습니다</td>
 	</tr>
 	</c:if>
 	 <c:forEach var="item" items="${commentRead.comments}">
	<tr>				
		<td height="30"><u:pre value="${item.c_content}"/><br/><br/>
		<div class="comment">사원번호:${item.c_writer} 작성일:<fmt:formatDate pattern="yyyy.MM.dd" value="${item.c_regdate}"/>
		수정일:<fmt:formatDate pattern="yyyy.MM.dd" value="${item.c_moddate}"/>
		<c:if test="${not empty EMP_USER && EMP_USER.empno==item.c_writer||EMP_USER.grade == 999}">
 	 	<input type="button" class="w-btn w-btn-gray" name="btnCModify" id="btnCModify" value="댓글수정하기" onclick="cModifyChk(${item.comment_no})"/>
		<input type="button" class="w-btn w-btn-gray" name="btnCDelete" id="btnCDelete" value="댓글삭제하기" onclick="cDeleteChk(${item.comment_no})"/>
		</c:if></div>
		</td>
	</c:forEach>
	<tr>
 	<td height="80" style="text-align:center;">
 	 	<c:set var="pageNo" value="${empty param.pageNo?'1':param.pageNo}"/>
 	 	<!-- 삼항연산자 value= "조건? 조건참일경우: 조건거짓일경우" -->
 	 	
 	 	<input type="button" class="w-btn w-btn-gray" name="btnCWrite" id="btnCWrite" value="댓글쓰기">
 
	</td>
 	</tr>
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