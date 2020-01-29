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
		                <th>프로젝트명</th>
		                <th>프로젝트 기간</th>
		                <th>작성자</th>
		                <th>작성일</th>
		            </tr>
		        </thead>
		        <tbody>
		        	
		        </tbody>
		    </table>
                <div class="button">
                	<button type="button" onclick="projectMng.createForm()" id="write">글쓰기</button>
                </div>
		        <div class="paging">
                    <div id="divCollectDataPager"></div>
                </div>
	    </div>
	</div>

    <script>
        	var projectMng = projectMng || function(){
        		var getter = function(){},
        		
        		paging = {
        			totalCnt : '0',
        			curPageNum : $("#curPageNum").val() ||'1',
        			listSize : '0'
        		}
        		
        		getter.dataLoad = function(){
        			ajaxRequest("POST","/project/getProjectList",paging, function(result, response){
        				if(result == true){
        					setContent(response[1])
        					
        					totalCnt = response[0].totalCnt
        					curPageNum = response[0].curPageNum
        					listSize = response[0].listSize
        					
        					pagingRefresh(totalCnt,curPageNum,listSize)
        				} else {
        					alert("getProjectList ajax response fail")
        				}
        			})
        		}
        		
        		getter.createForm = function(){
        			location.href = "/project/createProjectContent";
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
        				"<td><a href='/project/getProjectDetailContent/"+response[each].id+"'> "+response[each].projectTitle+"</a></td>"+
        				"<td>"+response[each].projectStart+" ~ "+response[each].projectEnd+"</td>"+"<td>"+response[each].createdBy+"</td>"
        				+"<td>"+response[each].createdDate+"</td>"+"</tr>";
	      				$(".table table tbody").append(row)
  					}
        		}
        		
        		return getter
        	}()
        	
        $(function(){
        	projectMng.dataLoad()
        	
        })
    </script>
<%@ include file = "../footer.jsp" %>

