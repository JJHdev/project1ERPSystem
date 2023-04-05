<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<!DOCTYPE html>
<html lang="ko">
<head>
 <meta charset="UTF-8">
 <meta name="description" content="member board Web Application">
 <meta name="keywords" content="member, board, article, mvc">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 <script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<style>
	body {
 		 padding:1.5em;
 		 background: #f5f5f5
	}

	table {
  		 border: 1px #a39485 solid;
  		 font-size: .9em;
 		 box-shadow: 0 2px 5px rgba(0,0,0,.25);
 		 width: 100%;
  		 border-collapse: collapse;
  	  	 border-radius: 7px;
  		 overflow: hidden;	
  	  	 text-align:center;
	}
	th {
  		 padding: 1.5em .5em;
  		 vertical-align: middle;
		 background: #736658;
	 	 color: #fff;
	 	 font-weight: bold;
	}
  
 	td {
 		 border-bottom: 1px solid rgba(0,0,0,.1);
 	 	 background: #fff;
	}

	a {
 	 	 color: #73685d;
	}
  	input{
  		height:80px;
  	}
   

</style>

</head>
<body>
 <hr/>
 <a href="<%=request.getContextPath()%>/index.jsp">HOME</a>
 <hr/>
 <h3>댓글작성</h3>
<form name="cwriteFrm" id="cwriteFrm" method="post" action="/comment/write.aa">
	<table>
		<tr>
		<td>
		<input type="text" id="c_content" name="c_content">
		<input type="hidden" id="cno" name="cno" value="${comReq.comment_no}">
		<input type="hidden" id="no" name="no" value="${no}">
		<input type="hidden" id="rowSize" name="rowSize" value="${rowSize}">
		<input type="hidden" id="pageNo" name="pageNo" value="${pageNo}">
		</td>
		<td>		 
		 <input type="submit" value="작성하기">
		 </td>
		 </tr>
	</table>
</form>

</body>
</html>