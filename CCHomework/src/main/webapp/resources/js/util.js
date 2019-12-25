	const ajaxRequest = function(method,url, param, callback){
		if(typeof (jQuery) == undefined){
			alert("jQuery library is undefined")
			return
		}
		
		try {
			$.ajax({
				type : method,
				url : url,
				data : JSON.stringify(param),
				dataType : "json",
				contentType : "application/json; charset=utf-8"
			}).done(function(response){
				callback(true, response);
			}).fail(function(fail){
				callback(false, fail);
			}).always(function(always){
				console.log(always)
			})
		} catch (error){
			alert("error")
		}
	}
	
	function listPaging(totalCnt, curPageNo, listSize, cb ){
		   /*
		   var totalCnt = ${totalCnt };   // totalcount
		   var curPageNo = ${curPageNo };   // init page or current page
		   var listSize = ${listSize };   // count per page
		    */   
		   var pageChanged = function(sPage) {
		      $("input#curPageNo").val(sPage);
		      cb(sPage);
		   };
		   
		   // paging data init
		   $("#divCollectDataPager").empty().pagerEx({

		      totalItemCount : totalCnt,   // totalcount
		      currentPage : curPageNo,   // current page
		      recordPerPage : listSize,         // count per page
		      pageIndexChanged : pageChanged
		   });
		}
	
	function setSortFormat(sort){
		if(sort == 0){
			return "일반";
		} else if(sort == 1){
			return "정보";
		} else if(sort == 2){
			return "리뷰";
		} else if(sort == 3){
			return "자유";
		}
	}