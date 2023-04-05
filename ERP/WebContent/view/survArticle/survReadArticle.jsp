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
  a { margin: 10px;
}
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
 $(document).ready(function() {
	    
	 $('button').click(function() {
        if ($("input[type=radio][name=survey1]:checked").is(':checked')) {
        	alert('응답해주셔서 감사합니다.');
        	
        	var obj_value = $("input:radio[name='survey1']:checked").val();
        	
        	location.href ="/surv/result.aa?no="+${articleData.article.surv_no}+"&survey1="+obj_value;
        	
        }
        else {
            alert('다음 설문답을 선택해주세요.');
        }
	 })
});
  
 </script>
</head>
<body>
	
<%-- ReadArticleHandler 컨트롤러에 의해 아래와 같이 Model받았다
request.setAttribute("articleData", articleData);//article테이블과 article_content테이블 관련 데이터
request.setAttribute("pageNo", pageNo);//요청페이지
request.setAttribute("rowSize", rowSize);//1페이지당게시글수 
*DB내용:${articleData}<br/><br/>
--%>
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

<%-- *요청페이지:${pageNo}<br/>
*1페이지당게시글수:${rowSize}<br/><br/><br/> --%>
<form name="rowSizeFrm" id="rowSizeFrm" 
       action="<%=request.getContextPath()%>/surv/result.aa" method="get">
      
 <hr/>
 <h3>설문 상세 보기</h3>
 <table border="1">
 	<tr>
 	 <th class="thead">번호</th>
 	 <td class="tbody">${articleData.article.surv_no}</td>
 	</tr>
 	<tr>
 	 <th class="thead">작성자명</th>
 	 <td class="tbody">${articleData.article.surv_writer}</td>
 	</tr>
 	<tr>
 	 <th class="thead">제목</th>
 	 <td class="tbody">${articleData.article.surv_tit}</td>
 	</tr>
 	<tr>
 	 <th class="thead">작성일</th>
 	 <td class="tbody">
 	 <fmt:formatDate type="both" pattern="yyyy/MM/dd"  value="${articleData.article.surv_regdate}" />
 	 <input type="hidden" name="surv_regdate" value="${articleData.article.surv_regdate}"disabled/>
 	 </td>
 	</tr>
 	<tr>          
 	<tr>
 	 <th class="thead">종료일</th>
 	 <td class="tbody">
 	 <fmt:formatDate type="both" pattern="yyyy/MM/dd"  value="${articleData.article.surv_resdate}" />
 	 <input type="hidden" name="surv_resdate" pattern="yyyy/MM/dd" value="${articleData.survContent.surv_resdate}" disabled>
 	 </td>
 	</tr>

 	 	<%-- 수정과 삭제기능은 관리자등급의 경우에만 노출하도록 한다 --%>

 </table>
 	<div class="survey">
 	<table>
 	<tr>
 	 	<th class="thead"><span class="survaysName">${articleData.survContent.surv_content}</span></th>
		<td class="tbody">
	 		<input type="radio"  name="survey1" id="survey1" value="만족" />만족
			<input type="radio"  name="survey1" id="survey1" value="다소만족" />다소만족
			<input type="radio"  name="survey1" id="survey1" value="보통" />보통
			<input type="radio"  name="survey1" id="survey1" value="다소미흡" />다소미흡
			<input type="radio"  name="survey1" id="survey1" value="매우미흡" />매우미흡
 			</td><td class="tbody"><button type ="button" class="w-btn w-btn-gray" id="survay-btn" name="survay-btn">응답</button>
 			</td>
 			</tr>
 		</table>
 		</div>
 		<br/>
  	 <div>
 		<a class="w-btn w-btn-gray" href="/surv/list.aa?pageNo=${pageNo}&rowSize=${rowSize}">목록보기</a>
 	 	<c:if test="${not empty EMP_USER && EMP_USER.grade==999 or AUTHUSER.memberid==articleData.article.surv_writer}">
 	 	<a class="w-btn w-btn-gray" href="/surv/modify.aa?no=${articleData.article.surv_no}&pageNo=${pageNo}&rowSize=${rowSize}">글수정</a>
	 	 	<a class = "w-btn w-btn-gray" href="/surv/delete.aa?no=${articleData.article.surv_no}">글삭제</a>
 		</c:if>
 		
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






