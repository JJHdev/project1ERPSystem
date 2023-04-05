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
tr.t1:hover td{font-weight: bold;
        background-color:#f3e9e9 !important;
        color:#303f39 !important;
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
	
  word-break:break-all;
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
  	table-layout: fixed;
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
	$("#rowSize").change(function(){
		$("#rowSizeFrm").submit();
		//let selectedSize = $("select#rowSize option:selected").val();
		$("#rowSize").val(seletedSize)
	});
	$("#btnWrite").on("click",function(){
		location.href="<%=request.getContextPath()%>/notice/write.aa";
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
   	<header>
	<h2>공지사항 목록보기</h2>
	</header>
	<form name="rowSizeFrm" id="rowSizeFrm" action="/notice/list.aa" method="get">
게시물수:
		<select name="rowSize" id="rowSize">
 			<option value="5">선택</option>
 			<option value="5">5</option>
 			<option value="10">10</option>
 			<option value="15">15</option>
 		</select>
 	</form>	
	<table id="customers">
		<thead>
			<tr>
		    	<th class="thead" width="10%">글번호</th>
		    	<th class="thead" width="40%">제목</th>
		    	<th class="thead" width="20%">작성날짜</th>
		  		<th class="thead" width="20%">작성자</th>
		  		<th class="thead" width="10%">조회수</th>
				  	</tr>
		</thead>
					<tbody>
					<c:if test="${noticePage.hasNoNotices()}">
						<tr>
							<td colspan="5" style="text-align:center;">게시글이 없습니다</td>
						</tr>	
					</c:if> 
					
					<c:if test="${!noticePage.hasNoNotices()}">
						<c:forEach var="item" items="${noticePage.contents}">
							<tr class="t1">				
								<td width="10%"><c:out value="${item.notice_no}" /></td>
								
								<td width="40%">
								    <a href="/notice/read.aa?no=${item.notice_no}&pageNo=${noticePage.currentPage}&rowSize=${rowSize}">
								    <c:out value="${item.title}" />
								    </a>
								</td>
								<td width="20%"><fmt:formatDate pattern="yyyy.MM.dd" value="${item.regdate}"/></td>
								<td width="20%"><c:out value="${item.empno}" /></td>	
					
								<td width="10%"><c:out value="${item.readcnt}" /></td>
							</tr>
						</c:forEach>
					</c:if>
						
						<tr>
							<td colspan="24" style="text-align:center;">
							<!-- JSTL 조건 : prev출력 -->
							<c:if test="${noticePage.startPage>5}">
								<a href="/notice/list.aa?pageNo=${noticePage.startPage-5}">prev</a>
							</c:if>	
						
							<!-- JSTL반복문 이용하여 처리 forEach조건문 : 페이지번호 -->
							<c:forEach var="pNo" begin="${noticePage.startPage}" end="${noticePage.endPage}">
							<a href="/notice/list.aa?pageNo=${pNo}&rowSize=${rowSize}">${pNo}</a>  
							</c:forEach>				
					
							<!-- JSTL 조건: next출력 -->
							<c:if test="${noticePage.endPage<noticePage.totalPages}">
							<a href="/notice/list.aa?pageNo=${noticePage.startPage+5}">next</a>
							</c:if>		 
							</td>
						</tr>
							<tr>
							<c:if test="${not empty EMP_USER && EMP_USER.grade == 999}">
							<td colspan="24" style="text-align:right;"><input type="button" class="w-btn w-btn-gray" name="btnWrite" id="btnWrite" value="공지사항 글쓰기"></td>
							</c:if>
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