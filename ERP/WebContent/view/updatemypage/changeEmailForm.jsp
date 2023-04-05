<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/buttonSubmit.css">
<title>비번변경폼</title>
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
  text-align: letf;
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
    text-align: left;
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
	.error {color:red; font-size:0.8em;}
</style>

<script>



$(document).ready(function(){
	
$("form#joinFrm").submit(function(){  

	if($("#curf_email").val()==""){ 
		alert("현재메일을 입력하세요.");
		return false; 
	}
	
	if($("#curb_email").val()==""){ 
		alert("현재메일을 입력하세요.");
		return false; 
	}
	
	if($("#newf_email").val()==""){ 
		alert("변경할 메일을 입력하세요.");
		return false; 
	}

	if($("#newb_email").val()==""){ 
		alert("변경할 메일을 입력하세요.");
		return false; 
	}

});
	
	
$("#curbb_email").change(function(){
	let val = $("select#curbb_email option:selected").val();
	$("#curb_email").val(val);
});

$("#newbb_email").change(function(){
	let val = $("select#newbb_email option:selected").val();
	$("#newb_email").val(val);
});

});

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
 <form name="joinFrm" id="joinFrm"action="mypageupdateemail.aa" method="post">
 <table>
 <tr>
 	<th class="thead">
 	현재 이메일
 	</th>
 	<th class="tbody">
 					<input type="text" name="curf_email" id="curf_email" placeholder="이메일 입력란">
				  @
				  <input type="text" name="curb_email" id="curb_email" placeholder="이메일 주소 입력란">
				  <select name="curbb_email"id="curbb_email">
				  		<option value="">선택</option>
						<option value="naver.com">naver.com</option>
						<option value="gmail.com">gmail.com</option>
						<option value="nate.com">nate.com</option>
						<option value="daum.net">daum.net</option>
					</select>
 	 <span class="error"><c:if test="${errors.curEmail}">이메일를 입력하세요</c:if></span>
   <span class="error"><c:if test="${errors.badCurEmail}">이메일이 일치하지 않습니다</c:if></span>
 	</th>
 </tr>
  <tr>
 	<td class="thead">
 	new 이메일
 	</td>
 	<td class="tbody">
	 	<input type="text" name="newf_email" id="new_email" placeholder="이메일 입력란">
				  @
				  <input type="text" name="newb_email" id="newb_email" placeholder="이메일 주소 입력란">
				  <select name="newbb_email"id="newbb_email">
				  		<option value="">선택</option>
						<option value="naver.com">naver.com</option>
						<option value="gmail.com">gmail.com</option>
						<option value="nate.com">nate.com</option>
						<option value="daum.net">daum.net</option>
					</select>
	 	<input type="hidden" value="">
	 	<span class="error"><c:if test="${errors.newEmail}">새 이메일를 입력하세요</c:if></span>
 	</td>
 </tr>
  <tr>
 	<td class="tbody" style="text-align: center;">
 		<input type="submit" class="w-btn w-btn-gray"  value="이메일변경"/>
 		<input type="hidden" name="empno" value="${empno}">
 	</td>
 	<td class="tbody" style="text-align: center;">
 		<input type="reset" class="w-btn w-btn-gray"  value="취소"/>
 	</td>
 </tr>
</table> 
</form>


</body>
</html>








