var attemptsData = [];

function WashersField(){
	var _diagram = {},
		_width = 320,
		_height = 280,
		_data = [],
		_svg;
}

function FrameScoreboard(firstTeam){
	var _scoreboard = {},
		_currentTeam = firstTeam,
		_currentAttemptNumber = 1,
		_homeTeamFinished = false,
		_guestTeamFinished = false;
	
	_scoreboard.currentTeam = function(teamType){
		if (teamType){
			_currentTeam = teamType;
		} else {
			return _currentTeam;
		}
	};
	
	_scoreboard.incrementAttemptNumber = function(){
		if (_currentAttemptNumber < 4){
			_currentAttemptNumber++;
		} else {
			
		}
	}
	
	return _scoreboard;
	
}

function render(){
	
	var svg = d3.select("#svg"),
		x = d3.scale.linear()
			.domain([0.00, 1.00])
			.range([0, 320]),
		y = d3.scale.linear()
			.domain([0.00, 1.00])
			.range([0, 280]),
		completedAttempts = [];
	
	//FILTER FOR COMPLETED ATTEMPTS
	completedAttempts = attemptsData.filter(function(attempt){
		return attempt.points != null;
	});
	
	var enter = svg.selectAll("circle.attempt")
		.data(completedAttempts)
		.enter()
			.append("g")
			.attr("transform", function(d){ return "translate(" + x(d.positionX) + "," + y(d.positionY) +")"; })
			.classed("attempt", true)
			.classed("home-attempt", function(d){ return d.teamType === "HOME"; })
			.classed("guest-attempt", function(d){ return d.teamType === "GUEST"; });
	
	enter.append("circle")
			.attr("r", 15);
	
	enter.append("text")
			.text(function(d){ return d.playerAttemptNumber; })
			.attr("dx", -4)
			.attr("dy", 4);
	
}


/**
 * recordAttempt(event, mousePosition)
 */
function recordAttempt(event, mousePosition){
	var frameAttemptNumber = parseInt($(".current-attempt").data("frame-attempt-number")),
		playerAttemptNumber = parseInt($(".current-attempt").data("player-attempt-number")),
		$playerTd = $(".current-attempt").parent("tr").find(".player"),
		teamType = $playerTd.data("team-type")
		playerId = $playerTd.data("player-id"),
		points = 0,	
		attempt = {},
		svgWidth = $("#svg").width(),
		svgHeight = $('#svg').height();
	
	//FIND HOW MANY POINTS SHOULD BE AWARDED
	switch (event.target.id){
	case "svg-background":
		points = 0;
		break;
	case "box-edge":
		points = 2;
		break;
	case "box":
		points = 1;
		break;
	case "cup-edge":
		points = 1;
		break;
	case "cup":
		points = 3;
		break;
	default:
		points = 0;
	}
	
	//CONSTRUCT THE ATTEMPT OBJECT
	attempt = {
		id: null,
		frameId: parseInt($('#frame #id').val()),
		playerId: parseInt(playerId), 
		teamType: teamType,
		frameAttemptNumber: frameAttemptNumber,
		playerAttemptNumber: playerAttemptNumber,
		points: points,
		positionX: mousePosition[0] / svgWidth,
		positionY: mousePosition[1] / svgHeight
	};
	
	attemptsData[frameAttemptNumber - 1] = attempt;
	render();
	renderAttemptsTable();
	updateFrameScore();
	
	$("#undoButton").removeClass("hidden");
	
}

function renderAttemptsTable(){
	
	d3.selectAll("td.attempt-score")
		.data(attemptsData, function(d, i){ return d && d.frameAttemptNumber || i+1; })
		.classed("complete", function(d){ return d.points != null; })
		.text(function(d){ return d.points; });
	
	//HIGHLIGHT CURRENT ATTEMPT
	$(".attempt-score")
		.removeClass("current-attempt")
		.each(function(i){
			if ( ! $.isNumeric($(this).text()) ){
				$(this).addClass("current-attempt");
				return false;
			}
		});
	
	//UPDATE SCORE
	updateFrameScore();
	
	if ($(".current-attempt").length == 0 ){
		$("#frameSaveButton").removeClass("hidden");
	}
	
}

function updateFrameScore(){
	var homeScore = 0;
		guestScore = 0;
		
	for (var i = 0; i < attemptsData.length; i++){
		if (attemptsData[i].teamType == "HOME"){
			homeScore += parseInt(attemptsData[i].points);
		} else {
			guestScore += parseInt(attemptsData[i].points);
		}
	}
	
	$(".frame-score-header .home-score .score").text( $.formatNumber(homeScore, {format: "00", locale: "us"}) );
	$(".frame-score-header .guest-score .score").text( $.formatNumber(guestScore, {format: "00", locale: "us"}) );
	
}

$(document).ready(function(){
	var frameId = $("#frame #id").val(),
		svg = d3.select("#svg");
	
	//DISPLAY ANY EXISTING ATTEMPTS
	d3.json("/washers/frames/" + frameId + "/attempts", function(error, json){
		attemptsData = json;
		renderAttemptsTable();
		render();
	});
	
	//CLICK HANDLERS FOR SVG ELEMENTS
	svg.on("click", function(d, i){
		if ( $(".current-attempt").length == 0){
			$("#saveButton").removeClass("hidden");
			return;
		}
			
		recordAttempt(d3.event, d3.mouse(svg.node()));
	});
	
	$("#frameSaveButton").click(function(e){
		
		e.preventDefault();
		
		$('input[name="jsonData"]').val(JSON.stringify(attemptsData));
		
		$("#attemptsForm").submit();
		
	});
	
});