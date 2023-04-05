<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>

<title>로그인 창</title>
<style>
	*{ 	
		margin: 0;
		padding: 0;
      	box-sizing: border-box;
      	font-family: 'Roboto', sans-serif;
      	}
	
	body{ 
		margin-top:50px;
    	display: flex;
    	height: 70vh;
    	justify-content: center;
    	align-items: center;
    	padding:100px
    }
	.container_body{
		max-width: 800px;
		width: 100%;
		background: #F3F3ED;
		padding :100px 100px;
		border-radius: 10px;
	}
	.container_title{
		font-size: 50px;
		font-weight:800;
		margin-bottom:20px
	}
	
	form .container_form .container_input{
		margin-bottom: 20px;
	}
	.container_form .container_input .detail{
		display:block;
		font-weight : 500;
		margin-bottom: 5px;
	}
	.container_form .container_input input{
		
		height: 45px;
		width: 40%;
		font-size: 20px;
		outline: none;
		border-radius: 6px;
		border: 2px solid #ccc;
		border-color: black;
		border-bottom-width: 2px;
		border-top-width: 2px;
		
	}
	form .container_submit{
		height: 50px;
		margin: 20px 0;
	}
	form .container_submit input{
		
		border: 2px solid #ccc; 
		border-color: #50504F;
		height: 100%;
		width: 30%;
		outline: none;
		font-size: 18px;
		font-weight: 600;
		border-radius: 6px;
		letter-spacing: 5px;
		background: white;
		margin-top:30px
		}
	 .error {color:red; font-size:0.8em;}
	
</style>
</head>
<body>

    <div class="container_body">
        <div class="container_title">로그인</div>
        	<form name="loginFrm"id="loginFrm" method="post" action="<%=request.getContextPath()%>/login.aa">
        <div class="container_form">
        	
		<c:if test="${errors.idOrPwNotMatch}">
		 <span class="error">아이디와 암호가 일치하지 않습니다</span><br/><br/>
		</c:if>
            	
            	<div class="container_input">
            	사원번호<span class="detail"><input type="text" name="empno" placeholder=""></span>
            	<c:if test="${errors.empno}"><span class="error">사번을 입력하세요</span></c:if>
            	</div><br>
            	
            	<div class="container_input">
            	비밀번호<span class="detail"><input type="password" name="emppwd" placeholder=""></span>
            	<c:if test="${errors.emppwd}"><span class="error">비밀번호를 입력하세요</span></c:if>
            	</div>
            </div>
            	<div class="container_submit">
            		<input type="submit" value="로그인">
            	</div>
        </form>
    </div>
</body>
</html>

