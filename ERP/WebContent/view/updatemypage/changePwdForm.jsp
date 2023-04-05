<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/mypageUpdateInfo.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/buttonSubmit.css">
<title>비번변경폼</title>
<style>
	.error {color:red; font-size:0.8em;}
</style>
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
  <form action="mypageupdatepwd.aa" method="post">
 <table>
 <tr>
 	<th class="thead">
 	 현재 비밀번호
 	</th>
 	<th class="tbody">
 	 <input type="text" name="curPwd" id="curPwd" />
 	 <span class="error"><c:if test="${errors.curPwd}">비밀번호를 입력하세요</c:if></span>
   <span class="error"><c:if test="${errors.badCurPwd}">비밀번호가 일치하지 않습니다</c:if></span>
 	</th>
 </tr>
  <tr>
 	<td class="thead">
 	new 비밀번호
 	</td>
 	<td class="tbody">
	 <input type="text" name="newPwd" id="newPwd" />
  <span class="error"><c:if test="${errors.newPwd}">새 비밀번호를 입력하세요</c:if></span>
 	</td>
 </tr>
  <tr>
 	<td class="tbody" style="text-align: center;">
 		<input type="submit" class="w-btn w-btn-gray"  value="암호변경"/>
	  <input type="hidden" name="empno" value="${empno}">
 	</td>
 	<td class="tbody" style="text-align: center;">
	  <input type="reset" class="w-btn w-btn-gray"  value="취소"/>
 	</td>
 </tr>
</table> 
</form>

</body>
</html>








