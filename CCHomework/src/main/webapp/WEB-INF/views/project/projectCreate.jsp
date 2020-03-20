<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ include file="../header.jsp"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/boardDetail.css">
<div class="container">
	<input type="hidden" name="projectId" id="projectId" value="${projectId}" />

		<form id = "createForm" method="post" >
		<table style="padding-top: 50px" align=center width=700 border=0
			cellpadding=2>
			<tr>
				<td height=20 align=center bgcolor=#ccc><font color=white>
					 수주현황 글쓰기</font></td>
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
					

					<input type="submit" id="submit" style="display:none">
					<center>
						<input type="button" id="target" onclick="projectCreateMng.submit()" value="글쓰기"> <input type="button"
							onclick="projectCreateMng.goList()" value="목록으로">
					</center>
				</td>
			</tr>
		</table>
	</form>
</div>

<script>

	var projectCreateMng = projectCreateMng || function() {
		var getter = function() {
		}

		requestParams = {
			form : ''
		}

		getter.setRequestParam = function() {
			let result = true
			if($("#projectTitle").val()==''){
				result = false
			}
			if($("#projectStart").val()==''){
				result = false
			}
			if($("#projectEnd").val()==''){
				result = false
			}
			
			requestParams.form = $("#createForm").serialize()
			return result
		}

		getter.submit = function() {
			//$("#submit").click()			
		
			let validate = getter.setRequestParam()
			if(validate){
				$.ajax({
					url : "/project/createProjectContent",
					type : "post",
					data : requestParams.form,
					dataType : "json"
				}).done(function(result) {
					location.href = "/project/getProjectDetailContent/" + result[0].id
				}).fail(function(result) {
					alert("createProjectContent ajax response fail")
				})
			}
		}

		getter.goList = function() {
			location.href="/project/"
		}

		return getter
	}()

	$(function() {
		projectCreateMng()
	})
</script>
<%@ include file="../footer.jsp"%>

