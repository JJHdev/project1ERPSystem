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
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/buttonSubmit.css?after">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<style>
 tr.t1:hover td{
									 			font-weight: bold;
											  background-color:#f3e9e9 !important;
									  		color:#303f39 !important;
											  }
 .z1{
		text-align:right;
		}
 .title1{
					 color: #73685d;  
					 background: #fff;
					 }
 .a1{text-align: right;}
 body {
					  padding:1.5em;
					  background: #f5f5f5
					}
 table {
				  border: 1px #a39485 solid;
				  font-size:1em;
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
				    table-layout: fixed;
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
	  $("#btnInser").on("click",function(){
			location.href="<%=request.getContextPath() %>/hpdesk/inserthpdesk.aa";
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
   
   
   
   
   
   
       <!--  내용 부분 : 시작 -->
<%-- sessionScope:${sessionScope.EMP_USER} <br/><br/>
hpdeskPage:${hpdeskPage}<br/><br/> --%>
			<h2 class="title1">문의사항 목록</h2>	
			<form name="listFrm" id="listFrm" action="<%=request.getContextPath() %>/hpdesk/listhpdesk.aa"method="get" >
			<table border="1">
			<thead>
				<tr >
					<th width="10%">게시글번호</th>
					<th width="40%">게시글제목</th>
					<th width="15%">작성자</th>
					<th width="20%">작성일</th>
					<th width="15%">글확인상태</th>
				</tr>
			</thead>
			<tbody>
			<%-- 게시글이 없는 경우 --%>
			<%-- JSTL if조건문이용하여 출력 --%>
			<c:if test="${hpdeskPage.haNoHPDeskes()}">
			<tr>
				<td colspan="5" style="text-align:center;">게시글이 없습니다.</td>
			</tr>
			</c:if>
				<%--  JSTL forEach반복문이용하여 출력시작 	--%>
				<c:if test="${hpdeskPage.hasHPDeskes()}">
				<c:forEach var="item" items="${hpdeskPage.hdcontent}">
	  		<tr class="t1">
		  		<td >${item.hdno }	</td> <!-- 글번호 hdno/현재페이지 /페이지당게시글수 //articlePage.getCurrentPage() -->
		  		<td ><a href="<%=request.getContextPath() %>/hpdesk/readhpdesk.aa?hdno=${item.hdno}&empno=${item.hpdeskWriter.empno}">${item.hdtitle}</a></td>
		  		<td>${item.hpdeskWriter.ename }</td>
		  		<td><fmt:formatDate pattern="yyyy.MM.dd" type="date" value="${item.hddate}"/></td>
		  		<td>
		 						<c:choose>
													<c:when test="${item.hdcheck==1}">
														미답변
													</c:when> 
													<c:when test="${item.hdcheck==2}">
														처리중
													</c:when> 
														<c:when test="${item.hdcheck==3}">
														답변완료
													</c:when> 
								</c:choose> 	
					 	</td>
	  		</tr>
	  		</c:forEach>
			 	</c:if>
				<%--  반복문이용하여 출력끝 --%>
				<%-- paging출력부분 --%>
				<tr>
					<td colspan="5" style="text-align:center;">
					<!-- JSTL if조건문: 이전출력 현재보이지않음 /p653 var="변수명"-->
					<c:if test="${hpdeskPage.startPage>5}">
						<a href="<%=request.getContextPath() %>/hpdesk/listhpdesk.aa?pageNo=${hpdeskPage.startPage-5}&rowSize=${rsize}">prev</a>
					</c:if>
					<!-- JSTL forEach조건문: 페이지번호출력 & 링크태그  -->
					 <c:forEach var="pNo"  begin="${hpdeskPage.startPage}"  end="${hpdeskPage.endPage}">
						<a href="<%=request.getContextPath() %>/hpdesk/listhpdesk.aa?pageNo=${pNo}&rowSize=${rsize}">${pNo}</a>  
					</c:forEach>
					<!-- JSTL if조건문: 다음출력 현재보이지않음 /메서드getTotalPages()를 변환  -->
					<c:if test="${hpdeskPage.endPage<hpdeskPage.totalPages}">
						<a href="<%=request.getContextPath() %>/hpdesk/listhpdesk.aa?pageNo=${hpdeskPage.startPage+5}&rowSize=${rsize}">next</a>
					</c:if> 					
					</td>
				</tr>
				</tbody>	
				</table>
				<br/>
				<div  class="z1"><input class="w-btn w-btn-gray"type="button"name="btnInser" id="btnInser" value="문의글쓰기"/></div>
 				</form>		
       <br><br><br>    
       <!-- 내용 부분: 끝 -->
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