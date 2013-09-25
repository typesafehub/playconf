
$(".flip-thing > li").each(function(i,el){
	var number = parseFloat($(el).text());
	$(el).html("").addClass("num-"+number);
	$(el).append("<span class='none'/>");
	for (var i=0;i<=number;i++){
		$(el).append("<span class='back-"+i+"'/>");
		$(el).append("<span class='num-"+i+"'/>");
	}
});


;(function(){

	var routes = {
		write: function(el){
			var day = $(el).parents(".day").attr("data-day"),
				hour = $(el).attr("data-time"),
				room = $(el).hasClass("room1") ? "room1" : $(el).hasClass("room2") ? "room2" : $(el).hasClass("room3") ? "room3" : "room",
				title = escape($(el).find("h2").first().text().replace(/\ /g,"-")),
				route = "/" + day + "/" + room + "/" + hour + "/" + title;
			window.location.hash = "#" + route;
		},
		read: function(){
			var target = window.location.hash.split("/"),
				el = $(".day[data-day='"+target[1]+"'] ."+ target[2] +"[data-time='"+target[3]+"']").first();

				if (target.length > 2) {
					$(window).scrollTop( (el.offset().top - 100) );
					el.trigger("click");
				}
		}
	}

	var hourSize = 100;
	$("#schedule .day").each(function(ø,root) {

		var times = $(root).attr("data-time").split("-"),
			daystart = times[0].split(":"),
			daystop = times[1].split(":"),
			daytop = (parseFloat(daystart[0]) + parseFloat(daystart[1]) / 60),
			dayheight = (parseFloat(daystop[0]) + parseFloat(daystop[1]) / 60) - daytop + 1;

			$(root).css("height", (hourSize * dayheight) + "px");

			var side = $("aside", root);
			for (i = parseFloat(daystart[0]); i <= daystop[0] ; i++) {
				// PM:
				side.append("<span class='time' data-time='"+i+":00-"+i+":00'>"+(i>12?(i-12)+" pm":i+" am")+"</span>")
				// Not PM :
				// side.append("<span class='time' data-time='"+i+":00-"+i+":00'>"+i+":00</span>")
			}

		$("[data-time]",root).each(function(ø, el) {
			var times = $(el).attr("data-time").split("-"),
				start = times[0].split(":"),
				stop = times[1].split(":"),
				top = (parseFloat(start[0]) + parseFloat(start[1]) / 60),
				height = (parseFloat(stop[0]) + parseFloat(stop[1]) / 60) - top;

			$(el).css("top", (hourSize * (top - daytop)) + "px");
			$(el).css("height", (hourSize * height) + "px");
		});

	});

	var allTracks = $("#schedule .tracks .track").click(function(e){
		e.preventDefault();

		routes.write(this);

		allTracks.removeClass("active");
		$(this).addClass("active");
		
		$("#details").show();
		$("#details div").removeClass("active");
		$("#"+$(this).attr("data-target")).addClass("active");

		return false;
	});

	// Scroll effect on schedule
	var titles = [];
	titles = $(".day").map(function(i,el){
		return {
			el: $(el).find("header")[0],
			top: $(el).offset().top,
			bottom: $(el).offset().top + $(el).height() - 60
		}
	});

	routes.read();

	$(".mask").click(function() {
		$("#details").hide();
	});

}());

