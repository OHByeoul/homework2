<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/boardDetail.css">
	<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/comment.css">
<div class="container">
	<form id="detailContent">
		<input type="hidden" name="projectId" id="projectId" value="${projectId}" readonly="readOnly"/>
		<table style="padding-top: 50px" align=center width=700 border=0
			cellpadding=2>
			<tr>
				<td height=20 align=center bgcolor=#ccc><font color=white>
						수주현황 상세조회</font></td>
			</tr>
			<tr>
				<td bgcolor=white>
					<table class="table2">
						<tr>
							<td>사업명</td>
							<td><input type="text" name="projectTitle" id="projectTitle" size="60" required readonly="readOnly"></td>
						</tr>
						<tr>
							<td>작성자</td>
							<td><input type="text" name="createdBy" id="createdBy" size="20" value="${sessionScope.id}" readonly="readOnly"></td>
						</tr>
						<tr>
							<td>작성일</td>
							<td><input type="text" name="createdDate" id="createdDate" size="20" value="" placeholder="20200120" readonly="readOnly"></td>
						</tr>

						<tr>
							<td>프로젝트 시작일</td>
							<td><input type="text" name="projectStart" id="projectStart" size="60" placeholder="20200120" required readonly="readOnly"></td>
						</tr>
						<tr>
							<td>프로젝트 종료일</td>
							<td><input type="text" name="projectEnd" id="projectEnd" size="60" placeholder="20200120" required readonly="readOnly"></td>
						</tr>
						<tr>
							<td>게시 여부</td>
							<td><input type="radio" name="isPost" value="y" checked="checked" onclick="return(false)">게시</td>
							<td><input type="radio" name="isPost" value="n" onclick="return(false)">미게시</td>
						</tr>
					</table>

					<center>
						<button type="button" id="updateBtn" onclick="projectDetailMng.updateContent()">수정하기</button>
						<button type="button" id="deleteBtn"
							onclick="projectDetailMng.deleteContent()">삭제하기</button>
						<input type="button" onclick="projectDetailMng.goList()" value="목록으로">
					</center>
				</td>
			</tr>
		</table>
		</form>
</div>

<script>
	var projectDetailMng = projectDetailMng || function() {
				
				var getter = function() {
				}

				params = {
					projectId : $('#projectId').val(),
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
					ajaxRequest("POST", "/project/getProjectDetailContent", params,
							function(result, response) {
								if (result) {
									setContent(response[0])
									if(params.session != $("#createdBy").val()){
										$("#updateBtn").hide()
										$("#deleteBtn").hide()
									}
								} else {
									alert('getProjectDetailContent load fail')
								}
							})
				}

				getter.setRequestParam = function() {
					requestParams.form = $("#detailContent").serialize()
				}
				
				getter.updateContent = function() {
					location.href = "/project/updateProjectContent/"+params.projectId
				}

				getter.goList = function() {
					location.href="/project/"
				}

				getter.deleteContent = function() {
					let answer
					answer = confirm("해당 게시물을 삭제하시겠습니까?")
					if(answer == true){
						ajaxRequest("POST", "/project/deleteContent", params, function(result, response) {
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
		let projectStart = response.projectStart
		let projectEnd = response.projectEnd
		let projectTitle = response.projectTitle
		let createdDate = response.createdDate
		let isPost = setPostOption(response.isPost)

		$("#createdBy").val(createdBy)
		$("#projectTitle").val(projectTitle)
		$("#projectStart").val(projectStart)
		$("#projectEnd").val(projectEnd)
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
		projectDetailMng.dataLoad()

	})
</script>
<%@ include file="../footer.jsp"%>

