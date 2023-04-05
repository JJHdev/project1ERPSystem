<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비번변경폼</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/buttonSubmit.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/mypageUpdateInfo.css">
<style>
	.error {color:red; font-size:0.8em;}
</style>
<script>
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
 <form action="mypageupdatedname.aa" name="form1" id="form1" method="post">
 <table>
 <tr>
 	<th class="thead">
 	현재 부서번호
 	</th>
 	<th class="tbody">
 	<input type="text" name="curDname" id="curDname" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" />
 	 <span class="error"><c:if test="${errors.curDname}">부서번호를 입력하세요</c:if></span>
 	 <span class="error"><c:if test="${errors.badCurDname}">부서번호가 일치하지 않습니다</c:if></span>
 	</th>
 </tr>
  <tr>
 	<td class="thead">
 	new 부서번호
 	</td>
 	<td class="tbody">
	 	<input type="text" name="newDname" id="newDname" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" />
	 	<span class="error"><c:if test="${errors.newDname}">새 부서번호를 입력하세요</c:if></span>
 	</td>
 </tr>
  <tr>
 	<td class="tbody" style="text-align: center;">
 		<input type="submit" class="w-btn w-btn-gray" id="btn1"  value="부서번호변경"/>
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








