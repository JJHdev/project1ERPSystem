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
 <title>게시판목록</title>
 <script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
 <style>
 .z1{text-align:right;}
  tr.t1:hover td{
									 			font-weight: bold;
											  background-color:#f3e9e9 !important;
									  		color:#303f39 !important;
											  }

  a { margin: 10px;
}
.w-btn {
    position: relative;
    border: none;
 
    padding: 3px 7px;
    border-radius: 7px;
    font-family: "paybooc-Light", sans-serif;
    box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
    text-decoration: none;
    font-weight: 600;
    transition: 0.25s;
}
  .w-btn-gray {
    background-color: #82766b;
    color: #e3dede;
}
  .w-btn:hover {
    letter-spacing: 2px;
    transform: scale(1.2);
    cursor: pointer;
}
  .w-btn:active {
    transform: scale(1.5);
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
 </script>
</head>
<body>
 <%-- ListArticleHandler컨트롤러에 의해서 아래와 같이 Model을 받았다
	ArticlePage articlePage<=목록+페이징처리 관련 내용
  request.setAttribute("articlePage", articlePage);
  request.setAttribute("rsize",rsize);
--%>
<%-- ${survArticlePage}
   <c:forEach  var="item" items="${survArticlePage.content}">
     <c:out value="${item}" /> <br/>
     *글번호:<c:out value="${item.surv_no}" /><br/>
     *제목:<c:out value="${item.surv_tit}" /><br/>
     *작성자:<c:out value="${item.surv_writer}" /><br/>
     *작성일:<c:out value="${item.surv_regdate}" /><br/>
     *응답자수:<c:out value="${item.survReq_cnt}" /><br/>
     <br/><br/>
 	 </c:forEach> --%>


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
 

 
 <h3>설문 게시글 목록</h3>
 
 <form name="rowSizeFrm" id="rowSizeFrm" 
       action="<%=request.getContextPath()%>/surv/list.aa" method="get"> 
   
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
  	 <th>번호</th>
  	 <th>제목</th>
  	 <th>작성자</th>
  	 <th>설문기간</th>
  	 <th>응답자수</th>
  	 <th>조회수</th>
  	</tr>
  </thead>
  <tbody>
   <%-- 게시글이 없는 경우 --%>
   <%-- JSTL if조건문이용하여 출력 --%>
   <c:if test="${survArticlePage.hasNoArticles()}">
   <tr>
  	 <td colspan="5" style="text-align:center;">게시글이 없습니다.</td>
   </tr>
   </c:if>
   
   <%-- JSTL forEach반복문이용하여 출력시작 
      for(int i=1; i<=10;i++){ syso(i) } //1 2 3.. 9 10
      for(타입 변수명 : 컬렉션명){ syso(변수명) }
   --%> 
   <c:if test="${survArticlePage.hasArticles()}">
   <c:forEach  var="item" items="${survArticlePage.content}">
 	 <tr class="t1">
 	  <td>${item.surv_no}</td>
 	  <td><a href="<%=request.getContextPath()%>/surv/read.aa?no=${item.surv_no}&pageNo=${survArticlePage.currentPage}&rowSize=${rsize}">${item.surv_tit}</a></td>
 	  <td>${item.surv_writer}</td>
 	  <%-- <td><fmt:formatDate type="date" value="${item.regdate}" /></td> --%>
 	  <td>
 	      <fmt:formatDate type="date" pattern="yyyy/MM/dd" value="${item.surv_regdate}" /><br/>~
 	      <fmt:formatDate pattern="yyyy/MM/dd" value="${item.surv_resdate}" /><br/>
 	   </td>
 	  <td>${item.survReq_cnt}</td>
 	  <td>${item.result_count}</td>
 	 </tr>
 	 </c:forEach>
 	 </c:if>
   <%-- 반복문이용하여 출력끝 --%>
    
   <%-- paging출력 부분 --%>
   <tr>
  	<td colspan="5" style="text-align:center;">
     <%-- JSTL if조건문: 이전출력 --%>
     <c:if test="${survArticlePage.startPage>5}">
       <a href="<%=request.getContextPath()%>/surv/list.aa?pageNo=${survArticlePage.startPage-5}&rowSize=${rsize}">prev</a>
     </c:if>  
     <%-- JSTL forEch조건문: 페이지번호출력 --%>  
     <c:forEach var="pNo"                       
     			begin="${survArticlePage.startPage}" 
     			end="${survArticlePage.endPage}">
      <a href="<%=request.getContextPath()%>/surv/list.aa?pageNo=${pNo}&rowSize=${rsize}">${pNo}</a> 
     </c:forEach>  
                                      
     <%-- JSTL if조건문: 다음출력 --%>  
     <c:if test="${survArticlePage.endPage<survArticlePage.totalPages}">
       <a href="<%=request.getContextPath()%>/surv/list.aa?pageNo=${survArticlePage.startPage+5}&rowSize=${rsize}">next</a>
     </c:if> 
   </td>
   </tr>
   </table>
   <br/>
   <c:if test="${not empty EMP_USER && EMP_USER.grade==999}">
   <div class="z1" ><a class="w-btn w-btn-gray" href="<%=request.getContextPath()%>/surv/write.aa?rowSize=${rsize}">글쓰기</a></div>
 </c:if>
    <!-- 내용 부분: 끝 -->
       </td>
    </tr>
<tr>
  <td colspan="16">
    <jsp:include page="../module/bottom.jsp" flush="false"/>
  </td>
</tr>
  </tbody>
</table>
 <br/><br/><br/><br/><br/><br/><br/>
 <br/><br/><br/><br/><br/><br/><br/>
 
</body>
</html>



















