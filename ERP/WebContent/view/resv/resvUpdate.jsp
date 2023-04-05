<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="description" content="ConferenceRoom reservation">
  <meta name="keywords" content="room,resv">

  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/buttonSubmit.css">
<title>회의실 예약 수정하기</title>
 <script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<style>
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

 .td1{
  	background-color: #c2c2c2;
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
	  $("form#resvUpFrm").submit(function(){  
			
			let frmObj = $(this);
			
			//회원번호필수입력
			if($("#empno").val()==""){ //미입력시
				alert("회원번호를 입력하세요.");
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
<!-- 주소값에 파라미터 숨기기 -->
<script type="text/javascript">
	history.replaceState({}, null, location.pathname);

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
   
<%-- resvUpdate 컨트롤러에 의해 아래와같이 Model받았다
				new	ResvUpdateRequest(empno, resvdate, resvmemo, resvname, resvtel, resvemail, resvno)
					request.setAttribute("resvUpReq", resvUpReq);
		
*DB내용: ${resvUpReq}<br/><br/>
--%>
<h2>회의실 예약 수정하기</h2>
<form name="resvUpFrm" id="resvUpFrm" method="post" action="<%=request.getContextPath()%>/resvUpdate.aa?empno=${resvUpReq.empno}">
    <table class="resvTable" border="1">
      <thead>
        <tr>
        	<th class="thead"></th>
	        <th class="thead" style="font-size:15px" width=300px><center>기존 예약</center></th>
	        <th class="thead" style="font-size:15px"><center>수정 예약</center></th>
        </tr>
      </thead>
      <tbody>
          <input type="text" name="empno" id="empno" value="${resvUpReq.empno}" style="display:none;">
        <tr>
          <th class="thead" style="font-size:14px">예약 날짜</th>
          <!-- <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 날짜포맷 위해 라이브러리 추가해야함 -->
          <td class="td1"><fmt:formatDate value="${resvUpReq.resvdate}" pattern="yyyy-MM-dd"/></td>
          <td><input type="date" name="resvdate" id="resvdate"></td>
        </tr>
        <tr>
          <th class="thead" style="font-size:14px">예약할 회의실</th>
          <td class="td1">${resvUpReq.roomname}</td>
          <td>
          <select name="roomname" id="roomname">
          	<option value="">선택하세요</option>
          	<option value="회의실1">회의실1</option>
          	<option value="회의실2">회의실2</option>
          </select>
          </td>
        </tr>
        <tr>
          <th class="thead" style="font-size:14px">예약 관련 메모</th>
          <td class="td1">${resvUpReq.resvmemo}</td>
          <td><input type="text" name="resvmemo" id="resvmemo" size=50px placeholder="예약 관련한 메모를 작성하세요(선택)"></td>
        </tr>
        <tr>
          <th class="thead" style="font-size:14px">사용자 또는 부서</th>
          <td class="td1">${resvUpReq.resvname}</td>
          <td><input type="text" name="resvname" id="resvname" size=30px placeholder="사용자명 또는 부서명을 입력하세요"></td>
        </tr>
        <tr>
          <th class="thead" style="font-size:14px">사용자 연락처</th>
          <td class="td1">${resvUpReq.resvtel}</td>
          <td><input type="text" name="resvtel" id="resvtel" size=30px placeholder="- 포함하여 입력하세요"></td>
        </tr>
        <tr>
          <th class="thead" style="font-size:14px">사용자 이메일</th>
          <td class="td1">${resvUpReq.resvemail}</td>
          <td><input type="text" name="resvemail" id="resvemail" size=30px placeholder="@ 포함하여 이메일주소를 입력하세요"></td>
        </tr>
        <tr>
        	<input type="hidden" name="resvno" id="resvno" value="${resvUpReq.resvno}">
          <td colspan="3"><input type="submit" value="예약수정하기"  class="w-btn w-btn-gray" style="float: right;"/></td>
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