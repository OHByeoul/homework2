<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="./header.jsp" %>
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
		        	
		        </tbody>
		    </table>
		        <div class="paging">
                    <div id="divCollectDataPager"></div>
                </div>
	    </div>
	</div>
	<style>
		.btn-group {
			margin-left : 950px;
			margin-top : 20px;
		}
	</style>
    <script>
        	var boardObj = boardObj || function(){
        		var getter = function(){},
        		
        		paging = {
        			totalCnt : '0',
        			curPageNum : $("#curPageNum").val() ||'1',
        			listSize : '0'
        		}
        		
        		getter.dataLoad = function(){
        			ajaxRequest("POST","./getBoardList",paging, function(result, response){
        				if(result == true){
        					setContent(response[1])
        					
        					totalCnt = response[0].totalCnt
        					curPageNum = response[0].curPageNum
        					listSize = response[0].listSize
        					
        					pagingRefresh(totalCnt,curPageNum,listSize)
        				} else {
        					alert("response fail")
        				}
        			})
        		}
        		

        		function pagingRefresh(totalCnt, curPageNum, listSize){
        			listPaging(totalCnt,curPageNum,listSize, cb)
        		}


        		function cb(target){
        			$('#curPageNo').val(target)
        			paging.curPageNum = target
//        			page.requestParameters.curPageNo = target
	       			getter.dataLoad()
        		}

        		
        		function setPagingData(){
        			
        		}
        		
        		function setContent(response){
        			$("tbody").empty()
        			let row
  					for(let each in response){
        				row = "<tr>"+"<td>"+response[each].id+"</td>"+"<td>"+response[each].sort+"</td>"+
        				"<td><a href='./getContent/'"+response[each].id+"> "+response[each].title+"</a></td>"+"<td>"+response[each].createdBy+"</td>"+"<td>"+response[each].YMD+"</td>"+"</tr>";
	      				$(".table table tbody").append(row)
  					}
        		}
        		
        		return getter
        	}()
        	
        $(function(){
        	boardObj.dataLoad()
        	
        })
    </script>
<%@ include file = "./footer.jsp" %>

