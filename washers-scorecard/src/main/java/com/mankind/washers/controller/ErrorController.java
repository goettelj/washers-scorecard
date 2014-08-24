package com.mankind.washers.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error-handler")
public class ErrorController {

	private Logger logger = LoggerFactory.getLogger(ErrorController.class);
	
	@RequestMapping
	public String displayErrorPage(Exception e, Model uiModel) {
		
		logger.debug("Handling error.");
		
		String msg = "";
		
		if (e != null) {
			msg = e.getMessage();
		} else {
			msg = "Unexpected Error.  See log.";
		}
		
		uiModel.addAttribute("errorMessage", msg);
		
		return "error";
		
	}
	
}
