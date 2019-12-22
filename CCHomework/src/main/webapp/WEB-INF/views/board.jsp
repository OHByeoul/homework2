<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="./header.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/board.css">
	<div class = "content">
		<div class = "table">
		    <table>
		        <thead>
		            <tr>
		                <th>번호</th>
		                <th>글종류</th>
		                <th>제목</th>
		                <th>내용</th>
		                <th>작성자</th>
		                <th>작성일</th>
		                <th>조회수</th>
		            </tr>
		        </thead>
		        <tbody>
		        	
		        </tbody>
		    </table>
	    </div>
	</div>
    <script>
        	var boardObj = boardObj || function(){
        		var getter = function(){},
        		
        		params = {
        			totalCnt : '0',
        			curPageNum : '0'
        		}
        		
        		getter.init = function(){
        			ajaxRequest("POST","./getBoardList","", function(result, response){
        				if(result == true){
        					setContent(response)
        					
        				} else {
        					alert("response fail")
        				}
        			})
        		}
        		
        		function setContent(response){
        			let row
  					for(let each in response){
        				row = "<tr>"+"<td>"+response[each].id+"</td>"+"<td>"+response[each].sort+"</td>"+
        				"<td>"+response[each].title+"</td>"+"<td>"+response[each].content+"</td>"+"<td>"+response[each].createdBy+"</td>"+"<td>"+response[each].YMD+"</td>"+"</tr>";
	      				$(".table table tbody").append(row)
  					}
        		}
        		
        		return getter
        	}()
        	
        $(function(){
        	boardObj.init()
        	
        })
    </script>
<%@ include file = "./footer.jsp" %>

