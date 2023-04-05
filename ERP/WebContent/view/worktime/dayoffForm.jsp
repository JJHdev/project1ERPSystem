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
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/dayoffForm.css?after">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/buttonSubmit.css">
 
  
<title>연차 관리</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<style>

 </style>
 <script type="text/javascript">
	window.onload = function() {
		today = new Date();
		console.log("today.toISOString() >>>" + today.toISOString());
		today = today.toISOString().slice(0, 10);
		console.log("today >>>> " + today);
		bir = document.getElementById("writeoffday");
		bir.value = today;
		
		var today = new Date();
		today2 = new Date(Date.parse(today.toISOString().slice(0, 10)));
		console.log("today2 >>>> " + today2);
		document.getElementById("todaydate").value = today2;
	}
	
</script>
 <script>
 
 function offhis(){
	 
	 var popupWidth = 1100;
	 var popupHeight = 1000;

	 var popupX = Math.ceil(( window.screen.width - popupWidth )/2);
	 var popupY = Math.ceil(( window.screen.height - popupHeight )/2); 
	 
 window.open("<%=request.getContextPath()%>/offlist.aa","연차기록",
		'width='+popupWidth+',height='+popupHeight+',left='+popupX+', top='+ popupY);
}
 
 
 </script>
</head>
<body>
 
<table>
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
   
	<h2>연차 관리</h2>

       <!--  내용 부분 : 시작 -->
   
    
   <form name="dayoffFrm" id="dayoffFrm"  action="<%=request.getContextPath()%>/offday.aa">  
	
					  <!--  출근시간찍어보자 게시판 글쓰면 작성시간 담기는 기능 이용하자 -->
   <table id="table1" border="1">
 	
		 	<tbody>
		 	 <tr> 
		 	  <th class="thead">사원 번호</th>
		 	  <td>
		 	  <input type="text" name="offempno" id="offempno" value="${EMP_USER.empno}" readonly>
		 	  </td>
		 	 </tr>
		 	
		 	 <tr>
		 	  <th class="thead">사원 이름</th>
		 	 <td>
		 	  <input type="text" name="offempname" id="offempname" value="${EMP_USER.ename}" readonly>
		 	   <input type="hidden" name="offempno" value="${EMP_USER.deptno}" readonly>
		 	 </td>
		 	 </tr>
	
 	   </tbody>
 </table>
 
 	
 	 <table border="1">
		 	<tbody>
		 	 <tr> 
		 	 <th class="thead">연차 신청일</th>
		 	  <td>
		 	  <!-- 글작성일로 자동 기입 -->
		 	 <input type="date" name="writeoffday" id="writeoffday" readonly/>
		 	 <input type="hidden" name="todaydate" id="todaydate">
 		 	<span class=nonupdate> ※연차 신청일 변경 불가</span>
		
		 	  </td> 
		 	 </tr>
		 	
		 	 <tr>
		 	  <th class="thead">연차 기간</th>
		 	 <td>
		 	 <!--몇일부터 ~ 몇일까지   =  (일수 몇ㅇ   -->
		 		 
  				<input type="date" name="startday" 
					         id="startday"
					         max="2080-12-31"
					         min="2022-01-01"
					         required />
					~
		 		  <input  type="date" name="endday" 
					         id="endday"
					         max="2080-12-31"
					         min="2022-01-01"
					        required />
			
	<script>
	 $(document).ready(function(){
	       $("#btn1").click(function(){
		let strD1 = document.querySelector("#startday").value;
		let strD2 = document.querySelector("#endday").value;
		let startoday = document.querySelector("#todaydate").value;
		
		  let d1=new Date(strD1);
	      let d2=new Date(strD2);
	      let d3=new Date(startoday);
	      
		let arr1 = strD1.split('-');
		let arr2 = strD2.split('-');
		let arr3 = startoday.split('-');
		
		let dat1 = new Date(arr1[0], arr1[1], arr1[2]);
		let dat2 = new Date(arr2[0], arr2[1], arr2[2]);
		let dat3 = new Date(arr3[0], arr3[1], arr3[2]);
		
		
		let diff = dat2 - dat1;
		let currDay = 24 * 60 * 60 * 1000;// 시 * 분 * 초 * 밀리세컨
		let offcnt =parseInt(diff/currDay);
		$("#offcnt").val(offcnt);	
	       
		//yyyymmdd 체크
			if (d1 > d2) {
				alert("시작일은 종료일보다 늦을 수 없습니다.");
				window.location.reload();
				return false;
			}
			if(strD1=="" && strD2 ==""){
				alert("시작일과 종료일을 선택해 주세요.");
				return false ;
			}
			if(strD2 =="" ){
				alert("종료일을 선택해 주세요.");
				return false ;
			}
			if(strD1 =="" ){
				alert("시작일을 선택해 주세요.");
				return false ;
			}
			if (d3 > d1) {
				alert("시작일은 오늘 이후로만 가능합니다.");
				window.location.reload();
				return false ;
			}
			if (d1 > d2 || d3 <= d1 ) {
				 alert("* 사용 연차 수 : " + offcnt + "일");
				 return true;
				}
		
	       });
	       		 
	    });
			
	</script>
	<!-- 
	
	1.date1 이 date2보다 크면 안된다
	2.휴가 신청일보다  date1이 적을수 없다.
	3.date1=date2가 같고 반일을 선택했다면 0.5일로 계산한다.
	  
	  -->
		<input type="hidden"  id="offcnt" >
		<input type="button"  id="btn1" value="일정 확인" />
		<!-- 	<div id="daycnt"></div>		  
		 	<input type="button" name="offcntbtn" id="offcntbtn" value="연차사용수량체크"/>
		 	 -->
		 	 </td>
		 	 </tr>
				 	 <tr>
				 	  <th class="thead" >연차 사유</th>
				 	  <td>
				<select name="offnotice" id="offnotice" required> 
					
				 	<c:if test="${workin.work_in_notice eq '개인 일정'}">
					   <option value="개인일정" selected="selected">개인 일정</option>
					</c:if> 
						<option value="개인일정">개인 일정</option>
					
					 <c:if test="${workin.work_in_notice eq '병가'}">
					   <option value="병가" selected="selected">병가</option>
					</c:if> 
						<option value="병가" >병가</option>
					<c:if test="${workin.work_in_notice eq '하계 휴가'}">
				
					   <option value="하계휴가" selected="selected">하계 휴가</option>
					</c:if> 	
						<option value="하계휴가">하계 휴가</option>
					
					<c:if test="${workin.work_in_notice eq '출산 휴가'}">
					   <option value="출산휴가" selected="selected">출산 휴가</option>
					</c:if> 
						<option value="출산휴가">출산 휴가</option>
						
						
					<c:if test="${workin.work_in_notice eq '기타 사유'}">
					   <option value="기타" selected="selected">기타 사유</option>
					</c:if> 
						<option value="기타">기타 사유</option>
				</select>
<script>
	$(document).ready(function(){
		
	       $("#saveoff").click(function(){
	    	   
	    	 	 	 alert("일정 등록에 성공 했습니다.");
			
	       });
		 });
	
</script>  	

						
				  </td>
				 </tr>
	 		 	 	 	 
			 	<tr style=border:none;> 
			 	<td style=border:none;>
			
					<input type="button" class="w-btn w-btn-gray" name="offhisbtn" id="offhisbtn" value="연차기록조회"  onclick="offhis()">
				</td>

			 	
	 	 		<td style=border:none;>
					<input type="submit" class="w-btn w-btn-gray" name="saveoff" id="saveoff" value="연차등록하기">
				</td> 
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