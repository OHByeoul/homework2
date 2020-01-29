<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ include file ="../header.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/board.css">
	<div class = "content">
		<input type="hidden" name="curPageNum" id="curPageNum" value="${curPageNum}"/>
		<div class = "table">
		    <table>
		        <thead>
		            <tr>
		                <th>번호</th>
		                <th>글종류</th>
		                <th>제목</th>
		                <th>작성자</th>
		                <th>작성일</th>
		                <th>조회수</th>
		            </tr>
		        </thead>
		        <tbody>
		        	<c:forEach var="board" items="${boards}" varStatus="status">
		        		<tr>
	    					<td>${board.id}</td>
	    					<td>${board.sort}</td>
	    					<td><a href='/board/getDetailContent/${board.id}'>${board.title}</a></td>
	    					<td>${board.createdBy}</td>
	    					<td>${board.YMD}</td>
	    					<td>${board.views}</td>
    					</tr>
					</c:forEach>
		        </tbody>
		    </table>
	    </div>
	</div>

    <script>
        	
    </script>
<%@ include file = "../footer.jsp" %>

