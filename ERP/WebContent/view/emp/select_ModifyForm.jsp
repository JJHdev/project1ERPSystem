<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/tableTop.css"><!-- 테이블 -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/buttonSubmit.css"><!--버튼  -->
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="description" content="member board Web Application with MVC">
<meta name="keywords" content="member, board, aeticle, mvc">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ERP인사관리</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	<script src="https://code.jquery.com/jquery-2.2.4.js" ></script>
  <style>
  </style>
  <script>
  
  const autoHyphen2 = (target) => {
	  target.value = target.value
	    .replace(/[^0-9]/g, '')
	   .replace(/^(\d{0,3})(\d{0,4})(\d{0,4})$/g, "$1-$2-$3").replace(/(\-{1,2})$/g, "");
	 }
  
  $(document).ready( function(){
	  
	 
		 $("#btn").click( function() {
				if($("#emppwd").val()==""){ 
					alert("비밀번호를 입력하세요.");
					$("#emppwd").focus();
					return false; 
				}
				if($("#deptno").val()==""){ 
					alert("부서번호를 입력하세요.");
					$("#deptno").focus();
					return false; 
				}
				if($("#ename").val()==""){ 
					alert("사원명을 입력하세요.");
					$("#ename").focus();
					return false; 
				}
				if($("#erprank").val()==""){ 
					alert("직급을 입력하세요.");
					$("#erprank").focus();
					return false; 
				}
				if($("#email").val()==""){ 
					alert("이메일를 입력하세요.");
					$("#email").focus();
					return false; 
				}
				if($("#email")){ 
			    var regEmail = /\w+([-+.]\w+)*@\w+([-.]\w+)*\.[a-zA-Z]{2,4}$/;
			    	/* /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/; */
			    if (!regEmail.test($("#email").val())) {
			        alert('이메일 형식에 맞춰주세요.');
			        return false;
			    }
				}
					
				if($("#tel").val()==""){ 
					alert("전화번호를 입력하세요.");
					$("#tel").focus();
					return false; 
				}
				if($("#tel").val().length<13){ 
					alert("전화번호는 예)010-1234-1234로 입력하세요.");
					$("#tel").focus();
					return false; 
				}
				if($("#sal").val()==""){ 
					alert("연봉을 입력하세요.");
					$("#sal").focus();
					return false; 
				}
				if($("#grade").val()==""){ 
					alert("등급을 입력하세요.");
					$("#grade").focus();
					return false; 
				}
				if($("#grade").val()!=1 && $("#grade").val()!=999){ 
					alert("등급은 1 또는 999로 입력하세요.");
					$("#grade").focus();
					return false; 
				}
				if(confirm("수정하시겠습니까?") == false) {
					$("#grade")
					return false;
				}else{
					alert("수정되었습니다.")
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

	<h2>수정</h2>
	<form action="/selectEmp_Modify.aa" method="post">
	 <table class="t1">
	 <thead> 
		<tr class="t1" style="text-align: center">
			<th>사원번호</th><th>비밀번호</th><th>부서번호</th>
			<th>사원명</th><th>직급</th>
			<th>이메일</th><th>전화번호</th><th>입사일</th>
			<th>연봉</th><th>권한등급</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<input type="hidden" name="empno" id="empno" value="${SelectEmp.empno}" />
			<td><c:out value="${SelectEmp.empno}"/></td>
			<td><input type="text" name="emppwd" id="emppwd" value="${SelectEmp.emppwd}" size="10"/></td>
			<td><input type="number" name="deptno" id="deptno" value="${SelectEmp.deptno}" size="10"/></td>
			<td><input type="text" name="ename" id="ename" value="${SelectEmp.ename}" size="10"/></td>
			<td><input type="text" name="erprank" id="erprank" value="${SelectEmp.erprank}" size="10"/></td>
			<td><input type="text" name="email" id="email" value="${SelectEmp.email}" size="20"/></td>
			<td><input type="text" name="tel" id="tel" value="${SelectEmp.tel}" size="11"
				oninput="autoHyphen2(this)"  maxlength="13" /></td>
			<td><input type="date" name="hiredate" id="hiredate"
				value="<fmt:formatDate pattern="yyyy-MM-dd" value="${SelectEmp.hiredate}" />" size="10"/></td>
			<td><input type="number" name="sal" id="sal" value="${SelectEmp.sal}" size="8"/></td>
			<td><input type="number" name="grade" id="grade" value="${SelectEmp.grade}" size="10"/></td>
		</tr>
		<tr>
			<td colspan="10"><input type="submit" value="수정완료" id="btn" class="w-btn w-btn-gray"/></td>
		</tr>
		</tbody>
	 </table>
	</form> 

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