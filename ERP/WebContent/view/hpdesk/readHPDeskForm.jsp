<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
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
.title1{
	color: #73685d;  
	background: #fff;
	}
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
			location.href="<%=request.getContextPath() %>/hpdesk/listhpdesk.aa?pageNo=${pageNo}&rowSize=${rowSize}";
	  });
	  //유저용
	  $("#btnModify").on("click",function(){
			location.href="<%=request.getContextPath() %>/hpdesk/modifyhpdesk.aa?empno=${hpdeskData.hpdesk.hpdeskWriter.empno}&hdno=${hpdeskData.hpdesk.hdno}&pageNo=${pageNo }&rowSize=${rowSize }&hdcheck=${hpdeskData.hpdesk.hdcheck}";
	  });

	  //관리자용
	  $("#btnAModify").on("click",function(){
			location.href="<%=request.getContextPath() %>/hpdesk/modifyadmin.aa?empno=${hpdeskData.hpdesk.hpdeskWriter.empno}&hdno=${hpdeskData.hpdesk.hdno}&pageNo=${pageNo }&rowSize=${rowSize }&hdcheck=${hpdeskData.hpdesk.hdcheck}";
	  });
	  
	  $("#btnDel").on("click",function(){
		  let c=confirm("삭제하시겠습니까?");
		  if(c){
			location.href="<%=request.getContextPath() %>/hpdesk/deletehpdesk.aa?hdno=${hpdeskData.hpdesk.hdno}";
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
<%-- sessionScope:${sessionScope.EMP_USER}<br/>
 *DB내용:${hpdeskData}<br/>
 *요청페이지:${pageNo}<br/>
 *1페이지당게시글수:${rowSize}<br/> --%>
			 <h2 class="title1">문의사항 내용조회</h2>
			 <form name="readFrm" id="readFrm" method="get" action="<%=request.getContextPath() %>/hpdesk/listhpdesk.aa">			 
	     <div class="z1"><input class="w-btn w-btn-gray"type="button" name="btnList"id="btnList"value="목록조회"/></div>
	     <br/>
	     <input type="hidden" name="empno" id="empno"value="${hpdeskData.hpdesk.hpdeskWriter.empno}"/>
	     <c:set var="pNo" value="${(empty param.pageNo)? '1' : param.pageNo }"/>	 
			 <table class="wrt" border="1">
       <tr>
	       	<th class="thead">작성자</th>
	       		<td class="tbody" >${hpdeskData.hpdesk.hpdeskWriter.ename }</td>
	    	</tr>
	    	<tr>   	
	       	<th class="thead">게시글제목</th>
	       		 <td class="tbody" >${hpdeskData.hpdesk.hdtitle}</td>
	    	</tr> 
	    	<tr>
	       		<th class="thead">게시글내용</th>
	       		 <td class="tbody"  rows="20" cols="70" ><u:pre value="${hpdeskData.hpdesk.hdcontent}"/></td>	       		 		
		    </tr>
       	<tr>  
	       		<th class="thead">게시글댓글</th>
	       		 <td class="tbody"  rows="10" cols="70"><u:pre value="${hpdeskData.hdcomm.hdcomment}"/></td>
	    	</tr>
     		</table>
		   	<br/>
			<%-- 관리자모드 --%>
			<c:if test="${!empty EMP_USER && (EMP_USER.grade==999)}">
		   	<div class="z1"><input class="w-btn w-btn-gray"type="button" name="btnAModify"id="btnAModify"value="문의댓글수정"/></div>
			<div class="z1"><input class="w-btn w-btn-gray"type="button"name="btnDel"id="btnDel" value="문의글삭제"/></div>
			</c:if>
		    <%-- 일반사원용 --%>
		   	<c:if test="${!empty EMP_USER && (EMP_USER.empno==hpdeskData.hpdesk.hpdeskWriter.empno)}">
		    <div class="z1"><input class="w-btn w-btn-gray"type="button"name="btnModify" id="btnModify"value="문의글수정"/></div>
			</c:if>	
			<c:if test="${!empty EMP_USER && (EMP_USER.empno==hpdeskData.hpdesk.hpdeskWriter.empno) && (EMP_USER.grade==1)}">
		    <div class="z1"><input class="w-btn w-btn-gray"type="button"name="btnDel"id="btnDel" value="문의글삭제"/></div>
     		</c:if>
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