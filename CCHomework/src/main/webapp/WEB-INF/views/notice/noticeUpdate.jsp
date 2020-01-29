<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/boardDetail.css">
<div class="container">
	<form id="updateForm">
		<input type="hidden" name="noticeId" id="noticeId" value="${noticeId}" readonly="readOnly"/>
		<table style="padding-top: 50px" align=center width=700 border=0
			cellpadding=2>
			<tr>
				<td height=20 align=center bgcolor=#ccc><font color=white>
						공지사항 수정</font></td>
			</tr>
			<tr>
				<td bgcolor=white>
					<table class="table2">
						<tr>
							<td>제목</td>
							<td><input type="text" name="title" id="title" size="60"></td>
						</tr>
						<tr>
							<td>작성자</td>
							<td><input type="text" name="createdBy" id="createdBy" size="20" value="${sessionScope.id}" readonly="readOnly"></td>
						</tr>
						<tr>
							<td>작성일</td>
							<td><input type="text" name="createdDate" id="createdDate" size="20" value="" placeholder="20200120"></td>
						</tr>

						<tr>
							<td>내용</td>
							<td><textarea id="content" name="content"cols="85" rows="15"></textarea></td>
						</tr>
						<tr>
							<td>게시 여부</td>
							<td><input type="radio" name="isPost" value="y">게시</td>
							<td><input type="radio" name="isPost" value="n">미게시</td>
						</tr>
					</table>

					<center>
						<button type="button" id="updateComplete"
							onclick="noticeUpdateObj.submit()">수정 완료</button>
						<input type="button" onclick="noticeUpdateObj.goBack()" value="뒤로가기">
					</center>
				</td>
			</tr>
		</table>
		</form>
</div>

<script>
	var noticeUpdateObj = noticeUpdateObj || function() {
				var getter = function() {
				}

				params = {
					noticeId : $('#noticeId').val()
				}

				requestParams = {
					form : ''
				}

				getter.dataLoad = function() {
					ajaxRequest("POST", "/notice/getNoticeDetailContent", params,
							function(result, response) {
								if (result) {
									setContent(response[0])
								}
							})
				}

				getter.setRequestParam = function() {
					requestParams.form = $("#updateForm").serialize()
				}

				getter.submit = function() {
					getter.setRequestParam()
					$.ajax({
						url : "/notice/updateNoticeContent",
						type : "post",
						data : requestParams.form,
						dataType : "json"
					}).done(
							function(result) {
								console.log('done')
								location.href = "/notice/getNoticeDetailContent/"
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
			
	function setPostOption(isPost){
		if(isPost == 'y'){
			$('input:radio[name=isPost]:input[value=y]').attr("checked", true);
			$('input:radio[name=isPost]:input[value=n]').attr("checked", false);
		} else {
			$('input:radio[name=isPost]:input[value=y]').attr("checked", false);
			$('input:radio[name=isPost]:input[value=n]').attr("checked", true);
		}
	}
				

	$(function() {
		noticeUpdateObj.dataLoad()

	})
</script>
<%@ include file="../footer.jsp"%>

