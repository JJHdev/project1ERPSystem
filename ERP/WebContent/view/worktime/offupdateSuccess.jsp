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
   <link rel="stylesheet" href="<%=request.getContextPath()%>/css/tableLeft.css?after">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/buttonSubmit.css">
<title>연차 일정 변경 완료</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<style>


body{width: 70%; margin: 0 auto;}
th{
margin-left: 400px;
 border-radius: 10px;
 font-size:35px;
 width:100px;
 text-align: :center;

}
td{
border-radius: 10px;
 text-align: :center;
}
#close1{
	margin-left: 40%;
	border-radius: 10px;
	width:150px;
	font-size:40px;
 	text-align: :center;
}
input#close1 {

	font-size:35px;
	text-align: :center;
}
</style>
</head>
<body>

<script type="text/javascript">


function closeTabClick() {

	window.close();
	 
	
}; 



</script>


<table id="t1">

<tr>

<th>연차 일정 수정이 완료되었습니다.</th>
</tr>

<tr>
<td>
<input type="submit" id="close1" value="닫기" onclick="closeTabClick()">
</td>
</tr>


</table>


</body>
</html>