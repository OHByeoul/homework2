<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ include file="./header.jsp"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/boardDetail.css">
<div class="container">
	<input type="hidden" name="boardId" id="boardId" value="${boardId}" />

		<form id = "createForm" method="post" action="./createContent">
		<table style="padding-top: 50px" align=center width=700 border=0
			cellpadding=2>
			<tr>
				<td height=20 align=center bgcolor=#ccc><font color=white>
						게시판 글쓰기</font></td>
			</tr>
			<tr>
				<td bgcolor=white>
					<table class="table2">
						<tr>
							<td>종류</td>
							<td><select name="sort" id="sort">
									<option value="0">일반</option>
									<option value="1">정보</option>
									<option value="2">리뷰</option>
									<option value="3">자유</option>
							</select></td>
						</tr>
						<tr>
							<td>작성자</td>
							<td><input type="text" name="createdBy" id="createdBy" size="20" value="${sessionScope.id}" readonly="readOnly"></td>
						</tr>

						<tr>
							<td>제목</td>
							<td><input type="text" name="title" id="title" size="60" ></td>
						</tr>

						<tr>
							<td>내용</td>
							<td><textarea id="content" name="content"cols="85" rows="15" ></textarea></td>
						</tr>
					</table>

					<center>
						<input type="button" onclick="boardCreateMng.submit()" value="글쓰기"> <input type="button"
							onclick="boardCreateMng.goList()" value="목록으로">
					</center>
				</td>
			</tr>
		</table>
	</form>
</div>

<script>

	var boardCreateMng = boardCreateMng || function() {
		var getter = function() {
		}

		requestParams = {
			form : ''
		}

		getter.setRequestParam = function() {
			requestParams.form = $("#createForm").serialize()
		}

		getter.submit = function() {
			getter.setRequestParam()
			$.ajax({
				url : "/board/createContent",
				type : "post",
				data : requestParams.form,
				dataType : "json"
			}).done(function(result) {
				location.href = "/board/getDetailContent/" + result[0].id
			}).fail(function(result) {
				alert("createContent ajax response fail")
			})
		}

		getter.goList = function() {
			location.href="/board/"
		}

		return getter
	}()

	$(function() {
		boardCreateMng()
	})
</script>
<%@ include file="./footer.jsp"%>

