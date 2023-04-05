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
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/tableLeft.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/buttonSubmit.css">
 <script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
 <style>
 
.w-btn {
    position: relative;
    border: none;
 
    padding: 3px 7px;
    border-radius: 7px;
    font-family: "paybooc-Light", sans-serif;
    box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
    text-decoration: none;
    font-weight: 600;
    transition: 0.25s;
}
  .w-btn-gray {
    background-color: #82766b;
    color: #e3dede;
}
  .w-btn:hover {
    letter-spacing: 2px;
    transform: scale(1.2);
    cursor: pointer;
}
  .w-btn:active {
    transform: scale(1.5);
}
 </style>
 <script>
 $(document).ready(function () {
	 
	 $("#regDate").text(getToday());
		function getToday() {
			var date = new Date();
			var year = date.getFullYear();
			 var month = ("0" + (1 + date.getMonth())).slice(-2);
		    var day = ("0" + date.getDate()).slice(-2);
				var regdate= year + "-" + month + "-" + day;
		 $('input[name=today]').attr('value',regdate);
				return regdate;
		}
	 
	    	 $("#survayRsi-btn").on("click",function(){
	 			  	valid();//필수입력체크
	 			  	
	 		  });
 
//필수입력체크
 	function valid(){
	    		  titleVal = $("#sTitle").val();
	    		  contentVal = $("#sContent").val();
	    		  endDateVal = $("#surv_resdate").val();
	    		  if(titleVal==""){
	    			  alert("제목을 입력하세요");
	    			  $("#sTitle").focus();
	    			  return false;
	    		  }
	    		  if(contentVal==""){
	    			  alert("질문 내용을 입력하세요");
	    			  $("#sContent").focus();
	    			  return false;
	    		  }
	    		  $("#writeFrm").submit();  
	    	 }
	
	    
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
<!--  내용 부분 : 시작 -->
<%-- ReadArticleHandler 컨트롤러에 의해 아래와 같이 Model받았다
request.setAttribute("articleData", articleData);//article테이블과 article_content테이블 관련 데이터
request.setAttribute("pageNo", pageNo);//요청페이지
request.setAttribute("rowSize", rowSize);//1페이지당게시글수 
*DB내용:${articleData}<br/><br/>
--%>


 <h3>설문 등록하기</h3>
 <form name="writeFrm" id="writeFrm" 
       method="post" action="<%=request.getContextPath()%>/surv/write.aa">
 <table class="t1" border="1">
 	<tr>
 	 <th class="thead">작성자명</th>
 	 <td class="tbody">
 	 ${EMP_USER.ename}
 	 <input type="hidden" name="sName" id="sName" value="${EMP_USER.ename}"/></td>
 	</tr>
 	<tr>
 	 <th class="thead">제목</th>
 	 <td class="tbody">
 	  <input type="text" name="sTitle" id="sTitle"/>
 	 </td>
 	</tr>
 	<tr>
 	<th class="thead">등록일자</th>
 	<td class="tbody">
 	<span id="regDate"></span>
 		<input type="hidden" name="today" id="today"/>
 	</td>
 	</tr>
 	<tr>
 	 <th class="thead">질문 내용
 	 </th>
 	 <td>
 	 	<input type="text" name="sContent" id="sContent"/>
 	 </td>
 	</tr>
 	<tr>          
 	 <th class="thead">종료일</th>
 	 <td class="tbody">
 	 <input type="date" name="surv_resdate" id="surv_resdate" min="2023-02-05" max="2023-05-27" required />
 	 </td>
 	</tr>

 	<tr>
 	 <td colspan="2" style="text-align:center;">
 	 <input type ="button" class="w-btn w-btn-gray" id="survayRsi-btn" name="survayRsi-btn" value="설문등록하기"/>
 	 	</td>
 	 	</tr>
 	 	<%-- 수정과 삭제기능은 관리자등급의 경우에만 노출하도록 한다 --%>
 </table>
 	 <%--P662 29라인
 	 <c:set var="변수명" value="변수값"/>--%>
 	<br/>
 	 <div>
 	 <c:set var="pgNo" 
 	    value="${(empty param.pageNo)?'1':param.pageNo}"/>                      
 	 	<a class="w-btn w-btn-gray" href="/surv/list.aa?pageNo=1&rowSize=3">목록보기</a>
 	 	
 </div>
 </form>
 <!-- 내용 부분: 끝 -->
       </td>
    </tr>
<tr>
  <td colspan="16">
    <jsp:include page="../module/bottom.jsp" flush="false"/>
  </td>
</tr>
  </tbody>
</table>
</body>
</html>






