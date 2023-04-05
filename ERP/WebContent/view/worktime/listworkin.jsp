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
 <title></title>
 <script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
 <style></style>
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
<body>
<%-- ${articlePage.content} <hr/><hr/><hr/>

   <c:forEach  var="item" items="${articlePage.content}">
     <c:out value="${item}" /> <br/>
     *사원번호:<c:out value="${item.writer}" /><br/>
     *작성자명:<c:out value="${item.writer.writer_name}" /><br/>
     *작성일:<c:out value="${item.regdate}" /><br/>
 
     <br/><br/>
 	 </c:forEach> --%>








</body>
</html>