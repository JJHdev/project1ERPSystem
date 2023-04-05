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
  <%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/css/tableTop.css?after"> --%>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/workForm.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/buttonSubmit.css">
  
<title>출근 시간 관리</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<style>
</style>

<script>
$(document).ready(function(){
 		
 		//특정회원조회
 		$("#operator1").click(function(){
 			//필수입력체크
 			let midVal = $("#daychek").val();
 			
 			if(midVal !=""){
 				alert("이미 금일 출석 기록이 있습니다.");
 				return false;
 			}
 			if(midVal==""){
 				alert("출근 시간이 정상 기록되었습니다.");
 				return true;
 			}
 		});
});
 		
	</script> 
<script>	
	$(document).ready(function(){
 		
 		//특정회원조회
 		$("#operator2").click(function(){
 			//필수입력체크
 			let midVal = $("#daychek2").val();
 			
 			if(midVal !=""){
 				alert("이미 금일 퇴근 기록이 있습니다.");
 				return false;
 			}
 			if(midVal==""){
 				alert("퇴근 시간이 정상 기록되었습니다.");
 				return true;
 			}
 		});
});
 		
	</script> 
	
	
	
	
	
	<!-- /* 
    $("#operator1").click(function(){
 	 	 	 alert("출근 시간이 정상 기록되었습니다.");
 	 	 	 return true;
		
    });
    
    $("#operator2").click(function(){
	 	 alert("퇴근 시간이 정상 기록되었습니다.");
	 	return true;
		
		}); */
    
    
     -->
    






<!-- <script>
	$(document).ready(function(){
		
	       $("#operator2").click(function(){
	    	 	 	 alert("퇴근 시간이 정상 기록되었습니다.");
	    	 	 	return true;
	    			
	       });
		 });
	
</script>  	 -->
</head>
<body>

<!-- <script>
        const operator1 = document.getElementById("operator1");
        const key = "lastClickTime";
        const oneDay = 24 * 60 * 60 * 1000;

        operator1.addEventListener("click", function() {
            const lastClickTime = localStorage.getItem(key);
            const now = new Date().getTime();

            if (lastClickTime && now - lastClickTime < oneDay) {
                alert("You can only submit once per day.");
            } else {
                localStorage.setItem(key, now);
                // Submit form or do other actions here
            }
        });
</script> -->
  

 
<table id="tab1">
<tr>
  <td colspan="16">
      <jsp:include page="../module/top.jsp" flush="false"/>
   </td>
</tr>
 
<tr>
  <td width="17%" valign="top">
      <jsp:include page="../module/left.jsp" flush="false"/>
   </td>

       <!--  내용 부분 : 시작 -->
 
	<!-- 출근 시간 폼 -->
		<td> 
	  <form name="workFrm" id="workinFrm" action="<%=request.getContextPath()%>/workin.aa">
				
		<input type="submit" name="operator" id="operator1" value="출근 체크">
		<br/>
		<br/>
					  <!--  출근시간찍어보자 게시판 글쓰면 작성시간 담기는 기능 이용하자 -->
      <table border="1">
      									<!-- 사원정보표 머리부분 -->
					<thead>
				<tr>
		    	 <th>사원번호</th>		<!-- 접속한 사용자 세션에서 받아와 보여준다 -->
				 <th>사원이름</th>		<!-- 접속한 사용자 세션에서 받아와 보여준다 -->
			  	 <th>출근시간</th>		<!-- 현재시간(근태 출근 페이지접속한)  -->
			  	 <th>특이사항사유</th> <!-- 선택항목을 몇개준다 ex)없음(디폴트),단순지각,질병관련,기타 -->
			  </tr>
				</thead>
   		 <tbody>
   		 <!-- 출석버튼을 눌러서  위에 정보 인서트 진행(특이사항 보고) -->
   		 
							  <!--   금일 출근 체크전 나오는 안내글 (제거예정)-->
		<%-- <c:if test="${Work_In.hasNoTodayCheck()}">
		   <tr>
		  	 <td colspan="5" style="text-align:center;">금일 출근 기록이없습니다.</td>
		   </tr>
		</c:if> --%>
					 
  							 <!-- 만약 출근기록이 있다면 -->
	
				
 		<tr>	    
		<!-- 사원번호 -->	        
	 	<td>${EMP_USER.empno}</td> 	 
 	
 		<!-- 사원이름 -->
		<td>${EMP_USER.ename}</td>
 		 
 	
 		<!--출근시간기록 -->
 <%-- 		<c:if test="${Work_In.hasNoTodayCheck()}">
		   <tr>
		  	 <td colspan="5" style="text-align:center;">금일 출근 기록이없습니다.</td>
		   </tr>
		</c:if>--%>
		
		
	<td>
	<input type="hidden" id="daychek" value="${workin.work_in_time}">
		
	<c:if test="${workout.work_out_time==null}">
 	  
 	    <fmt:formatDate type="date"  pattern="yyyy.MM.dd. HH:mm" value="${workin.work_in_time}" /> 
 	  
 	 </c:if>
 	 
 	 <c:if test="${workin.work_in_time==null}">
 
 	    <fmt:formatDate type="date" pattern="yyyy.MM.dd. HH:mm" value="${workout.work_in_time}" /> 
 	
 	 </c:if>
 	 
 	</td>	 
 	   <!-- 출근특이사항작성 -->
 	   
 
 	    
<%--  	${workin.work_in_notice} 이걸로 여기까지 데이터는 오는지봤다. 데이터는오나 폼의 표현식 문제였다 --%>
 	<td>
		
		  <select name="todaynoti" id="todaynoti"> 
		
			 <c:if test="${workin.work_in_notice eq '없음' || workout.work_in_notice eq '없음'}">
				   <option value="없음" selected="selected">없음</option>
			</c:if> 
					<option value="없음">없음</option>
				
			 <c:if test="${workin.work_in_notice eq '단순지각' || workout.work_in_notice eq '단순지각'}">
				   <option value="단순지각" selected="selected">단순지각</option>
			</c:if> 
					<option value="단순지각" >단순지각</option>
			<c:if test="${workin.work_in_notice eq '질병관련' || workout.work_in_notice eq '질병관련'}">
			
				   <option value="질병관련" selected="selected">질병관련</option>
			</c:if> 	
					<option value="질병관련">질병관련</option>
				
				<c:if test="${workin.work_in_notice eq '기타' || workout.work_in_notice eq '기타'}">
				   <option value="기타" selected="selected">기타</option>
				</c:if> 
					<option value="기타">기타</option>
				
		  </select>
	
 	    </td>
 	   </tr>
    </tbody>
 </table>
 	 <br/>
 	 
	  <!--퇴근 체크 폼 -->
		<input type="submit" name="operator" id="operator2" value="퇴근 체크"  >
      <br/>
      <br/>
      <table id="tab2" border="1">
      						<!-- 사원정보표 머리부분 -->
			<thead>
				<tr>
		    	 <th>사원번호</th>		<!-- 접속한 사용자 세션에서 받아와 보여준다 -->
				  <th>사원이름</th>		<!-- 접속한 사용자 세션에서 받아와 보여준다 -->
			  	 <th>퇴근시간</th>		<!-- 현재시간(근태 출근 페이지접속한)  -->
			  	 <th>특이사항사유</th> <!-- 선택항목을 몇개준다 ex)없음(디폴트),단순지각,질병관련,기타 -->
			  
			  </tr>
			</thead>
   		 <tbody>
   		 <!-- 출석버튼을 눌러서  위에 정보 인서트 진행(특이사항 보고) -->
   		 
							  <!--   금일 퇴근 체크전 나오는 안내글 (제거예정)-->
		<%-- <c:if test="${Work_In.hasNoTodayCheck()}">
		   <tr>
		  	 <td colspan="5" style="text-align:center;">금일 퇴근 기록이없습니다.</td>
		   </tr>
		</c:if> --%>
					 
  							 <!-- 만약 퇴근기록이 있다면 -->
				
 		<tr>	    
		<!-- 사원번호 -->	        
	 	<td>${EMP_USER.empno}</td> 	 
 	
 		<!-- 사원이름 -->
		<td>${EMP_USER.ename}</td>
 		 
 	
 		<!--출근시간기록 -->
 <%-- 		<c:if test="${Work_In.hasNoTodayCheck()}">
		   <tr>
		  	 <td colspan="5" style="text-align:center;">금일 출근 기록이없습니다.</td>
		   </tr>
		</c:if> --%>
		
		
 	  <td>
 	  <input type="hidden" id="daychek2" value="${workout.work_out_time}">
 	    <fmt:formatDate type="date" pattern="yyyy.MM.dd. HH:mm" value="${workout.work_out_time}" /> 
 	  </td> 
 	  
 	  
 	   <!-- 퇴근특이사항작성 -->
 	    <td>
 
 
 	    
  <select name="todayoutnoti" id="todayoutnoti"> 

	 <c:if test="${workout.work_out_notice eq '없음'}">
		   <option value="없음" selected="selected">없음</option>
	</c:if> 
			<option value="없음">없음</option>
		
	 <c:if test="${workout.work_out_notice eq '연장근무'}">
		   <option value="연장근무" selected="selected">연장근무</option>
	</c:if> 
			<option value="연장근무" >연장근무</option>
		
		<c:if test="${workout.work_out_notice eq '기타'}">
		   <option value="기타" selected="selected">기타</option>
		</c:if> 
			<option value="기타">기타</option>
		
  </select>

 	    </td>
 	   </tr>
       <!-- 내용 부분: 끝 -->

	</tbody>
 </table>
	</form>

</tr>
<tr>
  <td colspan="16">
    <jsp:include page="../module/bottom.jsp" flush="false"/>
  </td>
</tr>
</table>
 
 
</body>
</html>

