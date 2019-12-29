<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./header.jsp"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/boardDetail.css">
	<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/comment.css">
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
		<div class = "commentContainer">
			<form class = "commentForm">
				<label for="comment" >댓글</label>
				<textarea name="content" id="commentInput" cols="70" rows="3"></textarea>
				<button type="button" onclick="boardDetailMng.createComment()" >댓글작성</button>
			</form>
			<div class = "commentContent">
				
			</div>
		</div>
</div>

<script>
	var boardDetailMng = boardDetailMng || function() {
				
				var getter = function() {
				}

				params = {
					boardId : $('#boardId').val(),
					session : "${sessionScope.id}",
					createdBy : '',
					content : ''
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
								} else {
									alert('getDetailContent load fail')
								}
							})
					getter.commentLoad()
				}
				
				getter.commentLoad = function(){
					ajaxRequest("POST", "/comment/getComments", params.boardId,
							function(result, response){
								if(result){
									setComment(response[0])
								} else {
									alert('comment load fail')
								}
							})
				}
				
				getter.createComment = function(){
					getter.setCommentParam()
					ajaxRequest("POST", "/comment/createComment", params,
							function(result, response){
								if(result){
									setComment(response[0])
									$("#commentInput").val('')
								} else {
									alert('createComment fail')
								}
							})
				}

				getter.setRequestParam = function() {
					requestParams.form = $("#detailContent").serialize()
				}
				
				getter.setCommentParam = function(){
					params.content = $("#commentInput").val()
					params.createdBy = params.session
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
			
			function setComment(response){
				$(".commentContent").empty()
				for(each in response){
					let row = ''
					let content = response[each].content
					let createdBy = response[each].createdBy
					let YMD = response[each].YMD
					row = "<div class="+'commentInfo'+">"+
						"<label for='creator'>작성자  </label>"+
						"<span class='commentCreator' name='createdBy'>"+createdBy+"</span>"+
						"<label for='ymd'>  작성일  </label>"+
						"<span class="+'commentCreator'+">"+" ["+YMD+"] "+"</span>"+
					"</div>"+
					"<div class='comment' name='content'>"+content+
					"</div>"
					$(".commentContent").append(row)
				}
			}

	$(function() {
		boardDetailMng.dataLoad()

	})
</script>
<%@ include file="./footer.jsp"%>

