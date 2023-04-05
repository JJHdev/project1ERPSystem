<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/buttonSubmit.css">
</head>
<style>

submit{
  margin: 10px;
  }
  
  button {
    margin: 10px;
}
.w-btn {
    position: relative;
    border: none;
 
    padding: 10px 10px;
    border-radius: 7px;
    font-family: "paybooc-Light", sans-serif;
    box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
    text-decoration: none;
    font-weight: 600;
    transition: 0.25s;
}
  .w-btn-gray {
    background-color: #ffd633;
    color: black;
}
  .w-btn:hover {
    letter-spacing: 2px;
    transform: scale(1.1);
    cursor: pointer;
}
  .w-btn:active {
    transform: scale(1.1);
}

.container  {
  position: absolute;
  top: 68%;
  width: 60%;
  text-align: center;
  font-size: 18px;
}

 body {
		background-image: url("/view/images/404.png");
 		background-repeat: no-repeat;
 		background-size: cover;
 
 }

</style>
<body>
	<div class="container">
		<input type="button" class="w-btn w-btn-gray" value="홈으로 이동하기" onClick="location.href='<%=request.getContextPath()%>/notice/list.aa'">
	</div>
</body>
</html>