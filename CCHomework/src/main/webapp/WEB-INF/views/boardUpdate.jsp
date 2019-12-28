<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./header.jsp"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/boardDetail.css">
<div class="container">
	<form id="updateForm">
		<input type="hidden" name="boardId" id="boardId" value="${boardId}" readonly="readOnly"/>
		<table style="padding-top: 50px" align=center width=700 border=0
			cellpadding=2>
			<tr>
				<td height=20 align=center bgcolor=#ccc><font color=white>
						게시판 수정</font></td>
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
							<td><input type="text" name="createdBy" id="createdBy" size="20" readonly="readOnly"></td>
						</tr>

						<tr>
							<td>제목</td>
							<td><input type="text" name="title" id="title" size="60"></td>
						</tr>
						<tr>
							<td>내용</td>
							<td><textarea name="content" id="content" cols="85" rows="15"></textarea></td>
						</tr>
					</table>

					<center>
						<button type="button" id="updateComplete"
							onclick="boardUpdateObj.submit()">수정 완료</button>
						<input type="button" onclick="boardUpdateObj.goBack()" value="뒤로가기">
					</center>
				</td>
			</tr>
		</table>
		</form>
</div>

<script>
	var boardUpdateObj = boardUpdateObj || function() {
				var getter = function() {
				}

				params = {
					boardId : $('#boardId').val()
				}

				requestParams = {
					form : ''
				}

				getter.dataLoad = function() {
					ajaxRequest("POST", "/board/getDetailContent", params,
							function(result, response) {
								if (result) {
									setContent(response[0])
								}
							})
				}

				getter.setRequestParam = function() {
					requestParams.form = $("#updateForm").serialize()
				}
/*
				getter.readOnlyChange = function() {
					if ($('input')[0].hasAttribute("readOnly")) {

						alert('수정할 내용을 수정 후 수정완료 버튼을 누르세요')
						$('input').attr('readOnly', false)
						$('textarea').attr('readOnly', false)
						$('#YMD').attr('readOnly', true)
						$('#sort').attr('readOnly', true)
						$('#updateComplete').show();
					} else {
						alert('현재 읽기 상태로 수정 불가합니다.')
						$('input').attr('readOnly', true)
						$('textarea').attr('readOnly', true)
						$('#updateComplete').hide();
					}
				}*/

				getter.submit = function() {
					getter.setRequestParam()
					$.ajax({
						url : "/board/updateContent",
						type : "post",
						data : requestParams.form,
						dataType : "json"
					}).done(
							function(result) {
								console.log('done')
								location.href = "/board/getDetailContent/"
										+ result[0].id
								console.log('succccc')
							}).fail(function(result) {
						console.log('fuckkkk')
					})
				}

				getter.goBack = function() {
					history.back()
				}

				return getter
			}()

	function setContent(response) {
		let sort = response.sort
		let createdBy = response.createdBy
		let content = response.content
		let title = response.title
		let YMD = response.YMD

		$("#sort select").val(sort)
		$("#createdBy").val(createdBy)
		$("#content").val(content)
		$("#title").val(title)
		$("#YMD").val(YMD)
	}

	$(function() {
		boardUpdateObj.dataLoad()

	})
</script>
<%@ include file="./footer.jsp"%>

