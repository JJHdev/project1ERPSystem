<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<!DOCTYPE html>
<html lang="ko">
<head>
 <meta name="description" content="member board Web Application">
 <meta name="keywords" content="member, board, article, mvc">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/tableLeft.css?after">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/buttonSubmit.css">
<title>연차 기록 보기</title>
 <script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
 <style>
thead{
    color: #fff;
    background: #73685d;
 font-size:20px;
  border-radius: 10px;
 border-bottom: 1px solid #e5e5e5;
  border-bottom: 1px solid rgba(0,0,0,.1);
  font-weight: bold;
  
 }
tr{
font-size:20px;
  border-radius: 10px;
 border-bottom: 1px solid #e5e5e5;
  border-bottom: 1px solid rgba(0,0,0,.1);
  text-align:center;
}
th{
font-size:23px;
  border-radius: 10px;
 border-bottom: 1px solid #e5e5e5;
  border-bottom: 1px solid rgba(0,0,0,.1);
  text-align:center;
  }
 </style>

 <script type="text/javascript">
	window.onload = function() {
		today = new Date();
		console.log("today.toISOString() >>>" + today.toISOString());
		today = today.toISOString().slice(0, 10);
		console.log("today >>>> " + today);
		bir = document.getElementById("todaydate");
		bir.value = today;
	}
 </script>
  <%-- <script>
 
 function offhis(){
	 
	 var popupWidth = 1000;
	 var popupHeight = 900;

	 var popupX = Math.ceil(( window.screen.width - popupWidth )/2);
	 var popupY = Math.ceil(( window.screen.height - popupHeight )/2); 
	 
	 
	 window.open("<%=request.getContextPath()%>/updateoffday.aa?offdayno=${item.offdayno}&empno=${item.empno}&deptno=${item.deptno}&deptname=${item.deptname}&startday=${item.startday}&endday=${item.endday}&offnotice=${item.offnotice}","연차기록",'width='+popupWidth+',height='+popupHeight+',left='+popupX+', top='+ popupY)
	
	 "location.href='<%=request.getContextPath()%>/updateoffday.aa?offdayno=${item.offdayno}&empno=${item.empno}&deptno=${item.deptno}&deptname=${item.deptname}&startday=${item.startday}&endday=${item.endday}&offnotice=${item.offnotice}'"
 
 }//modifyChk()끝
 
 
 </script> --%>
 
 
</head>
<body>
 <h2>연차 사용 기록</h2>
 <hr/>

<%--    <c:forEach  var="item" items="${offday.offdays}">
	     <c:out value="${item}" /> <br/>
	     *사원번호:<c:out value="${item.empno}" /><br/>
	     *부서명:<c:out value="${item.deptname}" /><br/>
	     *연차시작일:<c:out value="${item.start_offday}" /><br/>
	     *연차종료일:<c:out value="${item.end_offday}" /><br/>
	     *연차사유:<c:out value="${item.offnotice}" /><br/>
	     <br/><br/>
 	 </c:forEach>
  --%>
 
 
	<table  border="1">
	  	 <thead class="tab1"> 

		 	<tr>
				 	
		    	 <th>사원번호</th>		<!-- 접속한 사용자 세션에서 받아와 보여준다 -->
				 <th>부서이름</th>		<!-- 접속한 사용자 세션에서 받아와 보여준다 -->
			  	 <th>연차 시작일</th> <!-- 선택항목을 몇개준다 ex)없음(디폴트),단순지각,질병관련,기타 -->
			  	 <th>연차 종료일</th>
			  	 <th>연차 사유</th>
			  	 <th>연차일정수정</th>
			  	 <th>연차등록취소</th>
			</tr>
		</thead>
   		
   		<tbody>
   		
  		 <c:forEach var="item" items="${offday.offdays}"> 
 		<input type="hidden" name="todaydate" id="todaydate"> 
		<input type="hidden" name="offdayno" id="offdayno" value="${item.offdayno}" >   			 
   		<input type="hidden" name="offdayno" id="offdaydeptno" value="${item.deptno}" >
   			 <tr>
   			     
		   		 <td>${item.empno}</td>
		   		 <td>${item.deptname}</td>
		   		 <td>${item.startday}</td>
		   		  <td>${item.endday}</td>
		   		   <td>${item.offnotice}</td>
				<td>
				<button class="w-btn w-btn-gray" id="updatebtn" onclick="location.href='<%=request.getContextPath()%>/updateoffday.aa?offdayno=${item.offdayno}&empno=${item.empno}&deptno=${item.deptno}&deptname=${item.deptname}&startday=${item.startday}&endday=${item.endday}&offnotice=${item.offnotice}'" >연차수정하기</button>
	  	<%-- <a href="<%=request.getContextPath()%>/updateoffday.go?offdayno=${item.offdayno}&empno=${item.empno}&deptname${item.deptname}&start_offdat${item.start_offday}&end_offday${item.end_offday}&offnotice${item.offnotice}" id="updatebtn"><input type="button" value="연차수정하기"></a> --%>
				</td>
					 
				<td>
				
		<a href="<%=request.getContextPath()%>/deletedayoff.aa?offdayno=${item.offdayno}" id="deloffA">
			<c:if test="${EMP_USER.grade eq 999}">
				<input type="button" class="w-btn w-btn-gray" id="deloffbtn"value="연차등록취소">
			</c:if>
				<c:if test="${EMP_USER.grade eq 1}">
					<input type="button" id="deloffbtn"value="관리자 문의" disabled >
				</c:if>
		</a>
				</td>
				
	   		  </tr>
	   		</c:forEach> 
		   		
	   	
		</tbody>
	</table>
 			 <div>
	   		  	**수정 참고 사항**<br/>
	   		  	-최근 10건의 휴가 사용 기록만 제공 가능합니다.<br>
	   		  <div class="offnoti1">-이미 시작된 연차는 삭제,수정이 불가합니다.</div>
	   		  	-특정 기간 기록 필요시, helpdesk로 시작일,종료일,사용 목적을 담아 요청해주세요.<br>
	   		  	-연차 등록 취소는 연차 시작일전에만 가능합니다.<br>
	   		 </div>
 
 
 
 
 
 
 
</body>
</html>