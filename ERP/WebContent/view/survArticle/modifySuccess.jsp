<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html lang="ko">
<head>
 <meta charset="UTF-8">
 <meta name="description" content="member board Web Application">
 <meta name="keywords" content="member, board, article, mvc">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/mypageUpdateInfo.css">
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/tableTop.css">
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/buttonSubmit.css">
 
 <script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
 <style>
 body {
  padding:1.5em;
  background: #f5f5f5
}

table {
  border: 1px #a39485 solid;
  font-size: .9em;
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
 	$(document).ready(function(){
 		
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
<!--  내용 부분 : 시작 -->
<%--
 컨트롤러에 의해 아래와 같이 Model받았다
 ModifyRequest modReq(로그인한userid,글번호,db의작성자명,db의title,db의내용)
		request.setAttribute("modReq", modReq);
 --%>
 <table>
 <thead>  
   <tr><th><h3>수정에 성공하셨습니다.</h3></th></tr>
   </thead>
 	
 	 <%--
 	 <c:set var="변수명" value="변수값"/>--%>
 	  <tbody>
    <tr>
      <td><c:set var="pgNo" 
 	    value="${(empty param.pageNo)?'1':param.pageNo}"/>                      
 	 
 	 <a class="w-btn w-btn-gray" href="<%=request.getContextPath()%>/surv/list.aa?pageNo=${pgNo}&rowSize=3">목록보기</a></td>
   </tr>
   </tbody>
 	 	<%-- 수정과 삭제기능은
 	 	  로그인한 유저의 id와 작성자의 id가 일치하는 경우에만 노출하도록 한다 
 	 	<c:if test="${AUTHUSER.memberid==articleData.article.writer.writer_id}">
	 	 	<a href="/article/modify.do?no=${articleData.article.number}&pageNo=${pgNo}&rowSize=${rowSize}">글수정</a>
	 	 	<a href="/article/deleteArticle.do?no=${articleData.article.number}">글삭제(delete용)</a>
	 	 	<a href="/article/deleteArticle2.do?no=${articleData.article.number}">글삭제(update용)</a>
 	 	</c:if>
 	 	--%>
 	 	</table>
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
</body>
</html>






