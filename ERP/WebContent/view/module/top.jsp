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
<title>ERP 인사관리</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
 <style>
    /* Simple Reset CSS */
    * {
      margin: 0;
      padding: 0;
      box-sizing: border-box;
    }
    body {
      font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
      color: #58666e;
      background-color: #f0f3f4;
    }
    li {
      list-style: none;
    }
    a {
      text-decoration: none;
    }
    
    header {
  width: 100%;
  height: 60px;
  z-index: 2000;
  background-color: #fff;
  box-shadow: 0 2px 2px rgba(0, 0, 0, 0.05), 0 1px 0 rgba(0, 0, 0, 0.05);
}

nav {
  float: right;
}

.logo {
  display: inline-block;
  height: 36px;
  margin: 12px 0 12px 25px;
}
.logo > img { height: 36px; }

.nav-items > li {
  display: inline-block;
}
.nav-items > li > a {
  line-height: 60px;
  padding: 0 30px;
  color: rgba(0, 0, 0, 0.4);
}
.nav-items > li > a:hover {
  color: rgba(0, 0, 0, 0.8);
}


  </style>

</head>
<body>

 <div id="wrap">
    <header>
      <a class="logo" href="#home">
        <img src="https://poiemaweb.com/img/logo.png" height="36px">
      </a>
      <nav>
        <ul class="nav-items">
          <li><a href="<%=request.getContextPath()%>/notice/list.aa">공지사항</a></li>
          <li><a href="<%=request.getContextPath()%>/mypageinfo.aa">마이페이지</a></li>
          <li><a href="<%=request.getContextPath()%>/logout.aa">로그아웃</a></li>
        </ul>
      </nav>
    </header>
  </div>



</body>
</html>