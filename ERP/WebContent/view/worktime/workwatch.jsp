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
<!--  <link href="../../css/workwatch.css" rel="stylesheet"> -->
 <title>시계</title>
 <script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
 <style>
 </style>

</head>
<body>
<form action="" method="post">
  	<div id="date" class="date"></div>
 	<div id="time" class="time"></div>
 	<hr>
 <script>
 function setClock(){
	    var dateInfo = new Date(); 
	    var hour = modifyNumber(dateInfo.getHours());
	    var min = modifyNumber(dateInfo.getMinutes());
	    var sec = modifyNumber(dateInfo.getSeconds());
	    var year = dateInfo.getFullYear();
	    var month = dateInfo.getMonth()+1; //monthIndex를 반환해주기 때문에 1을 더해준다.
	    var date = dateInfo.getDate();
	    document.getElementById("time").innerHTML = hour + ":" + min  + ":" + sec;
	    document.getElementById("date").innerHTML = year + "년 " + month + "월 " + date + "일";
	}
	function modifyNumber(time){
	    if(parseInt(time)<10){
	        return "0"+ time;
	    }
	    else
	        return time;
	}
	window.onload = function(){
	    setClock();
	    setInterval(setClock,1000); //1초마다 setClock 함수 실행
	}
 </script>
</form>
 <br/>
 <hr>
</body>
</html>