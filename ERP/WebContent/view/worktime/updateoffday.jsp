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
<title>수정하기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<style>
<style>
body {
  padding:1.5em;
  background: #f5f5f5
}

table {
  border: 1px #a39485 solid;
  font-size: .9em;
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
</head>
<body>
<%-- resvUpdate 컨트롤러에 의해 아래와같이 Model받았다
				new	ResvUpdateRequest(empno, resvdate, resvmemo, resvname, resvtel, resvemail, resvno)
					request.setAttribute("resvUpReq", resvUpReq);
		
*DB내용: ${resv}<br/><br/>
--%>
${resvUpReq}
${resvUpReq.empno}
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
   
 <h2>회의실 예약 수정하기</h2>
<form name="updatefrm" id="updateoffday" method="post" action="<%=request.getContextPath()%>/updateoffday.go?empno=${U.empno}"/>
    <table class="resvTable" border="1">
      <thead>
        <tr>
          <th colspan="2">회의실 예약 정보를 기입하세요</th>
        </tr>
      <thead>
      <tbody>
        <tr>
          <td>예약자 사번</td>
          <td>${resvUpReq.empno}</td>
          <td></td>
        </tr>
        <tr>
          <td>예약 날짜</td>
          <td><input type="text" name="bfresvdate" id="bfresvdate" value="${resvUpReq.resvdate}" readonly></td>
          <td><input type="date" name="resvdate"></td>
        </tr>
        <tr>
          <td>예약할 회의실</td>
          <td><input type="text" name="bfroomname" id="bfroomname" value="${resvUpReq.roomname}" readonly></td>
          <td>
          <select name="roomname" id="roomname">
          	<option value="">선택하세요</option>
          	<option value="회의실1">회의실1</option>
          	<option value="회의실2">회의실2</option>
          </select>
          </td>
        </tr>
        <tr>
          <td>예약 관련 메모</td>
          <td><input type="text" name="bfresvmemo" id="bfresvmemo" size=50px value="${resvUpReq.resvmemo}" readonly></td> <!-- 기존입력값 -->
          <td><input type="text" name="resvmemo" id="resvmemo" size=50px placeholder="예약 관련한 메모를 작성하세요(선택)"></td>
        </tr>
        <th colspan="2">사용자 예약 정보를 기입하세요</th>
        <tr>
          <td>사용자 또는 부서</td>
          <td><input type="text" name="bfresvname" id="bfresvname" size=30px value="${resvUpReq.resvname}" readonly></td><!-- 기존입력값 -->
          <td><input type="text" name="resvname" id="resvname" size=30px placeholder="사용자명 또는 부서명을 입력하세요"></td>
        </tr>
        <tr>
          <td>사용자 연락처</td>
          <td><input type="text" name="bfresvtel" id="bfresvtel" size=30px value="${resvUpReq.resvtel}" readonly></td><!-- 기존입력값 -->
          <td><input type="text" name="resvtel" id="resvtel" size=30px placeholder="사용자 대표 연락처를 입력하세요"></td>
        </tr>
        <tr>
          <td>사용자 이메일</td>
          <td><input type="text" name="bfresvemail" id="bfresvemail" size=30px value="${resvUpReq.resvemail}" readonly></td><!-- 기존입력값 -->
          <td><input type="text" name="resvemail" id="resvemail" size=30px placeholder="사용자 대표 이메일주소를 입력하세요"></td>
        </tr>
        <tr>
        	<input type="hidden" name="resvno" id="resvno" value="${resvUpReq.resvno}">
          <th colspan="2"><input type="submit" value="예약수정하기"></th>
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