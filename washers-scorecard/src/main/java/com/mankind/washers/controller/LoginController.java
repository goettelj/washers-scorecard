package com.mankind.washers.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {

	private Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String displayLoginScreen(Model uiModel) {
		
		return "login";
		
	}
	
	@RequestMapping(value="", method=RequestMethod.GET, params="error")
	public String handleError(@RequestParam String error) {
		
		logger.debug(error);
		
		return "login";
		
	}
	
}
