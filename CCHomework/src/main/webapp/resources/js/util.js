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