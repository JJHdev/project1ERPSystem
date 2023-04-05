<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="description" content="ConferenceRoom reservation">
  <meta name="keywords" content="room,resv">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/buttonSubmit.css?after">
<title>회의실 예약</title>
 <script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<style>
body {
  padding:1.5em;
  background: #f5f5f5
}

table {
  border: 1px #a39485 solid;
  font-size: 1em;
  box-shadow: 0 2px 5px rgba(0,0,0,.25);
  width: 100%;
  border-collapse: collapse;
  border-radius: 5px;
  overflow: hidden;
}

th {
  text-align: left;
}
  
.thead {
  font-weight: bold;
  color: #fff;
  background: #73685d;
}
  
 td, th {
  padding: 1em .5em;
  vertical-align: top;
}
  
 td {
  border-bottom: 1px solid rgba(0,0,0,.1);
  background: #fff;
}

a {
  color: #73685d;
}
  
 @media all and (max-width: 768px) {
    
  table, .thead, .tbody, th, td, tr {
    display: block;
  }
  
  th {
    text-align: right;
  }
  
  table {
    position: relative; 
    padding-bottom: 0;
    border: none;
    box-shadow: 0 0 10px rgba(0,0,0,.2);
  }
  
  .thead {
    float: left;
    white-space: nowrap;
  }
  
  .tbody {
    overflow-x: auto;
    overflow-y: hidden;
    position: relative;
    white-space: nowrap;
  }
  
  tr {
    display: inline-block;
    vertical-align: top;
  }
  
  th {
    border-bottom: 1px solid #a39485;
  }
  
  td {
    border-bottom: 1px solid #e5e5e5;
  }
  }
</style>
<script>
  $(document).ready(function(){
	  $("form#resvFrm").submit(function(){  
			
			let frmObj = $(this);
			
			//사원번호필수입력
			if($("#empno").val()==""){ //미입력시
				alert("사원번호를 입력하세요.");
				$("#empno").focus(); //포커스위치
				return false; 
			}
			//예약일 필수선택
			if($("#resvdate").val()==""){
				alert("예약일을 선택하세요.");
				$("#resvdate").focus(); 
				return false; 
			}
			//회의실 필수선택
			if($("#roomname").val()==""){ 
				alert("회의실을 선택하세요.");
				$("#roomname").focus(); 
				return false; 
			}
			//사용자 필수입력
			if($("#resvname").val()==""){ 
				alert("사용자명을 입력하세요.");
				$("#resvname").focus(); 
				return false; 
			}
			//사용자 연락처 필수입력
			if($("#resvtel").val()==""){ 
				alert("사용자 연락처를 입력하세요.");
				$("#resvtel").focus(); 
				return false; 
			}
			//사용자 이메일 필수입력
			if($("#resvemail").val()==""){ 
				alert("사용자 이메일을 입력하세요.");
				$("#resvemail").focus(); 
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

   
<h2>회의실 예약하기</h2>
<form name="resvFrm" id="resvFrm" method="post" action="<%=request.getContextPath()%>/resv.aa">
    <table border="1">
    
      <thead>
        <tr>
        <th class="thead" colspan="2"><center>----- 회의실 정보를 입력하세요 -----</center></th>
        </tr>
      </thead>
       <tbody>
       	<tr>
	        <th class="thead">예약자 정보</th>
	        <td><input type="hidden" name="empno" id="empno" value="${EMP_USER.empno}">${EMP_USER.ename}</td>
        </tr>
        <tr>
	        <th class="thead">예약 날짜</th>
	        <td><input type="date" name="resvdate" id="resvdate" ></td>
	        
        </tr>
        <tr>
	        <th class="thead">예약할 회의실</th>
	        <td><select name="roomname" id="roomname" onchange="selectBoxChange(this.value);">
	          	<option value="">선택하세요</option>
	          	<option value="회의실1">회의실1</option>
	          	<option value="회의실2">회의실2</option>
	          </select></td>
        </tr>
        <tr>
        	<th class="thead">예약관련 메모</th>
					<td><input type="text" name="resvmemo" id="resvmemo" size=50px placeholder="예약 관련한 메모를 작성하세요(선택)"></td>
      	</tr>
       </tbody>
        
        <thead>
        <tr>
        	<th class="thead" colspan="2"><center>----- 사용자 관련 정보를 입력하세요 -----</center></th>
        </tr>
        </thead>
        <tbody>
	        <tr>
	          <th class="thead">사용자 성함 또는 부서명</th>
	          <td><input type="text" name="resvname" id="resvname" size=30px placeholder="사용자명 또는 부서명을 입력하세요"></td>
	        </tr>
	        <tr>
	          <th class="thead">사용자 대표 연락처</th>
	          <td><input type="text" name="resvtel" id="resvtel" size=30px placeholder="- 포함하여 입력하세요"></td>
	        </tr>
	        <tr>
	          <th class="thead">사용자 대표 이메일</th>
	          <td><input type="text" name="resvemail" id="resvemail" size=30px placeholder="@ 포함하여 이메일주소를 입력하세요"></td>
	        </tr>
	        <tr>
	          <td colspan="2"><input type="submit" value="예약하기" class="w-btn w-btn-gray" style="float: right;"/></td>
	        </tr>
     	 </tbody>
    	</table>
</form>
  <script>
  
  var now_utc = Date.now()
  var timeOff = new Date().getTimezoneOffset()*60000;
  var today = new Date(now_utc-timeOff).toISOString().split("T")[0];
  document.getElementById("resvdate").setAttribute("min", today);
  
  </script>	  
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