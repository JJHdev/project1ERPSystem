<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
 <meta charset="UTF-8">
 <meta name="description" content="member board Web Application">
 <meta name="keywords" content="member, board, article, mvc">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/tableLeft.css?after">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/buttonSubmit.css">
 
 <title>근태 관리-연차 수정</title>
 <script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
 <style>

 input{
  font-size:20px;
 width: 150px;
   text-align:center;
  border-radius: 7px;
 }
 tr#t1{
 font-size: 35px;
 }
#updatebtn1{
   text-align:center;
 }
 thead{
    color: #fff;
    background: #73685d;
 font-size:20px;
  border-radius: 7px;
 border-bottom: 1px solid #e5e5e5;
  border-bottom: 1px solid rgba(0,0,0,.1);
  font-weight: bold;
  
 }
tr{
 font-size:20px;
  border-radius: 7px;
 border-bottom: 1px solid #e5e5e5;
  border-bottom: 1px solid rgba(0,0,0,.1);
  text-align:center; 
}
th{
font-size:23px;
  border-radius: 7px;
 border-bottom: 1px solid #e5e5e5;
  border-bottom: 1px solid rgba(0,0,0,.1);
  text-align:center;
  }
select#offnotice{
 text-align:center;
 font-size:23px;
  border-radius: 10px;
 border-bottom: 1px solid #e5e5e5;
  border-bottom: 1px solid rgba(0,0,0,.1);
}
button#fixbtn{
font-size: 20px;
 border-radius: 10px;
}
 
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

</head>
<body>
		<form name="up" id="updatebtn1" method="post" action="<%=request.getContextPath()%>/updateoffday.aa">
			<table  border="1">
			 <thead> 
				<tr>변경 전 </tr>
		 	<tr>
				 <th>사원번호</th>
				 <th>부서이름</th>	
				 	<!-- 접속한 사용자 세션에서 받아와 보여준다 -->
			  	 <th>연차 시작일</th>
			  	 <th>연차 종료일</th>
			  	 <th>연차 사유</th>
			  	 <th></th>
			</tr>
				</thead>
			 <tbody>
			  <tr>
		   		 <td>${OffDayUpdateRequest.empno}</td>
		   		 <td>${OffDayUpdateRequest.deptname}</td>
		   		 <td>
					<fmt:formatDate value="${OffDayUpdateRequest.startday}" pattern="yyyy-MM-dd" />
		   		 </td>
		   		  <td>
		   		  	<fmt:formatDate value="${OffDayUpdateRequest.endday}" pattern="yyyy-MM-dd" />
		   		  </td>
		   		   <td>${OffDayUpdateRequest.offnotice}</td>
			<td>
				<button type="button" id="fixbtn" value="수정불가버튼" disabled="disabled" onclick="closeTabClick()">일정 변경하기</button>
			</td>
			</tr>
			</tbody>
		</table>
							<!--  변경 후 정보 받기 -->	
					<br/>		
 <script>
 $(document).ready(function(){
       $("#btn1").click(function(){
	let strD1 = document.querySelector("#startday").value;
	let strD2 = document.querySelector("#endday").value;
	let startoday = document.querySelector("#todaydate").value;
	 console.log("startoday=="+startoday); 
	
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
			alert("※시작일은 종료일보다 늦을 수 없습니다.");
			window.location.reload();
			return false;
		}
		if(strD1=="" && strD2 ==""){
			alert("※시작일과 종료일을 선택해 주세요.");
			return false ;
		}
		if(strD2 =="" ){
			alert("※종료일을 선택해 주세요.");
			return false ;
		}
		if(strD1 =="" ){
			alert("※시작일을 선택해 주세요.");
			return false ;
		}
		if (d3 > d1) {
			alert("※시작일은 오늘 이후로만 가능합니다.");
			window.location.reload();
			return false ;
		}
		if (d1 > d2 || d3 <= d1 ) {
		/* 	 alert("※연차 일정이 변경되었습니다."); */
			 return true;
			}
		 
	
       });
   
    });
/*  function closeTabClick() {
	
	let chek = document.querySelector("#offcnt").value;
	
	
	 if(chek!==""){
 		window.close();
 
		 }
	 };  
	 */
 
 
</script>

<!--  <script>
 
	  function closeTabClick() {
		  
          window.close();
      };
 
 
 </script> -->
		<table  border="1">
				 	
					 <thead> 
					
					<tr id="t1">변경 후 </tr>
		 	<tr>
	
				 <th>사원번호</th>
				 <th>부서이름</th>	
				 	<!-- 접속한 사용자 세션에서 받아와 보여준다 -->
			  	 <th>연차 시작일</th>
			  	 <th>연차 종료일</th>
			  	 <th>연차 사유</th>
			  	 <th></th>
			</tr>
				</thead>
				
			<tbody>
			
			
			
			
			 <tr>
			 
			   		 <td>${OffDayUpdateRequest.empno}</td>
			   		 
			   		 <td>${OffDayUpdateRequest.deptname}</td>
			   		 
			   		 
			   		 <td><input type="date" name="startday" 
					         id="startday"
					         max="2080-12-31"
					         min="2022-01-01"
					         required />
					</td>
			   		  <td><input  type="date" name="endday" 
					         id="endday"
					         max="2080-12-31"
					         min="2022-01-01"
					        required />
					  </td>
			   		<td>
			   		
					<select name="offnotice" id="offnotice" required> 
						
						<option value="개인일정">개인 일정</option>
						<option value="병가" >병가</option>
						<option value="하계휴가">하계 휴가</option>
						<option value="출산휴가">출산 휴가</option>
						<option value="기타">기타</option>
						
					</select>
						
				  </td>
			
				<th colspan="2">
			<!-- 	<input type="submit" id="btn1" value="일정 변경하기"> -->
				
				<input type="submit" name="offhisbtn" id="btn1"  value="일정 변경하기" onclick="offhis()">
			<!-- 	<input type="button" class="w-btn w-btn-gray" name="offhisbtn" id="offhisbtn" value="연차기록조회"  onclick="offhis()"> -->
				
				
				</th>
			
			 </tr>
			 
			 	</tbody>
			 </table>
	
			  <input type="hidden" name="todaydate" id="todaydate">
			 <input type="hidden" name="writeoffday" id="writeoffday" readonly/>
			 <input type="hidden"  name="deptname" id="deptname1"  value="${OffDayUpdateRequest.deptname}">
			 <input type="hidden"  name="offdayno" id="offdayno1"  value="${OffDayUpdateRequest.offdayno}">
			 <input type="hidden" name="deptno" id="deptno1" value="${OffDayUpdateRequest.deptno}">
	</form>
	
	<!-- <input type="button" id="closebtn" value="닫기" onclick="window.close()"> -->
	
</body>
</html>