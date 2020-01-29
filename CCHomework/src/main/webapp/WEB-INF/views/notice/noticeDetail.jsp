<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/boardDetail.css">
	<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/comment.css">
<div class="container">
	<form id="detailContent">
		<input type="hidden" name="noticeId" id="noticeId" value="${noticeId}" readonly="readOnly"/>
		<table style="padding-top: 50px" align=center width=700 border=0
			cellpadding=2>
			<tr>
				<td height=20 align=center bgcolor=#ccc><font color=white>
						공지사항 상세조회</font></td>
			</tr>
			<tr>
				<td bgcolor=white>
					<table class="table2">
						<tr>
							<td>제목</td>
							<td><input type="text" name="title" id="title" size="60" readonly="readOnly"></td>
						</tr>					
						<tr>
							<td>작성자</td>
							<td><input type="text" name="createdBy" id="createdBy" size="20" readonly="readOnly"></td>
						</tr>
						<tr>
							<td>작성일</td>
							<td><input type="text" name="createdDate" id="createdDate" size="20" readonly="readOnly"></td>
						</tr>
						<tr>
							<td>내용</td>
							<td><textarea name="content" id="content" cols="85" rows="15" readonly="readOnly"></textarea></td>
						</tr>
						<tr>
							<td>게시 여부</td>
							<td><input type="radio" name="isPost" value="y" checked="checked" onclick="return(false)">게시</td>
							<td><input type="radio" name="isPost" value="n" onclick="return(false)">미게시</td>
						</tr>
					</table>

					<center>
						<button type="button" id="updateBtn" onclick="noticeDetailMng.updateContent()">수정하기</button>
						<button type="button" id="deleteBtn"
							onclick="noticeDetailMng.deleteContent()">삭제하기</button>
						<input type="button" onclick="noticeDetailMng.goList()" value="목록으로">
					</center>
				</td>
			</tr>
		</table>
		</form>
</div>

<script>
	var noticeDetailMng = noticeDetailMng || function() {
				
				var getter = function() {
				}

				params = {
					noticeId : $('#noticeId').val(),
					session : "${sessionScope.id}",
					createdBy : '',
					createdDate : '',
					content : '',
					isPost : ''
				}

				requestParams = {
					form : ''
				}

				getter.dataLoad = function() {
					ajaxRequest("POST", "/notice/getNoticeDetailContent", params,
							function(result, response) {
								if (result) {
									setContent(response[0])
									if(params.session != $("#createdBy").val()){
										$("#updateBtn").hide()
										$("#deleteBtn").hide()
									}
								} else {
									alert('getNoticeDetailContent load fail')
								}
							})
				}

				getter.setRequestParam = function() {
					requestParams.form = $("#detailContent").serialize()
				}
				
				getter.updateContent = function() {
					location.href = "/notice/updateNoticeContent/"+params.noticeId
				}

				getter.goList = function() {
					location.href="/board/"
				}

				getter.deleteContent = function() {
					let answer
					answer = confirm("해당 게시물을 삭제하시겠습니까?")
					if(answer == true){
						ajaxRequest("POST", "/notice/deleteContent", params, function(result, response) {
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
		let createdBy = response.createdBy
		let content = response.content
		let title = response.title
		let createdDate = response.createdDate
		let isPost = setPostOption(response.isPost)

		$("#createdBy").val(createdBy)
		$("#content").val(content)
		$("#title").val(title)
		$("#createdDate").val(createdDate)
	}
			
	function setPostOption(option){
		if(option=='y'){
			$('input:radio[name=isPost]:input[value=' + option + ']').attr("checked", true);
		} else if(option=='n'){
			$('input:radio[name=isPost]:input[value=' + option + ']').attr("checked", true);
		}	
	}

	$(function() {
		noticeDetailMng.dataLoad()

	})
</script>
<%@ include file="../footer.jsp"%>

