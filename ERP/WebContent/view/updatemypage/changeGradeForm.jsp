<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/mypageUpdateInfo.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/buttonSubmit.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<title>비번변경폼</title>
<style>

	.error {color:red; font-size:0.8em;}
</style>
<script>
$(document).ready(function(){
	$("form#joinFrm").submit(function(){  
		let frmObj = $(this);
	if($("#curGrade").val()!=1 && $("#curGrade").val()!=999){ 
		alert("계정등급 일반사원 =1 관리자=999로만 입력가능합니다.");
		$("#curGrade").focus();
		return false; 
	}
	if($("#newGrade").val()!=1 && $("#newGrade").val()!=999){ 
		alert("계정등급 일반사원 =1 관리자=999로만 입력가능합니다.");
		$("#newGrade").focus();
		return false; 
	}
	});
});
</script>
</head>
<body>
  <table>
 <tr>
 	<th>
 	
 	</th>
 	<th>
 	
 	</th>
 </tr>
</table> 
 <form name="joinFrm" id="joinFrm" action="mypageupdategrade.aa" method="post">
 <table>
 <tr>
 	<th class="thead">
 	현재 계정등급
 	</th>
 	<th class="tbody">
 	<input type="text" name="curGrade" id="curGrade" placeholder="일반사원 =1 관리자=999"/>
 	 <span class="error"><c:if test="${errors.curGrade}">계정 등급을 입력하세요</c:if></span>
   <span class="error"><c:if test="${errors.badCurGrade}">계정 등급이 일치하지 않습니다</c:if></span>
   <span class="error"><c:if test="${errors.StringCurGrade}">정해진 계정등급번호로 입력해주세요.</c:if></span>
 	</th>
 </tr>
  <tr>
 	<td class="thead">
 	new 계정 등급
 	</td>
 	<td class="tbody">
	 	<input type="text" name="newGrade" id="newGrade" placeholder="일반사원 =1 관리자=999"/>
	 	<span class="error"><c:if test="${errors.newGrade}">새 계정 등급을 입력하세요</c:if></span>
 	</td>
 </tr>
  <tr>
 	<td class="tbody" style="text-align: center;">
 		<input type="submit" class="w-btn w-btn-gray" value="계정등급 변경"/>
	  <input type="hidden" name="empno" value="${empno}">
 	</td>
 	<td class="tbody" style="text-align: center;">
	  <input type="reset" class="w-btn w-btn-gray" value="취소"/>
 	</td>
 </tr>
</table> 
</form>

</body>
</html>








