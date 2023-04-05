<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html lang="ko">
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="member board web Application">
<meta name="keywords" content="member, board, article, mvc">
<meta name="author" content="DoYoon KEUM">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<style></style>
<script>
$(document).ready(function(){
});
</script>
</head>
<body>
<%-- delete핸들러 결과물 int cnt를 setAttribute해서 여기서 볼거임 --%>
<h2>작성후~ 후~~~~~~</h2>
모델 잘 넘어와>>
${cnt}
<c:if test="${comReq!=null}">
<script>
alert("수정완료")
</script>
</c:if>

<c:if test="${comReq==null}">
<script>
alert("수정실패")
</script>
</c:if>

<script>
location.href="<%=request.getContextPath()%>/notice/read.aa?no=${no}&pageNo=${pageNo}&rowSize=${rowSize}"
</script>
</body>
</html>