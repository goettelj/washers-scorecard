package com.mankind.washers.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mankind.washers.domain.Attempt;
import com.mankind.washers.domain.AttemptJson;
import com.mankind.washers.domain.Frame;
import com.mankind.washers.domain.Game;
import com.mankind.washers.domain.Player;
import com.mankind.washers.domain.Team;
import com.mankind.washers.service.AttemptService;
import com.mankind.washers.service.FrameService;
import com.mankind.washers.service.GameService;
import com.mankind.washers.service.PlayerService;
import com.mankind.washers.util.GameUtils;

@Controller
@RequestMapping("/frames/{frameId}/attempts")
public class AttemptController {

	private Logger logger = LoggerFactory.getLogger(AttemptController.class);
	
	@Autowired
	private FrameService frameService;
	@Autowired
	private PlayerService playerService;
	@Autowired
	private AttemptService attemptService;
	@Autowired
	private GameService gameService;
	
	@RequestMapping
	public @ResponseBody List<AttemptJson> getAttemptsForFrame(@PathVariable long frameId){
		
		Frame frame = frameService.findOne(frameId);
		
		List<Attempt> frameAttempts = frame.getAttempts();
		
		SortedSet<Attempt> attempts = new TreeSet<Attempt>();
		
		//ADD ANY EXISTING ATTEMPTS
		for (Attempt attempt: frameAttempts) {
			attempts.add(attempt);
		}
		
		//ADD PLACEHOLDERS FOR ANY MISSING ATTEMPTS
		for (int i = 1; i <=8; i++) {
			Attempt attempt = new Attempt();
			attempt.setFrame(frame);
			attempt.setPlayer( i <= 4 ? frame.getFirstPlayer() : frame.getSecondPlayer() );
			attempt.setTeamType( i <= 4 ? frame.getFirstTeam() : frame.getSecondTeam() );
			attempt.setNumber(i);
			attempt.setPlayerNumber( i <= 4 ? i : i - 4);
			attempt.setPoints(null);
			attempts.add(attempt);
		}
		
		List<AttemptJson> jsonAttempts = new ArrayList<AttemptJson>();
		
		for (Attempt attempt : attempts) {
			jsonAttempts.add( new AttemptJson(attempt));
		}
		
		return jsonAttempts;
		
	}
	
	/**
	 * saveFrame
	 * @param jsonAttempts
	 * @param frameId
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST)
	public String saveFrame(
			@RequestParam("jsonData") String jsonData,
			@PathVariable long frameId) throws JsonMappingException, JsonParseException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		List<AttemptJson> jsonAttempts = mapper.readValue(jsonData, new TypeReference<List<AttemptJson>>() {});
		
		int homePoints = 0;
		int guestPoints = 0;
		
		Frame frame = frameService.findOne(frameId);
		
		List<Attempt> attempts = new ArrayList<Attempt>();
		
		for (AttemptJson attemptJson : jsonAttempts) {
			Attempt attempt = new Attempt();
			attempt.setId(attemptJson.getId());
			attempt.setFrame(frame);
			attempt.setNumber(attemptJson.getFrameAttemptNumber());
			attempt.setPlayerNumber(attemptJson.getPlayerAttemptNumber());
			attempt.setPlayer(playerService.findOne(attemptJson.getPlayerId()));
			attempt.setPoints(attemptJson.getPoints());
			attempt.setPositionX(attemptJson.getPositionX());
			attempt.setPositionY(attemptJson.getPositionY());
			attempt.setTeamType(attemptJson.getTeamType());
			attempts.add(attempt);
			
			//ADD POINTS TO SCORE
			if (attempt.getTeamType() == Team.Type.HOME) {
				homePoints += attempt.getPoints();
			} else {
				guestPoints += attempt.getPoints();
			}
		}
		
		attemptService.save(attempts);
		
		frame.setHomePoints(homePoints);
		frame.setGuestPoints(guestPoints);
		
		frameService.save(frame);
		
		//UPDATE GAME SCORE
		Game game = frame.getGame();
		
		game.setHomeScore( game.getHomeScore() + homePoints);
		game.setGuestScore( game.getGuestScore() + guestPoints);
		
		//SET WINNER IF GAME IS COMPLETE
		if ( GameUtils.isGameComplete(game)) {
			
			if ( game.getHomeScore() > game.getGuestScore()) {
				game.setWinner(game.getHomeTeam());
			} else {
				game.setWinner(game.getGuestTeam());
			}
			gameService.save(game);
			return "redirect:/games/" + game.getId() + "/game-over";
		}
		
		gameService.save(game);
		
		//CREATE NEW FRAME
		Frame nextFrame = GameUtils.createNextFrame(frame);
		
		logger.debug("Created next frame: " + nextFrame);
		
		frameService.save(nextFrame);
		
		return "redirect:/games/" + game.getId() + "/frames/" + nextFrame.getNumber() + "/" + nextFrame.getType().toString().toLowerCase();
	}
	
	
}
