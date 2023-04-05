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
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/buttonSubmit.css?after">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<style>
 .errors {color:Red;}
</style>
<script>
  $(document).ready(function(){
		$("#btn1").on("click",function(){
		//아이디필수입력
		if($("#receiveEmpno").val()==""){ 
			alert("받을 사번을 입력하세요.");
			$("#receiveEmpno").focus();
			return false; 
		}
		//사원이름필수입력
		if($("#title").val()==""){ 
			alert("제목을 입력하세요.");
			$("#title").focus();
			return false; 
		}
		//사원이름필수입력
		if($("#message").val()==""){ 
			alert("내용을 입력하세요.");
			$("#message").focus();
			return false; 
		}
		});
  });
</script>
</head>
<body>
 
<table width="95%" border="1" cellpadding="0" cellspacing="0">
<tr>
  <td colspan="16">
      <jsp:include page="../module/top.jsp" flush="false"/>
   </td>
</tr>
 
<tr>
  <td width="17%" valign="top">
      <jsp:include page="../module/left.jsp" flush="false"/>
   </td>
   <td width="78%" valign="top">
   
   <table>
		<form action="<%=request.getContextPath()%>/MessageController.aa" method="post">
	    <tr>
				<th class="thead">보내는 사원</th>
				<td class="tbody"><input type="text" id="sendEmpno" name="sendEmpno" value="${EMP_USER.empno}" readonly="readonly" ></td>    
	    </tr>
	    <tr>
				<th class="thead">받는 사원</th>
				<td class="tbody"><input type="text" id="receiveEmpno" name="receiveEmpno" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"  placeholder="보내는 사람 사원번호"></td>
				<td class="tbody">
				<c:if test="${cnt==0}">
					   	<span class="errors">전송할수 없는 사번입니다.</span> 
			  </c:if>
				</td>  
	    </tr>
	    <tr>
				<th class="thead">쪽지 제목</th>
				<td class="tbody"><input type="text" id="title" name="title" placeholder="글제목"></td>    
	    </tr>
	    <tr>
				<th class="thead">쪽지 내용</th>
				<td class="tbody"><textarea id="message" name="message" rows="6"></textarea></td>    
	    </tr>
	    <tr>
				<th class="thead">발송 버튼</th>
				<td class="tbody"><input type="submit" class="w-btn w-btn-gray"  name="btn1" id="btn1"value="send Message" class="special"></td>    
	    </tr>
	    <tr>
				<th class="thead">취소 버튼</th>
				<td class="tbody"><input type="reset" class="w-btn w-btn-gray"  value="clear"></td>    
	    </tr>
		</form>
   </table>
       </td>
</tr>   
<tr>
  <td colspan="16">
    <jsp:include page="../module/bottom.jsp" flush="false"/>
  </td>
</tr>
</table>
 
</body>
</html>