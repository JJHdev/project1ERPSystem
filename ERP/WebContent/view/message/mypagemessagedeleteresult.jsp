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
 <h2>deleteArticle2.jsp</h2>
 ${cnt}
 
 <c:if test="${cnt==1}">
 <script>
 	alert("메세지 삭제성공")
  </script>
  </c:if>
  
   <c:if test="${cnt==0}">
  <script>
 	alert("메세지 삭제실패")
 	</script>
 	  </c:if>
 	  
 	  <script>
 	  	location.href="<%=request.getContextPath()%>/MessageShowController.aa"; //지정url로 이동
 	  </script>
 	  
 <hr/>
</body>
</html>
