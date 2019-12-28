<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./header.jsp"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/boardDetail.css">
<div class="container">
	<form id="detailContent">
		<input type="hidden" name="boardId" id="boardId" value="${boardId}" readonly="readOnly"/>
		<table style="padding-top: 50px" align=center width=700 border=0
			cellpadding=2>
			<tr>
				<td height=20 align=center bgcolor=#ccc><font color=white>
						게시판 상세조회</font></td>
			</tr>
			<tr>
				<td bgcolor=white>
					<table class="table2">
						<tr>
							<td>종류</td>
							<td><input type="text" name="sort" id="sort" size="20" readonly="readOnly"></td>
						</tr>
						<tr>
							<td>작성자</td>
							<td><input type="text" name="createdBy" id="createdBy" size="20" readonly="readOnly"></td>
						</tr>

						<tr>
							<td>제목</td>
							<td><input type="text" name="title" id="title" size="60" readonly="readOnly"></td>
						</tr>
						<tr>
							<td>작성일</td>
							<td><input type="text" name="YMD" id="YMD" size="20" readonly="readOnly"></td>
						</tr>

						<tr>
							<td>내용</td>
							<td><textarea name="content" id="content" cols="85" rows="15" readonly="readOnly"></textarea></td>
						</tr>
					</table>

					<center>
						<button type="button" id="updateBtn" onclick="boardDetailMng.updateContent()">수정하기</button>
						<button type="button" id="deleteBtn"
							onclick="boardDetailMng.deleteContent()">삭제하기</button>
						<input type="button" onclick="boardDetailMng.goList()" value="목록으로">
					</center>
				</td>
			</tr>
		</table>
		</form>
</div>

<script>
	var boardDetailMng = boardDetailMng || function() {
				
				var getter = function() {
				}

				params = {
					boardId : $('#boardId').val(),
					session : "${sessionScope.id}"
				}

				requestParams = {
					form : ''
				}

				getter.dataLoad = function() {
					ajaxRequest("POST", "/board/getDetailContent", params,
							function(result, response) {
								if (result) {
									setContent(response[0])
									if(params.session != $("#createdBy").val()){
										$("#updateBtn").hide()
										$("#deleteBtn").hide()
									}
								}
							})
				}

				getter.setRequestParam = function() {
					requestParams.form = $("#detailContent").serialize()
				}

				getter.updateContent = function() {
					location.href = "/board/updateContent/"+params.boardId
				}

				getter.goList = function() {
					location.href="/board/"
				}

				getter.deleteContent = function() {
					let answer
					answer = confirm("해당 게시물을 삭제하시겠습니까?")
					if(answer == true){
					ajaxRequest("POST", "/board/deleteContent", params,
							function(result, response) {
								if (result) {
									alert('해당 게시물이 삭제되었습니다.')
									location.href = "/board/";
								} else {
									alert("deleteContent ajax response fail")
								}
							})
					}
				}

				return getter
			}()

	function setContent(response) {
		let sort = response.sort
		let createdBy = response.createdBy
		let content = response.content
		let title = response.title
		let YMD = response.YMD

		$("#sort").val(setSortFormat(sort))
		$("#createdBy").val(createdBy)
		$("#content").val(content)
		$("#title").val(title)
		$("#YMD").val(YMD)
	}

	$(function() {
		boardDetailMng.dataLoad()

	})
</script>
<%@ include file="./footer.jsp"%>

