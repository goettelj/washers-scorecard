$(document).ready(function(){
	
	$(".btn-number-of-players").click(function(e){
		var cssClass = "on";
		
		e.preventDefault();
		
		$(".btn-number-of-players").removeClass(cssClass);
		
		$(this).addClass(cssClass);
		
		if ( $(this).text() == "2" ){
			$("#numberOfPlayers2").prop("checked", true);
			$('.player-2').hide();
		} else {
			$("#numberOfPlayers4").prop("checked", true);
			$('.player-2').show();
		}
		
	});
	
});