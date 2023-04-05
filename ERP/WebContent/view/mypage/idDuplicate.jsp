<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원등록 폼</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/buttonSubmit.css?after">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/tableLeft.css?after">
<style>
 #memberFrm td{
  border: 1px solid #ddd;
  padding: 5px;
 }
 #memberFrm td{
  text-align:left;
  background-color:silver;
  color:black;
 }
 .c1 {width:120px;}
 
 .error1 {color:Red;}
</style>

<script>
$(document).ready(function(){
	
	$("#btn1").on("click",function(){
		opener.document.joinFrm.empno.value=idDuplicationFrm.Txtempno.value;
		 self.close();
	 });
	 
 });

</script>
</head>
<body>
	<form name=idDuplicationFrm id="idDuplicationFrm" method="get" action="<%=request.getContextPath()%>/idDuplicate.aa">
    <table>
         <legend><h5>사원번호 중복확인</h5></legend>
     <fieldset>
     	  		사원번호는 숫자로 4~8자 이내로 입력해 주세요.
     	 <tr>
     	  <th class="thead">
     	  사원번호검색
     	  </th>
     	  <td class="tbody">
     	  <input type="text" name="Txtempno" id="Txtempno" value="${empno}"/>
     	  </td>
     	 </tr>
     	 <tr>
     	   <td class="tbody" colspan="2">
     	   	<input type="submit" class="w-btn w-btn-gray"  name="idBtn" id="idBtn" value="id찾기" />
     	   </td>
     	  </tr>
     	  <tr>
     	   <td class="tbody">
				  <c:if test="${idduplicate==true}">
				   사용가능한 사번입니다. <input type="button" class="w-btn w-btn-gray"  id="btn1" value="사용" />
				  </c:if>
				  <c:if test="${idduplicate==false}">
				   사용중인 사번입니다.
     	   </td>
				  </c:if>
     	  </tr>
         </form>
     </fieldset>
    </table>
     
</body>
</html>



