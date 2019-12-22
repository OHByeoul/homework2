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
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/header.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>게시판</title>
</head>
<body>
	<div class="header menu">
		<ul>
			<li><a class="active" href="#">홈</a></li>
			<li><a href="#">게시판</a></li>
			<li><a href="#">기타</a></li>
			<li><a href="#">기타2</a></li>
		</ul>
	</div>

	<div class="sidebar">
		<div class="w3-sidebar w3-bar-block w3-light-grey w3-card"
			style="width: 15%">
			<a href="#" class="w3-bar-item w3-button">뭐넣지 1</a> <a href="#"
				class="w3-bar-item w3-button">뭐넣지 2</a>
			<div class="w3-dropdown-hover">
				<button class="w3-button">
					뭐넣지 <i class="fa fa-caret-down"></i>
				</button>
				<div class="w3-dropdown-content w3-bar-block">
					<a href="#" class="w3-bar-item w3-button">가나다</a> <a href="#"
						class="w3-bar-item w3-button">가나다</a>
				</div>
			</div>
			<a href="#" class="w3-bar-item w3-button">뭐넣지 3</a>
		</div>
	</div>