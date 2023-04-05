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
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<style>
 .sub_category_list {
	display: none;
}
.ac-category.expanded .ac-toggle i {
	transform: rotate(0.5turn);
}
.ac-toggle i {
	transition: .1s
}
.ac-category .ac-toggle i {
  float: right;
  padding: 4px;
  /* pointer-events: none; */
}
.c_cnt {
  float: right;
  /* pointer-events: none; */
}
.link_tit .c_cnt {
	display: none;
}
.ac-toggle .c_cnt {
	display: none;
}

.accordion {
	float: left;
	width: 100%;
	max-width: 250px;
	margin: 0px 0px 50px 0px;
}
.accordion a{
	text-decoration: none;
}
.accordion-tab-1 {
	width: 100%;
	background-color: #73685d;
	margin-bottom: 10px;
	border-radius: 5px;
	position: relative;
	overflow: hidden;
}
.accordion-tab-1 label {
	padding: 10px 5px 10px 5px;
	text-align: center;
	color: white;
	font-size: 15px;
	font-weight: bold;
	text-decoration: none;
	display: block;
	transition-duration: .5s;
	text-transform: uppercase; /* 대문자 */
}
.accordion-tab-1 label:hover {
	cursor: pointer;
}
.accordion-tab-2 {
	width: 100%;
	position: relative;
	overflow: hidden;
}

.accordion-tab-2 input {
	opacity: 0;
	z-index: -1;
	position: absolute;
}
.accordion-tab-2 label {
	padding: 10px 5px 10px 5px;
	border-radius: 5px;
	border-bottom: 1px solid black;
	background-color: #FBFBFB;
	color: black;
	font-size: 15px;
	font-weight: bold;
	text-decoration: none;
	display: block;
	transition-duration: .5s;
	text-transform: uppercase; /* 대문자 */
}
.accordion-tab-2 label:hover {
	cursor: pointer;
	padding-left: 10px;
}
.accordion-tab-2 input:checked ~ label {
	color: black;
	background-color: #FBFBFB;
	padding-left: 10px;
}
.accordion-tab-2 label::after {
	right: 5px;
	color: black;
	font-size: 15px;
	-webkit-transition: all .35s;
	-o-transition: all .35s;
	transition: all .35s;
	position: absolute;
	text-align: center;
}
.accordion-tab-2 input[type=radio] + label::after {
	content: "⊕";
}
.accordion-tab-2 input[type=radio]:checked + label::after {
	content: "⊖";
	color: black;
	transform: rotateX(360deg);
}
.accordion-tab-2 input:checked ~ .accordion-tab-3 {
	max-height: 1000px;
	margin-bottom: 15px;
}
.accordion-tab-3 {
	max-height: 0;
	overflow: hidden;
	-webkit-transition: max-height .35s;
	-o-transition: max-height .35s;
	transition: max-height .35s;
}
.accordion-tab-3 a {
	margin: 10px 0px 10px 0px;
	padding-left: 10px;
	font-size: 14px;
	color: #999;
	transition-duration: .5s;
	display: block;
	text-decoration: none;
	text-transform: capitalize; /* 첫글자 대문자로 */
}
.accordion-tab-3 a:hover {
	cursor: pointer;
	color: black;
	font-weight: bold;
	padding-left: 20px;
}
 </style>
	<script>
	$(function() {
		$(".category_list").children("li").has("ul").addClass("ac-category"),
			$(".ac-category .link_item").append("<i class=\"fas fa-angle-down\"></i>").addClass("ac-toggle"),
			$(".ac-category").each(function() {
			var c = $(this).children(".link_item"), d = c.attr("href");
			c.removeAttr("href"),
				$(this).children("ul").prepend(),
				c.children("span").appendTo(c.parent(".ac-category").find(".view-all"))
		});

		$(".ac-toggle").click(function() {
			var c = $(this).parent(".ac-category");
			c.children("ul").toggle(250), c.toggleClass("expanded")
		})
	});
	</script>
</head>
<body>


<s_sidebar_element>
<!-- 신규 카테고리 -->
<div class="accordion">

	<div class="accordion-tab-1">
		<label>전체보기</label>
		</div>
 ${EMP_USER.ename}님 접속중 
	<div class="accordion-tab-2">
		<input id="category-2" type="radio" name="tab">
		<label for="category-2">공지사항</label>
		<div class="accordion-tab-3">
			<a href="<%=request.getContextPath()%>/notice/list.aa">공지사항 목록보기</a>
		</div>
		</div>
<c:if test="${not empty EMP_USER && EMP_USER.grade==1}">  
	<div class="accordion-tab-2">
		<input id="category-1" type="radio" name="tab">
		<label for="category-1">마이페이지</label>
		<div class="accordion-tab-3">
	<a href ="<%=request.getContextPath()%>/mypageinfo.aa" >나의정보보기</a>
  <a href ="<%=request.getContextPath()%>/view/message/mypagemessage.jsp" >쪽지보내기</a>
  <a href ="<%=request.getContextPath()%>/MessageShowController.aa" >받은쪽지함</a>
			</div>
		</div>
</c:if>		
	
<c:if test="${not empty EMP_USER && EMP_USER.grade==999}">  
		<div class="accordion-tab-2">
		<input id="category-1" type="radio" name="tab">
		<label for="category-1">마이페이지</label>
		<div class="accordion-tab-3">
	<a href ="<%=request.getContextPath()%>/mypageinfo.aa" >나의정보보기</a>
  <a href ="<%=request.getContextPath()%>/register.aa" >사원등록하기</a>
  <a href ="<%=request.getContextPath()%>/mypageDeptDuplicate.aa" >부서 등록하기</a>
  <a href ="<%=request.getContextPath()%>/view/dept/deptDeleteForm.jsp" >부서 삭제하기</a>
  <a href ="<%=request.getContextPath()%>/mypagedeletelist.aa" >사원탈퇴하기</a>
  <a href ="<%=request.getContextPath()%>/view/message/mypagemessage.jsp" >쪽지보내기</a>
  <a href ="<%=request.getContextPath()%>/MessageShowController.aa" >받은쪽지함</a>
			</div>
		</div>
</c:if>

	<div class="accordion-tab-2">
		<input id="category-3" type="radio" name="tab">
		<label for="category-3">문의사항</label>
		<div class="accordion-tab-3">
			<a href="<%=request.getContextPath()%>/hpdesk/listhpdesk.aa">문의사항 목록보기</a>
			</div>
		</div>
	<div class="accordion-tab-2">
		<input id="category-4" type="radio" name="tab">
		<label for="category-4">사원조회</label>
		<div class="accordion-tab-3">
			<a href="<%=request.getContextPath()%>/selectEmp.aa">사원조회 목록보기</a>
		</div>
		</div>
		
	<div class="accordion-tab-2">
		<input id="category-5" type="radio" name="tab">
		<label for="category-5">근태 관리</label>
		<div class="accordion-tab-3">
			<a href="<%=request.getContextPath()%>/view/worktime/workForm.jsp">출퇴근 관리</a>
			<a href="<%=request.getContextPath()%>/view/worktime/dayoffForm.jsp">연차 관리</a>
			</div>
		</div>
			<div class="accordion-tab-2">
		<input id="category-6" type="radio" name="tab">
		<label for="category-6">설문 게시판</label>
		<div class="accordion-tab-3">
			<a href="<%=request.getContextPath()%>/surv/list.aa">게시판목록</a>
			</div>
		</div>
			<div class="accordion-tab-2">
		<input id="category-7" type="radio" name="tab">
		<label for="category-7">회의실 예약</label>
		<div class="accordion-tab-3">
			<a href="<%=request.getContextPath()%>/resv.aa">회의실 예약하기</a>
			<a href="<%=request.getContextPath()%>/resvCheck.aa">내 예약 확인하기</a>
			</div>
		</div>

	</div>
	
<script>
	Array.prototype.forEach.call(document.querySelectorAll('[type=radio]'), function(radio) {
		radio.addEventListener('click', function() {
			var self = this;

			Array.prototype.filter.call(document.getElementsByName(this.name), function(filterEl) {
				return self !== filterEl;
			}).forEach(function(otherEl) {
				delete otherEl.dataset.check
			})

			if (this.dataset.hasOwnProperty('check')) {
				this.checked = false
				delete this.dataset.check
			}
			else {
				this.dataset.check = ''
			}
		}, false)
	})
	</script>
</s_sidebar_element>


</body>
</html>