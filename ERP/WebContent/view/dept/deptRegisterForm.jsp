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
</style>
<script>

$(document).ready(function(){
	$("#btn1").on("click",function(){
		
		//유효성검사
		let idVal1 = $("#deptno").val();
		if(idVal1==""){
			alert("부서번호는 필수 입력입니다");
			$("#deptno").focus();
			return false;
		}
		
		let idVal2 = $("#deptname").val();
		if(idVal2==""){
			alert("부서이름은 필수 입력입니다");
			$("#deptname").focus();
			return false;
		}

	 //user가 입력한 id값을  
	 //회원가입창의 <input type="text" name="memberid" id="mid">요소의 값으로 설정
	 this.document.deptDuplicationFrm.deptno.value=deptDuplicationFrm.deptno.value;
	
	});
});

let idVal = $("#idTxt").val();
let queryS = {id:idVal};
console.log(queryS);

$(document).ready(function(){
	$("#btn2").on("click",function(){
		
		let deptnoVal = $("#deptno").val();
		let deptnameVal = $("#deptname").val();
		
		$.ajax({
				type:"GET",//요청방식.
				
				url:"<%=request.getContextPath()%>/mypageInsertDeptController.aa",
				
				data:{"deptname":deptnameVal,"deptno":deptnoVal},//서버로 전송할 데이터. 예){name:"홍GD"}
		
				success:function(data){
 					alert("새로운 부서를 만들었습니다.");
	 			//매개변수 data 에는 보낸 컨트롤러에서 응답받은 자료가 온다.
				},//정상응답후 호출되는 함수
				
				error:function(xhr,status,error){
					alert("새로운 부서를 만드는데 실패했습니다.");
					let v="";
					v+="\n에러코드 =>"+xhr.status;
					v+="\n status =>"+status;
					v+="\n에러메세지 =>"+xhr.responseText;
					v+="\n =>"+error;
					alert(v);
				},//오류발생시 호출되는 함수
				complete:function(){
				} //작업완료후 호출되는 함수
			});
		
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
   
   
   
		<h3>부서등록</h3>
<table>
	<form name="deptDuplicationFrm" id="deptDuplicationFrm" method="post" action="<%=request.getContextPath()%>/mypageDeptDuplicate.aa">
		 <tr>
		  <th width="25%" class="thead">부서번호</th>
		  <td class="tbody" colspan="2">
		  <input type="text" name="deptno" id="deptno" value="${Deptno}" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" placeholder="부서번호는는 2~4숫자">
		  <input type="submit" name="btn1" id="btn1" value="부서번호 중복검색" class="w-btn w-btn-gray" />
		  </td>
		 </tr>
		  <tr>
		  <th width="25%" class="thead">부서이름</th>
		  <td class="tbody" colspan="2"><input type="text" name="deptname" id="deptname" value="${DeptName}"></td>
		 </tr>
		  <tr>
		  <th width="25%" class="thead">기존 부서명</th>
		  <td class="tbody" colspan="2">
		  <select name="b_deptno"id="b_deptno">
					<c:forEach var="item" items="${mypageDbEmpList}">
					<option value="${item.deptno}">${item.deptno},${item.deptname}</option>
					</c:forEach>			
			</select>
		  </td>
		 </tr>
		 	<tr>
		 		<td>
			<c:if test="${resultNo==0}">
			<c:if test="${resultName==0}">
				만들수있는 부서입니다. <input type="button" id="btn2" value="부서만들기." class="w-btn w-btn-gray" />
			</c:if>  
			</c:if>  
			
			<c:if test="${resultNo==1}">
				사용 중인 부서번호입니다.
			</c:if>  	
			
			<c:if test="${resultName==1}">
				사용 중인 부서이름입니다.
			</c:if>  	
		 		</td>
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


