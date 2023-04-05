<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>

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
  
 @media all and (max-width: 365px) {
    
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
		$("#btn1").on("click",function(){
			 self.close();
		 });
	 });
 </script>
</head>
<body>

 <hr/>
 <h3>받은 쪽지</h3>
 <button type="button" name="btn1" id="btn1">창닫기</a>
 <table border="1">
 	<tr>
 	 <th class="thead">메세지 번호</th>
 	 <td class="tbody">${messageData.messageDTO.messageno}</td>
 	</tr>
 	
 	<tr>
 	 <th class="thead">보낸 사원번호</th>
 	 <td class="tbody">${messageData.messageDTO.sendempno}</td>
 	</tr>
 	
 	<tr>
 	 <th class="thead">보낸일 </th>
 	<td class="tbody">
 	 <fmt:formatDate type="date" dateStyle="full" value="${messageData.messageDTO.send_time}" />
 	 </td>
 	</tr>
 	
 	<tr>
 	 <th class="thead">내용</th>
	 <td class="tbody"><u:pre value="${messageData.messageContent.content}"/></td>
 	</tr>
 </table>
</body>
</html>






