<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/resources/js/util.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.board.pager.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/header.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>게시판</title>
</head>
<body>
	<div class="header menu">
		<ul>
			<li><a class="active" href="/board/">홈</a></li>
			<li><a href="/board/">게시판 리스트</a></li>
			<li><a href="/board/createContent">게시판 글쓰기</a></li>
		</ul>
	</div>

	<div class="sidebar">
		<div class="w3-sidebar w3-bar-block w3-light-grey w3-card"
			style="width: 15%">
			<div>
				<span id="state" style="margin-left : 15px; "></span>
				<button type="button" id="logout" onclick="logout()">로그아웃</button>
			</div>
			<div style="margin-left : 15px">
				<form action="/board/searchBoardList">
				<input type="text" id="search" name="searchName" placeholder="검색할 제목 입력" required>
				<button type = "submit" id="searchBtn">검색</button>
				</form>
			</div>
			<a href="/board/createContent" class="w3-bar-item w3-button">글쓰기</a> <a href="/board/"
				class="w3-bar-item w3-button">목록보기</a>
				<!-- 
			<div class="w3-dropdown-hover">
				<button class="w3-button">
					목록 정렬 <i class="fa fa-caret-down"></i>
				</button>
				<div class="w3-dropdown-content w3-bar-block">
					<a href="#" class="w3-bar-item w3-button">id 정렬</a> 
					<a href="#" class="w3-bar-item w3-button">제목 정렬</a>
					<a href="#" class="w3-bar-item w3-button">종류 정렬</a>
				</div>
			</div>
			 -->
		</div>
	</div>
	<script>
		let a = "${sessionScope.id}";
		if(a != ""){
			$("#state").text(a+"님 하잉")
		} else {
			$("#logout").hide()
		}
		
		function logout(){
			location.href = "/logout"
		}
	</script>