package com.mankind.washers.controller;

import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mankind.washers.SessionUser;
import com.mankind.washers.domain.Attempt;
import com.mankind.washers.domain.Frame;
import com.mankind.washers.domain.Game;
import com.mankind.washers.domain.Lineup;
import com.mankind.washers.domain.LineupPlayer;
import com.mankind.washers.domain.Player;
import com.mankind.washers.domain.Team;
import com.mankind.washers.domain.User;
import com.mankind.washers.service.FrameService;
import com.mankind.washers.service.GameService;
import com.mankind.washers.service.LineupService;
import com.mankind.washers.service.PlayerService;
import com.mankind.washers.service.TeamService;

@Controller
@RequestMapping("games")
public class GameController {

	private Logger logger = LoggerFactory.getLogger(GameController.class);
	
	@Autowired
	private PlayerService playerService;
	@Autowired
	private TeamService teamService;
	@Autowired
	private GameService gameService;
	@Autowired
	private LineupService lineupService;
	@Autowired
	private FrameService frameService;
	
	/**
	 * displaySetupScreen
	 * @param user
	 * @param uiModel
	 * @return
	 */
	@RequestMapping(value="setup")
	public String displaySetupScreen(@SessionUser User user, Model uiModel) {
		
		Game game = new Game();
		//ADD HOME PLAYERS
		game.getHomeTeam().getPlayers().add(new Player());
		game.getHomeTeam().getPlayers().add(new Player());
		//ADD GUEST PLAYERS
		game.getGuestTeam().getPlayers().add(new Player());
		game.getGuestTeam().getPlayers().add(new Player());
		
		uiModel.addAttribute("game", game);
		
		return "game-setup";
	}
	
	/**
	 * submitSetupScreen
	 * @param game
	 * @param bindingResult
	 * @param numberOfPlayers
	 * @return
	 */
	@RequestMapping(value="", method=RequestMethod.POST)
	public String submitSetupScreen(
			@ModelAttribute Game game, 
			BindingResult bindingResult,
			@RequestParam int numberOfPlayers) {
		 
		//HOME TEAM NAME
		String homeTeamName = createDefaultTeamName(game.getHomeTeam().getPlayers(), numberOfPlayers / 2);
		game.getHomeTeam().setName(homeTeamName);
		
		
		//GUEST TEAM NAME
		String guestTeamName = createDefaultTeamName(game.getGuestTeam().getPlayers(), numberOfPlayers /2);
		game.getGuestTeam().setName(guestTeamName);
		
		
		logger.debug("Saving " + game);
		
		game = gameService.save(game);
		
		//CREATE DEFAULT LINEUPS
		game.setHomeLineup(new Lineup(game, game.getHomeTeam()));
		game.setGuestLineup(new Lineup(game, game.getGuestTeam()));
		
		game = gameService.save(game);
		
		return "redirect:games/" + game.getId() + "/lineups";
	}
	
	/**
	 * displayGameSummary
	 * @param gameId
	 * @param uiModel
	 * @return
	 */
	@RequestMapping(value="{gameId}", method=RequestMethod.GET)
	public String displayGameSummary(
			@PathVariable long gameId,
			Model uiModel) {
		
		Game game = gameService.findOne(gameId);
		uiModel.addAttribute(game);
		
		return "game-summary";
	}
	
	/**
	 * createDefaultTeamName
	 * @param players
	 * @param numberOfPlayers
	 * @return
	 */
	private String createDefaultTeamName(List<Player> players, int numberOfPlayers) {
		String name = "";
		int i = 0;
		for (Player player : players) {
			i++;
			if ( i <= numberOfPlayers) {
				name += " & " + player.getName();
			}
		}
		if (name.length() >= 3) {
			name = name.substring(3);
		}
		
		return name;
	}
	
	/**
	 * displayLineupScreen
	 * @param gameId
	 * @param uiModel
	 * @return
	 */
	@RequestMapping(value="{gameId}/lineups", method=RequestMethod.GET)
	public String displayLineupScreen(@PathVariable long gameId, Model uiModel) {
		
		Game game = gameService.findOne(gameId);
		uiModel.addAttribute("game", game);
		
		int homeSize = game.getHomeLineup().getPlayers().size();
		LineupPlayer[] homeLineup = game.getHomeLineup().getPlayers().toArray(new LineupPlayer[homeSize]);
		uiModel.addAttribute("homeLineupPlayers", homeLineup);
		
		LineupPlayer[] guestLineup = game.getGuestLineup().getPlayers().toArray(new LineupPlayer[homeSize]);
		uiModel.addAttribute("guestLineupPlayers", guestLineup);
		
		return "lineups";
	}
	
	@RequestMapping(value="{gameId}/lineups", method=RequestMethod.POST)
	public String submitLineups(@PathVariable long gameId) {
		
		//TODO Save lineups
		
		//CREATE TOP OF FIRST FRAME
		Game game = gameService.findOne(gameId);
		
		Player homePlayer = game.getHomeLineup().getFirstPlayer();
		Player guestPlayer = game.getGuestLineup().getFirstPlayer();
		
		Frame frame = new Frame(game, 1, Frame.Type.TOP, Team.Type.GUEST);
		frame.setHomePlayer(homePlayer);
		frame.setGuestPlayer(guestPlayer);
		
		frameService.save(frame);
		
		return "redirect:games/" + game.getId() + "/frames/1/top";
		
	}
	
	/**
	 * displayFrame
	 * @param gameId
	 * @param frameNumber
	 * @param frameTypeName
	 * @param uiModel
	 * @return
	 */
	@RequestMapping(value="{gameId}/frames/{frameNumber}/{frameTypeName}", method=RequestMethod.GET)
	public String displayFrame(@PathVariable long gameId,
			@PathVariable int frameNumber,
			@PathVariable String frameTypeName,
			Model uiModel) {
		
		Game game = gameService.findOne(gameId);
		uiModel.addAttribute("game", game);
		
		Frame.Type frameType = Frame.Type.valueOf(frameTypeName.toUpperCase());
		Frame frame = game.getFrame(frameNumber, frameType);
		
		List<Attempt> homeAttempts = frame.getAttemptsForPlayer(frame.getHomePlayer());
		List<Attempt> guestAttempts = frame.getAttemptsForPlayer(frame.getGuestPlayer());
		
		Collections.sort(homeAttempts);
		Collections.sort(guestAttempts);
		
		uiModel.addAttribute("homeAttempts", homeAttempts);
		uiModel.addAttribute("guestAttempts", guestAttempts);
		
		uiModel.addAttribute("frame", frame);
		
		return "frame";
	}
	
	@RequestMapping(value="{gameId}/game-over", method=RequestMethod.GET)
	public String displayGameOverScreen(@PathVariable long gameId, Model uiModel) {
		
		Game game = gameService.findOne(gameId);
		
		uiModel.addAttribute("game", game);
		
		return "game-over";
		
	}
}
