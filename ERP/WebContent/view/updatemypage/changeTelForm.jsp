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
<script>
const regexPhoneNumber = (target) => {
    target.value = target.value.replace(/[^0-9]/g, '').replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, "$1-$2-$3");
};
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
  <form action="mypageupdatetel.aa" method="post">
 <table>
 <tr>
 	<th class="thead">
 	 현재 전화번호
 	</th>
 	<th class="tbody">
 	 <input type="text" name="curTel" id="curTel" oninput="regexPhoneNumber(this)"/>
 	 <span class="error"><c:if test="${errors.curTel}">전화번호를 입력하세요</c:if></span>
   <span class="error"><c:if test="${errors.badCurTel}">전화번호가 일치하지 않습니다</c:if></span>
 </tr>
  <tr>
 	<td class="thead">
 	new 전화번호
 	</td>
 	<td class="tbody">
	  <input type="text" name="newTel" id="newTel" oninput="regexPhoneNumber(this)"/>
    <span class="error"><c:if test="${errors.newTel}">새 전화번호를 입력하세요</c:if></span>
 	</td>
 </tr>
  <tr>
 	<td class="tbody" style="text-align: center;">
 		<input type="submit"class="w-btn w-btn-gray" value="전화번호 변경"/>
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








