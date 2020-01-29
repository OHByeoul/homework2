<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/boardDetail.css">
<div class="container">
	<form id="updateForm">
		<input type="hidden" name="projectId" id="projectId" value="${projectId}" readonly="readOnly"/>
		<table style="padding-top: 50px" align=center width=700 border=0
			cellpadding=2>
			<tr>
				<td height=20 align=center bgcolor=#ccc><font color=white>
						수주현황 수정</font></td>
			</tr>
			<tr>
				<td bgcolor=white>
					<table class="table2">
						<tr>
							<td>사업명</td>
							<td><input type="text" name="projectTitle" id="projectTitle" size="60" required></td>
						</tr>
						<tr>
							<td>작성자</td>
							<td><input type="text" name="createdBy" id="createdBy" size="20" value="${sessionScope.id}" readonly="readOnly"></td>
						</tr>
						<tr>
							<td>작성일</td>
							<td><input type="text" name="createdDate" id="createdDate" size="20" value="" placeholder="20200120" ></td>
						</tr>

						<tr>
							<td>프로젝트 시작일</td>
							<td><input type="text" name="projectStart" id="projectStart" size="60" placeholder="20200120" required></td>
						</tr>
						<tr>
							<td>프로젝트 종료일</td>
							<td><input type="text" name="projectEnd" id="projectEnd" size="60" placeholder="20200120" required></td>
						</tr>
						<tr>
							<td>게시 여부</td>
							<td><input type="radio" name="isPost" value="y" checked="checked">게시</td>
							<td><input type="radio" name="isPost" value="n">미게시</td>
						</tr>
					</table>

					<center>
						<button type="button" id="updateComplete"
							onclick="projectUpdateObj.submit()">수정 완료</button>
						<input type="button" onclick="projectUpdateObj.goBack()" value="뒤로가기">
					</center>
				</td>
			</tr>
		</table>
		</form>
</div>

<script>
	var projectUpdateObj = projectUpdateObj || function() {
				var getter = function() {
				}

				params = {
					projectId : $('#projectId').val()
				}

				requestParams = {
					form : ''
				}

				getter.dataLoad = function() {
					ajaxRequest("POST", "/project/getProjectDetailContent", params,
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
						url : "/project/updateProjectContent",
						type : "post",
						data : requestParams.form,
						dataType : "json"
					}).done(
							function(result) {
								console.log('done')
								location.href = "/project/getProjectDetailContent/"
										+ result[0].id
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
		projectUpdateObj.dataLoad()

	})
</script>
<%@ include file="../footer.jsp"%>

