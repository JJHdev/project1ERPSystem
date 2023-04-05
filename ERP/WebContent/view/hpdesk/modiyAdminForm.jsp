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
			line-height: 1.8;
			}
	.title1{color: #73685d;  background: #fff;}
	.t1{display:none;}
	.texta{
					  font-size:1.1em;
					  border: none;
					  resize: none;
					  width:500px;
					  }
	body {
					  padding:1.5em;
					  background: #f5f5f5
					}
 table {
					  border: 1px #a39485 solid;
					  font-size:1em;
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
	  
	  $("#btnDel").on("click",function(){
		  let c=confirm("삭제하시겠습니까?");
		  if(c){
			location.href="<%=request.getContextPath() %>/hpdesk/deletehpdesk.aa?hdno=${modifyHPDeskReq.hdno}";
		  }
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
<%-- sessionScope:${sessionScope.EMP_USER}
${modifyHPDeskReq } --%>
			 <h2 class="title1">문의사항 수정(관리자)</h2>
			 <form name="insertFrm" id="insertFrm" method="post" action="<%=request.getContextPath() %>/hpdesk/modifyadmin.aa?hdno=${modifyHPDeskReq.hdno}">
			<div class="z1"><input class="w-btn w-btn-gray"type="button" name="btnList"id="btnList"value="목록조회"/></div>
			<br/>
			 <table class="wrt"border="1">
			  <tr>
	       	<th class="thead">작성자</th>
	       		<td class="tbody">${modifyHPDeskReq.ename }</td>
	    	</tr>
	    	<tr>
	       	<th class="thead">게시글제목</th>
	       		<td class="tbody">	${modifyHPDeskReq.hdtitle }</td>
	    	</tr>
	    	<tr>
	       	<th class="thead">게시글내용</th>
	       		<td class="tbody">${modifyHPDeskReq.hdcontent }</td>
	    	</tr>
       	<th class="thead">게시글댓글</th>
	       		 <td class="tbody">
	       		 		<pre><textarea  class="texta"name="hdcomment" id="hdcomment" rows="10" cols="100"autofocus="autofocus">${modifyHPDeskReq.hdcomment }</textarea></pre>	
	       		 </td>	       		 
	    	</tr> 	
				<tr>
 	    <th class="thead">글확인상태</th>
						<td class="tbody" colspan="2" style="text-align:right;">	
	 									<select name="hdcheck"  id="hdcheck"style="height:25px;width:110px;font-size:14px; text-align:center;">
												<option value="1" >미답변</option>
												<option value="2" >처리중</option>
												<option value="3" >답변완료</option>
									</select>
		  			</td>
		  	</tr>
     		</table>
     		<br/>
   			<div class="z1"><input class="w-btn w-btn-gray"type="submit"  value="수정완료"/></div>
   			<div class="z1"><input class="w-btn w-btn-gray"type="button"name="btnDel"id="btnDel" value="문의글삭제"/></div>
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