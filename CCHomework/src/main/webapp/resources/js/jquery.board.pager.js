(function ($) {
    $.fn.pagerEx = function (setting) {
        var settings = $.extend({
            pager: $(this),
            totalItemCount: 100,
            recordPerPage: 10,
            currentPage: 1,
            pageGroupSize: 10,
            pageIndexChanged: null,
        }, setting || {});

        var showPager = function () {
            if (settings.totalItemCount == 0) { return; }
            settings.pager.empty();

            var $pageLink, baseVal = settings.currentPage - 1, pageCount = Math.ceil(settings.totalItemCount / settings.recordPerPage), iLoop = (Math.floor(baseVal / settings.pageGroupSize) * settings.pageGroupSize) + 1, iLoopEnd = (Math.floor(baseVal / settings.pageGroupSize) + 1) * settings.pageGroupSize, root = $("<div />").addClass("btn-group");
            
            if (iLoopEnd > pageCount) iLoopEnd = pageCount;
            
            // org source
            
            // prev 버튼 조건 노출
            var $prev = $("<button />").addClass("arr left").append($("<i/>").addClass("fa fa-chevron-left"));
            if (settings.currentPage > settings.pageGroupSize) root.append($prev.click(function () { settings.pageIndexChanged(iLoop - settings.pageGroupSize); }));
            
            // prev 버튼 상시 노출

            for (var page = iLoop; page <= iLoopEnd; page++) {
            	
            	$pageLink = $("<button />").addClass("").text(page);
                if (page != settings.currentPage) $pageLink.unbind("click").bind({ click: function () { settings.currentPage = parseInt($(this).text()); settings.pageIndexChanged(settings.currentPage); $pageLink.removeClass("current"); } });
                else {$pageLink.attr("disabled", "disabled"); $pageLink.addClass("current");}
                
                root.append($pageLink);
            }

            // org source
            //var $next = $("<button />").addClass("btn btn-default btn-sm").append($("<i/>").addClass("icon ml-chevron-right"));
            //if (pageCount > iLoopEnd) root.append($next.click(function () { settings.pageIndexChanged(iLoop + settings.pageGroupSize); }));
            
            // next 버튼 조건 노출
            var $next = $("<button />").addClass("arr right").append($("<i/>").addClass("fa fa-chevron-right"));
            if (pageCount > iLoopEnd) root.append($next.click(function () { settings.pageIndexChanged(iLoop + settings.pageGroupSize); }));
            
            // next 버튼 상시 노출

            
            var $design = $("<div />").addClass("pagenation").append(root)
            settings.pager.append($design);
        }
        showPager();
    }
    
    $.fn.popuppagerEx = function (setting) {
        var settings = $.extend({
            pager: $(this),
            totalItemCount: 100,
            recordPerPage: 10,
            currentPage: 1,
            pageGroupSize: 10,
            pageIndexChanged: null,
        }, setting || {});

        var showPager = function () {
            if (settings.totalItemCount == 0) { return; }
            settings.pager.empty();

            var $pageLink, baseVal = settings.currentPage - 1, pageCount = Math.ceil(settings.totalItemCount / settings.recordPerPage), iLoop = (Math.floor(baseVal / settings.pageGroupSize) * settings.pageGroupSize) + 1, iLoopEnd = (Math.floor(baseVal / settings.pageGroupSize) + 1) * settings.pageGroupSize, root = $("<div />").addClass("btn-group");

            
            if (iLoopEnd > pageCount) iLoopEnd = pageCount;
            
            // org source
            
            // prev 버튼 조건 노출
            var $prev = $("<button />").addClass("arr left").append($("<i/>").addClass("fa fa-chevron-left"));
            if (settings.currentPage > settings.pageGroupSize) root.append($prev.click(function () { settings.pageIndexChanged(iLoop - settings.pageGroupSize); }));
            
            // prev 버튼 상시 노출


            for (var page = iLoop; page <= iLoopEnd; page++) {
            	
            	$pageLink = $("<button />").addClass("").text(page);
                if (page != settings.currentPage) $pageLink.unbind("click").bind({ click: function () { settings.currentPage = parseInt($(this).text()); settings.pageIndexChanged(settings.currentPage); $pageLink.removeClass("current"); } });
                else {$pageLink.attr("disabled", "disabled"); $pageLink.addClass("current");}
                
                root.append($pageLink);
            }

            // org source
            
            // next 버튼 조건 노출
            var $next = $("<button />").addClass("arr right").append($("<i/>").addClass("fa fa-chevron-right"));
            if (pageCount > iLoopEnd) root.append($next.click(function () { settings.pageIndexChanged(iLoop + settings.pageGroupSize); }));
            
            // next 버튼 상시 노출
            
            var $design = $("<div />").addClass("popuppagenation").append(root)
            settings.pager.append($design);
        }
        showPager();
    }
})(jQuery);
