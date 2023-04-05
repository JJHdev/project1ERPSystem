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
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/tableLeft.css">
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/buttonSubmit.css">
 <script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
 <style>
 
 </style>
 <script>
 	$(document).ready(function(){
 		
 	});//
 </script>
</head>
<body>
<% request.setCharacterEncoding("UTF-8"); %>
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
<%-- ModifyArticleHandler 컨트롤러에 의해 아래와 같이 Model받았다
      new ModifyRequest(로그인한userid,글번호,db의작성자명,db의title,db의내용)
			request.setAttribute("modReq", modReq)

*DB내용:${articleData}<br/><br/>
*요청페이지:${pageNo}<br/>
*1페이지당게시글수:${rowSize}<br/><br/><br/>--%>

 
<form name="modifyFrm" id="modifyFrm" 
       method="post" action="<%=request.getContextPath()%>/surv/modify.aa">
 <table class="t1" border="1">
 	<tr>
 	 <th class="thead">번호</th>
 	 <td class="tbody">${modReq.articleNumber}</td>
 	</tr>
 	<tr>
 	 <th class="thead">작성자명</th>
 	 <td class="tbody">${modReq.writer_name}</td>
 	</tr>
 	<tr>
 	 <th class="thead">제목</th>
 	 <td class="tbody">
 	  <input type="text" name="title" id="title" value="${modReq.title}"/>
 	 </td>
 	</tr>
 	<tr>
 	 <th class="thead">내용</th>
 	 <td class="tbody">
 	 	<textarea name="content" id="content" rows="5" cols="30">${modReq.content}</textarea>
 	 </td>
 	</tr>
 	<tr>
 	 <th class="thead">날짜설정</th>
 	 <td class="tbody">
 	 기존 마감날짜 :<fmt:formatDate type="both" pattern="yyyy/MM/dd"  value="${modReq.res_date}" />
 	 <br/>
 	수정할 마감날짜:<input type="date" id="surv_resdate" name = "surv_resdate" value="${modReq.res_date}"/>
 	 </td>
 	</tr>
 	<tr>          
 	 <td colspan="2" style="text-align:center;">
		<input class="w-btn w-btn-gray" type="submit" value="수정하기"/>
 	 </td>
 	</tr>
 	<tr>          
 	 <td colspan="2" style="text-align:center;">
 	 	<a class="w-btn w-btn-gray" href="/surv/list.aa?pageNo=1&rowSize=3">목록보기</a>
 	 </td>
 	</tr>
 </table>
 	  <input type="hidden" name="no" id="no" value="${modReq.articleNumber}"/>
 	  <input type="hidden" name="writer_name" id="writer_name" value="${modReq.writer_name}"/>
 	  <%-- 여기에서는 작성자명을 수정처리컨트롤러에 넘기는 방식으로 추가하였다.
 	       현재방식이라면 DB의 작성자명과 세션에 담긴 이름이 동일하므로  세션으로 대체해도 된다. --%>
 </form>
 
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













