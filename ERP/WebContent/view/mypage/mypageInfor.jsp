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
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/buttonSubmit.css?after">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/tableLeft.css?after">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<style>
      input[type=text]{
  	border: none; 
  	background: transparent;
  }
  
</style>
<script>
  $(document).ready(function(){
	  
	  $("#btn1").click(function(){
			 //newWin변수에는 새로운 window객체가 저장
			  location.href="<%=request.getContextPath()%>/mypageupdatepwd.aa?empno=${empdb.empno}";
			 //변수명.close(); //창닫기
		});
	  
	  $("#btn2").click(function(){
			 //newWin변수에는 새로운 window객체가 저장
			 location.href="<%=request.getContextPath()%>/mypageupdatedname.aa?empno=${empdb.empno}";
			 //변수명.close(); //창닫기
		});
	  
	  $("#btn3").click(function(){
			 //newWin변수에는 새로운 window객체가 저장
			 location.href="<%=request.getContextPath()%>/mypageupdatelevel.aa?empno=${empdb.empno}";
			 //변수명.close(); //창닫기
		});
	  
	  $("#btn4").click(function(){
			 //newWin변수에는 새로운 window객체가 저장
			 location.href="<%=request.getContextPath()%>/mypageupdateemail.aa?empno=${empdb.empno}";
			 //변수명.close(); //창닫기
		});
	  
	  $("#btn5").click(function(){
			 //newWin변수에는 새로운 window객체가 저장
			 location.href="<%=request.getContextPath()%>/mypageupdatetel.aa?empno=${empdb.empno}";
			 //변수명.close(); //창닫기
		});
	  
	  $("#btn6").click(function(){
			 //newWin변수에는 새로운 window객체가 저장
			 location.href="<%=request.getContextPath()%>/mypageupdatesal.aa?empno=${empdb.empno}";
			 //변수명.close(); //창닫기
		});
	  
	  $("#btn7").click(function(){
			 //newWin변수에는 새로운 window객체가 저장
			 location.href="<%=request.getContextPath()%>/mypageupdategrade.aa?empno=${empdb.empno}";
			 //변수명.close(); //창닫기
		});
  });
  
  $(document).ready(function(){  
	  $("#b_deptno").change(function(){
			let val12 = $("select#b_deptno option:selected").val();
		$("#search").val(val12);
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
  
  
      <h2>마이페이지</h2>
      <c:if test="${not empty EMP_USER && EMP_USER.grade==999}">  
      <form action="<%=request.getContextPath()%>/mypageinfo.aa">
      <table>
      <thead>
	      <tr>
		      <td style="text-align:right;">
		      	<input type="text" name="search" id="search"readonly="readonly" placeholder="수정 할 사원번호" > 
		       <select id="b_deptno">
		       				<option value="">선택</option>
						<c:forEach var="item" items="${searcheno}">
							<option value="${item.empno}">${item.empno},${item.ename}</option>
						</c:forEach>	
					 </select>
		      </td>
		      <td class="tbody" style="text-align:left;">
		      	<input type="submit" name="submit" id="submit" value="사원번호찾기" class="w-btn w-btn-gray">
		      </td>
	      </tr>
      </thead>
      </table>
      </form>
      </c:if>
      
				<table border="1">
				  <tr>
				    <th width="33%" class="thead">사번</th>
				    <td width="33%" class="tbody">${empdb.empno}</td>
				    <td width="34%" ></td>
				  </tr>
				  <tr>
				    <th width="33%" class="thead">이름</th>
				    <td width="33%" class="tbody">${empdb.ename}</td>
				    <td width="34%"></td>
				  </tr>
				  <tr>
				    <th width="33%" class="thead">비밀번호</th>
				    <td width="33%" class="tbody">${empdb.emppwd}</td>
				    <td width="34%"><input type="button" name="btn1" id="btn1" value="비밀번호 변경" class="w-btn w-btn-gray"/></td>
				  </tr>
				  <tr>
				    <th width="33%" class="thead">부서번호</th>
				    <td width="33%" class="tbody">${empdb.deptno},${deptdb.deptname}</td>
				    <c:if test="${not empty EMP_USER && EMP_USER.grade==999}">  
				    <td width="34%"><input type="button" name="btn2" id="btn2" value="부서번호 변경" class="w-btn w-btn-gray"/></td>
				    </c:if>
				  </tr>
				  
				  <tr>
				    <th width="33%" class="thead">직급</th>
				    <td width="33%" class="tbody">${empdb.level}</td>
				    <c:if test="${not empty EMP_USER && EMP_USER.grade==999}">  
				    <td width="34%"><input type="button" name="btn3" id="btn3" value="직급 변경" class="w-btn w-btn-gray"/></td>
				    </c:if>
				  </tr>
				  
				  <tr>
				    <th width="33%" class="thead">이메일</th>
				    <td width="33%" class="tbody">${empdb.email}</td>
				    <td width="34%"><input type="button" name="btn4" id="btn4" value="이메일 변경" class="w-btn w-btn-gray"/></td>
				  </tr>
				  <tr>
				    <th width="33%" class="thead">전화번호</th>
				    <td width="33%" class="tbody">${empdb.tel}</td>
				    <td width="34%"><input type="button" name="btn5" id="btn5" value="전화번호 변경" class="w-btn w-btn-gray"/></td>
				  </tr>
				  <tr>
				    <th width="33%" class="thead">연봉</th>
				    <td width="33%" class="tbody">${empdb.sal}</td>
				    <c:if test="${not empty EMP_USER && EMP_USER.grade==999}">  
				    <td width="34%"><input type="button" name="btn6" id="btn6" value="연봉 변경" class="w-btn w-btn-gray"/></td>
				    </c:if>
				  </tr>
				  <tr>
				    <th width="33%" class="thead">계정권한 변경</th>
				    <c:if test="${not empty EMP_USER && EMP_USER.grade==999}"> 
				    <td colspan="2" align="center"><input type="button" name="btn7" id="btn7" value="계정권한 변경" class="w-btn w-btn-gray"/></td>
				    </c:if>
				  </tr>
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