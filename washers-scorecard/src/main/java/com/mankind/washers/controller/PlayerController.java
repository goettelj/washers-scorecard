package com.mankind.washers.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mankind.washers.service.PlayerService;

@Controller
@RequestMapping("players")
public class PlayerController {

	@Autowired
	private PlayerService playerService;
	
	private Logger logger = LoggerFactory.getLogger(PlayerService.class);
	
	@RequestMapping("{playerIdent}")
	public String displayPlayerJson(@PathVariable String playerIdent) {
		
		return "player-details";
	}
}
