<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="../header.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/board.css">
	<div class = "content">
		<input type="hidden" name="curPageNum" id="curPageNum" value="${curPageNum}"/>
		<div class = "table">
		    <table>
		        <thead>
		            <tr>
		                <th>번호</th>
		                <th>제목</th>
		                <th>작성자</th>
		                <th>작성일</th>
		            </tr>
		        </thead>
		        <tbody>
		        	
		        </tbody>
		    </table>
                <div class="button">
                	<button type="button" onclick="boardMng.createForm()" id="write">글쓰기</button>
                </div>
		        <div class="paging">
                    <div id="divCollectDataPager"></div>
                </div>
	    </div>
	</div>

    <script>
        	var boardMng = boardMng || function(){
        		var getter = function(){},
        		
        		paging = {
        			totalCnt : '0',
        			curPageNum : $("#curPageNum").val() ||'1',
        			listSize : '0'
        		}
        		
        		getter.dataLoad = function(){
        			ajaxRequest("POST","/notice/getNoticeList",paging, function(result, response){
        				if(result == true){
        					setContent(response[1])
        					
        					totalCnt = response[0].totalCnt
        					curPageNum = response[0].curPageNum
        					listSize = response[0].listSize
        					
        					pagingRefresh(totalCnt,curPageNum,listSize)
        				} else {
        					alert("getNoticeList ajax response fail")
        				}
        			})
        		}
        		
        		getter.createForm = function(){
        			location.href = "/notice/createNoticeContent";
        		}
        		

        		function pagingRefresh(totalCnt, curPageNum, listSize){
        			listPaging(totalCnt,curPageNum,listSize, cb)
        		}


        		function cb(target){
        			$('#curPageNo').val(target)
        			paging.curPageNum = target
	       			getter.dataLoad()
        		}
        		
        		function setContent(response){
        			$("tbody").empty()
        			let row
  					for(let each in response){
        				row = "<tr>"+"<td>"+response[each].id+"</td>"+
        				"<td><a href='/notice/getNoticeDetailContent/"+response[each].id+"'> "+response[each].title+"</a></td>"+"<td>"+response[each].createdBy+"</td>"
        				+"<td>"+response[each].createdDate+"</td>"+"</tr>";
	      				$(".table table tbody").append(row)
  					}
        		}
        		
        		
        		
        		return getter
        	}()
        	
        $(function(){
        	boardMng.dataLoad()
        	
        })
    </script>
<%@ include file = "../footer.jsp" %>

