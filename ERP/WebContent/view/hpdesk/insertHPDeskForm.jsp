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
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/buttonSubmit.css?after">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<style>
 .z1{
		text-align:right;
		}
 .title1{
						color: #73685d;
					  background: #fff;
					  }
 .error{
					 font-size:1.em; 
					 color:red;
					 }
 .t1{display:none;}
 .texta{
					  font-size:1.1em;
					  border: none;
					  resize: none;
					  width:500px;
					  line-height: 1.3;
					  }
 body {
					  padding:1.5em;
					  background: #f5f5f5
					}
 table {
					  border: 1px #a39485 solid;
					  font-size:17px;
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
	  $("#btnList").on("click",function(){
			location.href="<%=request.getContextPath() %>/hpdesk/listhpdesk.aa";
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
   
   
   
   
   
   
       <!--  내용 부분 : 시작 -->
<%-- ${EMP_USER }
${newHPDeskNo } --%>
			 <h2 class="title1">문의사항 작성</h2>
			 <form name="insertFrm" id="insertFrm" method="post" action="<%=request.getContextPath() %>/hpdesk/inserthpdesk.aa">
			 <div class="z1"><input class="w-btn w-btn-gray"type="button" name="btnList"id="btnList" value="목록조회"/></div>
			 <br/>
			 <input type="hidden" value="${EMP_USER.empno }"/>
			 <table border="1">
       <tr>
	       	<th class="thead">작성자</th>
	       		<td class="tbody"><input class="texta" type="text" value="${EMP_USER.ename }"/></td>
	    	</tr>
	    	<tr>   	
	       	<th class="thead">게시글제목</th>
	       		 <td class="tbody">
	       		 		<input class="texta"type="text" name="hdtitle" id="hdtitle" autofocus="autofocus" placeholder="제목을 입력해주세요." value="${param.hdtitle }"/>
	       		 		<span class="error" ><c:if test="${errors.hdtitle }">제목을 입력하세요</c:if></span>
	       		 </td>
	    	</tr>
	    	<tr>  
	       	<th class="thead">게시글내용</th>
	       		 <td class="tbody">
	       		 		<textarea class="texta"name="hdcontent" id="hdcontent" rows="20" cols="50" placeholder="내용을 입력해주세요.">${param.hdcontent }</textarea>
	       		 	  <span class="error" ><c:if test="${errors.hdcontent }">내용을 입력하세요</c:if></span>
	       		 </td>	       		 		
	    	</tr>
       	<tr>
       	<th class="thead">게시글댓글</th>
	       		 <td class="tbody">관리자만 등록 가능합니다.</td>
	    	</tr>
     		</table>
     		<br/>
       	<div class="z1"><input class="w-btn w-btn-gray"type="submit"  value="등록완료"/></div>
 				</form>
       <br><br><br>    
       <!-- 내용 부분: 끝 -->
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