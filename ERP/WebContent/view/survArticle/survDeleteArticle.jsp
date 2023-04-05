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
 <script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
 <style></style>
 <script>
 	$(document).ready(function(){
 		
 	});//
 </script>
</head>
<body>
 <%-- SurvDeleteArticleController컨트롤러를 거쳐왔다면 아래와 같이 Model받았다
*   int cnt : 삭제(update)된 행 수. 1이면 삭제성공,0이면 삭제실패*/
		request.setAttribute("cnt", cnt); --%>

 <c:if test="${cnt==1}">
	 <script>
	  alert("삭제성공");
	 </script>
 </c:if>
 
  <c:if test="${cnt eq 0}">	 
	 <script>  
	  alert("삭제실패");
	 </script>
  </c:if>
  
  <script>      
  location.href="<%=request.getContextPath()%>/surv/list.aa"; //지정url로 이동.
  </script>
</body>
</html>






