package com.mankind.washers.controller;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.mankind.washers.domain.User;
import com.mankind.washers.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@RequestMapping(value="/reset-password", method=RequestMethod.POST)
	public String resetPassword(@RequestParam String email, Model uiModel) {
		Calendar cal = Calendar.getInstance();
		
		User user = userService.findByEmail(email);
		
		if (user == null) {
			uiModel.addAttribute("errorMessage", "Email provided does not match our records.");
			return "login";
		}
		
		logger.info("Resetting password for " + email);
		
		//String password = RandomStringUtils.randomAlphanumeric(10);
		
		String password = "g";
		
		logger.debug("New random password: " + password);
		
		String encoded = passwordEncoder.encode(password);
		
		user.setPassword(encoded);
		cal.setTime(new Date());
		cal.add(Calendar.YEAR, 1);
		user.setPasswordExpirationDate(cal.getTime());
		
		userService.save(user);
		
		logger.debug("Password encoded: " + encoded);
		
		logger.debug("Encoded length: " + encoded.length());
		
		uiModel.addAttribute("successMessage", "Successfully reset password to " + password);
		
		return "login";
		
	}
	
}
