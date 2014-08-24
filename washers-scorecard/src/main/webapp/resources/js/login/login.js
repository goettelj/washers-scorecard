$(document).ready(function(){
	
	$(".reset-password-link").click(function(e){
		href = $(this).attr("href"),
		$emailInput = $('input[name="email"]'),
		$form = $("form");
		
		e.preventDefault();
		
		if ($emailInput.val() != ""){
			$form.attr("action", href);
			$form.submit();
		} else {
			alert("Please enter your email address.");
		}
		
	});
	
});