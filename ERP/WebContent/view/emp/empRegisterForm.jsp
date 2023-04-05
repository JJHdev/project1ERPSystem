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
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/buttonSubmit.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/tableLeft.css">
  
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<style>
  	input[type=text]{
  	border: none; 
  	background: transparent;
  }
    input[type=number]{
  	border: none; 
  	background: transparent;
  }
    input[type=password]{
  	border: none; 
  	background: transparent;
  }
    input[type=date]{
  	border: none; 
  	background: transparent;
  }
  }
</style>
<script>
$(document).ready(function(){
	

$("form#joinFrm").submit(function(){  
	let frmObj = $(this);
	
	//사원번호 글자수제한 편의상 4~6 숫자로만
	if($("#empno").val().length<4 || $("#empno").val().length>6){ //비밀번호의 글자수가 4~12이 아니면
		alert("사원번호는 4~6숫자로만 입력하세요.");
		$("#empno").focus();
		return false; 
	}
	
	//사원이름필수입력
	if($("#ename").val()==""){ 
		alert("사원이름을 입력하세요.");
		$("#ename").focus();
		return false; 
	}
	
	//부서번호필수입력
	if($("#deptno").val()==""){ 
		alert("부서번호를 입력하세요.");
		$("#deptno").focus(); 
		return false; 
	}
	
	//비밀번호필수입력
	if($("#emppwd").val()==""){ 
		alert("비밀번호 입력하세요.");
		$("#emppwd").focus(); 
		return false; 
	}
	
	//재확인비밀번호 필수입력
	if($("#re_emppwd").val()==""){
		alert("재확인 비밀번호 입력하세요.");
		$("#re_emppwd").focus();
		return false; 
	}
	
	//직급필수입력
	if($("#level").val()==""){
		alert("직급을 입력하세요.");
		$("#level").focus();
		return false; 
	}
	
	if($("#hiredate").val()==""){ 
		alert("입사일을 입력하세요.");
		$("#hiredate").focus(); 
		return false; 
	}
	
	if($("#tel").val()==""){ 
		alert("전화번호를 입력하세요.");
		$("#tel").focus(); 
		return false; 
	}
	
	if($("#f_email").val()==""){ 
		alert("이메일를 입력하세요.");
		$("#f_email").focus(); 
		return false; 
	}
	
	if($("#b_email").val()==""){ 
		alert("이메일를 입력하세요.");
		$("#b_email").focus(); 
		return false; 
	}
	
	if($("#sal").val()==""){ 
		alert("연봉을 입력하세요.");
		$("#sal").focus(); 
		return false; 
	}
	
	if($("#grade").val()!=1 && $("#grade").val()!=999){ 
		alert("계정등급 일반사원 =1 관리자=999로만 입력가능합니다.");
		$("#grade").focus();
		return false; 
	}
	
	//비밀번호와 비밀번호재확인 일치여부
	if($("#emppwd").val()!=$("#re_emppwd").val()){ 
		alert("비밀번호와 비밀번호재확인 일치해야합니다.");
		$("#emppwd").val("");   //초기화
		$("#re_emppwd").val("");
		$("#emppwd").focus(); //포커스위치
		return false; 
	}	

});

$("#bb_email").change(function(){
	let val = $("select#bb_email option:selected").val();
	$("#b_email").val(val);
});

$("#b_deptno").change(function(){
	let val = $("select#b_deptno option:selected").val();
	$("#deptno").val(val);
});

$("#btn1").click(function(){
	 //newWin변수에는 새로운 window객체가 저장
	 let newWin=window.open("<%=request.getContextPath()%>/view/mypage/idDuplicate.jsp","w","width=800px,height=500px");
	 //변수명.close(); //창닫기
});
});
const regexPhoneNumber = (target) => {
    target.value = target.value.replace(/[^0-9]/g, '').replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, "$1-$2-$3");
};
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
   
   
   
   
   
   
		<h3>사원등록</h3>
		<table>
		<form name="joinFrm"id="joinFrm" method="post" action="<%=request.getContextPath()%>/register.aa">
				
    
				<tr>
				 <th class="thead">사원번호</th>
				 <td class="tbody">
				 <input type="number" name="empno" id="empno" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" placeholder="사원번호는는 4~6숫자">
				 <input type="button" class="w-btn w-btn-gray" name="btn1" id="btn1" value="사원번호 중복검색"/>
				 </td>
				 <c:if test="${errors.empno}"> 사원번호를 입력하세요.</c:if>
 	   		 <c:if test="${errors.duplicateId}"> 이미 사용중인 사원번호입니다.</c:if>
 	   		 </td>
				</tr>
				<tr>
				 <th class="thead">사원이름</th>
				 <td class="tbody"><input type="text" name="ename" id="ename" placeholder="사원번호 작성"></td>
				 <td class="tbody"></td>
				</tr>
				<tr>
				 <th class="thead">부서번호</th>
				 <td class="tbody" colspan="2">
				 <input type="text" name="deptno" id="deptno" readonly="readonly" placeholder="체크박스 선택하세요">
				 <select name="b_deptno"id="b_deptno">
				 			<option value="${item.deptno}">선택</option>
						<c:forEach var="item" items="${mypageDbEmpList}">
							<option value="${item.deptno}">${item.deptno},${item.deptname}</option>
						</c:forEach>			
					</select>
				 </td>
				</tr>
				
				<tr>
				 <th class="thead">비밀번호</th>
				 <td class="tbody"><input type="password" name="emppwd" id="emppwd" placeholder="비밀번호 입력"></td>
				 <td class="tbody"><c:if test="${errors.emppwd}">비밀번호를 입력하세요</c:if></td>
				</tr>
				
				<tr>
				 <th class="thead">비밀번호 재확인</th>
				 <td class="tbody"><input type="password" name="re_emppwd" id="re_emppwd" placeholder="재확인 비밀번호 입력"></td>
				 <td class="tbody">
				  <c:if test="${errors.re_emppwd}"></c:if>
 	     		<c:if test="${errors.notMatch}"></c:if>
 	     	</td>
				</tr>
				<tr>
				 <th class="thead">직급</th>
				 <td class="tbody"><input type="text" name="level" id="level" placeholder="사원직책 입력"></td>
				 <td class="tbody"></td>
				</tr>
				<tr>
				 <th class="thead">입사일</th>
				 <td class="tbody"><input type="date" name="hiredate" id="hiredate"></td>
				 <td class="tbody"></td>
				</tr>
				<tr>
				 <th class="thead">이메일주소</th>
				 <td class="tbody" colspan="2">
				 	<input type="text" name="f_email" id="f_email" placeholder="이메일 입력란">
				  @
				  <input type="text" name="b_email" id="b_email" placeholder="이메일 주소 입력란">
				  <select name="bb_email"id="bb_email">
				  		<option value="">선택</option>
						<option value="naver.com">naver.com</option>
						<option value="gmail.com">gmail.com</option>
						<option value="nate.com">nate.com</option>
						<option value="daum.net">daum.net</option>
					</select>
				 </td>
				</tr>
				<tr>
				 <th class="thead">전화번호</th>
				 <td class="tbody"><input type="text" name="tel" id="tel" placeholder="010-1111-1111" oninput="regexPhoneNumber(this)"></td>
				 <td class="tbody"></td>
				</tr>
				<tr>
				 <th class="thead">연봉</th>
				 <td class="tbody"><input type="text" name="sal" id="sal" placeholder="연봉입력란"></td>
				 <td class="tbody"></td>
				</tr>
				<tr>
				 <th class="thead">직원등급</th>
				 <td class="tbody"><input type="text" name="grade" id="grade" placeholder="일반사원 =1 관리자=999" ></td>
				 <td class="tbody"></td>
				</tr>
				<tr>
				<td colspan="3">
				<div class="buttons">
				<input type="submit" class="w-btn w-btn-gray" value="등록하기" onclick="check_phone()">
				</div>
				</td>
				</tr>
				
	</form>
 </table> 
       
       
       
</tr>
<tr>
  <td colspan="16">
    <jsp:include page="../module/bottom.jsp" flush="false"/>
  </td>
</tr>
</table>
 
</body>
</html>


