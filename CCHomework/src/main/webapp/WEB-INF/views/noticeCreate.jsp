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
						공지사항 글쓰기</font></td>
			</tr>
			<tr>
				<td bgcolor=white>
					<table class="table2">
						<tr>
							<td>제목</td>
							<td><input type="text" name="title" id="title" size="60" required></td>
						</tr>
						<tr>
							<td>작성자</td>
							<td><input type="text" name="createdBy" id="createdBy" size="20" value="${sessionScope.id}" readonly="readOnly"></td>
						</tr>
						<tr>
							<td>작성일</td>
							<td><input type="text" name="createdDate" id="createdDate" size="20" value="" placeholder="20200120" required></td>
						</tr>

						<tr>
							<td>내용</td>
							<td><textarea id="content" name="content"cols="85" rows="15" required></textarea></td>
						</tr>
						<tr>
							<td>게시 여부</td>
							<td><input type="radio" name="isPost" value="y" checked="checked">게시</td>
							<td><input type="radio" name="isPost" value="n">미게시</td>
						</tr>
					</table>

					<center>
						<input type="button" onclick="noticeCreateMng.submit()" value="글쓰기"> <input type="button"
							onclick="noticeCreateMng.goList()" value="목록으로">
					</center>
				</td>
			</tr>
		</table>
	</form>
</div>

<script>

	var noticeCreateMng = noticeCreateMng || function() {
		var getter = function() {
		}

		requestParams = {
			form : ''
		}

		getter.setRequestParam = function() {
			let result = true
			if($("#title").val()==''){
				alert('제목을 입력해주세요')
				result = false
			} 
			if($("#content").val()==''){
				alert('내용을 입력해주세요')
				result = false
			} 
			
			requestParams.form = $("#createForm").serialize()
			return result
		}

		getter.submit = function() {
			let validate = getter.setRequestParam()
			if(validate){
				$.ajax({
					url : "/notice/createNoticeContent",
					type : "post",
					data : requestParams.form,
					dataType : "json"
				}).done(function(result) {
					location.href = "/notice/getNoticeDetailContent/" + result[0].id
				}).fail(function(result) {
					alert("createNoticeContent ajax response fail")
				})
			}
		}

		getter.goList = function() {
			location.href="/board/"
		}

		return getter
	}()

	$(function() {
		noticeCreateMng()
	})
</script>
<%@ include file="./footer.jsp"%>

