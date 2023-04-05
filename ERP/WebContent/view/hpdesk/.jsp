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
<style></style>
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
   
   
   
   
   
   
       <!--  내용 부분 : 시작 -->
	<h3>게시글목록listhpdesk</h3>	
		<table border="1">
			<thead>
				<tr>
					<th>글번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>글확인상태</th>
				</tr>
			</thead>
			<tbody>
			<%-- 게시글이 없는 경우 --%>
			<%-- JSTL if조건문이용하여 출력 --%>
			<c:if test="${articlePage.hasNoArticles()}">
			<tr>
				<td colspan="5" style="text-align:center;">게시글이 없습니다.</td>
			</tr>
				</c:if>
			<%--  JSTL forEach반복문이용하여 출력시작 
					for(int i=1; i<=10; i++){syso(i)} // 1 2 3 ... 9 10
					for(타입 변수명 :컬렉션명){syso(변수명)}
					<c:forEach  var="변수명"items="컬렉션명" begin="1" end="10" step="증가값(생략시1자동)" > 
					<td><fmt:formatDate type="date" value="${item.regdate}"/></td> date는 타입이 YYYY.M.D로 타입이 정해져있음
			--%>
	<c:if test="${articlePage.hasArticles()}">
			<c:forEach var="item" items="${articlePage.content}">
  		<tr>
	  		<td>${item.hdno}</td> <!-- 글번호 no/현재페이지 /페이지당게시글수 //articlePage.getCurrentPage() -->
	  		<td><a href="/hpdesk/readhpdesk.do?no=${item.hdno}&pageNo=${hpdeskPage.currentPage}">${item.hdtitle}</a></td>
	  		<td>${item.empid}</td>
	  		<%-- <td><fmt:formatDate type="date" value="${item.regdate}"/></td>
	  		<td><fmt:parseDate value="${item.regdate}" var="parsedDate"  pattern="yyyy/MM/dd"/></td>
	  		 --%>
	  		<td>
	  		<fmt:formatDate pattern="yyyy.MM.dd HH:mm:ss" type="date" value="${item.hddate}"/>
	  		</td>
	  		<td>${item.hdcheck}</td>
  		</tr>
			</c:forEach>
		 	</c:if>
			<%--  반복문이용하여 출력끝 --%>
			
			<%-- paging출력부분 --%>
		<%-- 	<tr>
				<td colspan="5" style="text-align:center;">
				<!-- JSTL if조건문: 이전출력 현재보이지않음 /p653 var="변수명"-->
				<c:if test="${hpdeskPage.startPage>5}">
					<a href="/hpdesk/listhpdesk.do?pageNo=${articlePage.startPage-5}&rowSize=${rsize}">prev</a>
				</c:if>
				<!-- JSTL forEach조건문: 페이지번호출력 & 링크태그  -->
				 <c:forEach var="pNo"  begin="${articlePage.startPage}"  end="${articlePage.endPage}">
					자기가 자기 스스로를 불러줌 (위치)  ListArticleHandler
					<a href="/article/listArticle.do?파라미터명=요청페이지&파라미터명=요청">페이지변수(페이지번호)</a>(공백 1칸적용)
					<a href="/article/listArticle.do?pageNo=${pNo}&rowSize=${rsize}">${pNo}</a>  
				</c:forEach> --%>

				<!-- JSTL if조건문: 다음출력 현재보이지않음 /메서드getTotalPages()를 변환  -->
<%-- 					<c:if test="${articlePage.endPage<articlePage.totalPages}">
					<a href="/article/listArticle.do?pageNo=${articlePage.startPage+5}&rowSize=${rsize}">next</a>
				</c:if> --%>
				
				</td>
			</tr>
	
			</tbody>			
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