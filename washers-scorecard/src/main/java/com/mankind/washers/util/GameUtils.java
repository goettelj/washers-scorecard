package com.mankind.washers.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mankind.washers.domain.Frame;
import com.mankind.washers.domain.Game;
import com.mankind.washers.domain.Frame.Type;
import com.mankind.washers.domain.Player;
import com.mankind.washers.domain.Team;

public class GameUtils {

	private static Logger logger = LoggerFactory.getLogger(GameUtils.class);
	
	public static Frame createFirstFrame(Game game) {
		
		Frame frame = new Frame();
		frame.setNumber(1);
		frame.setType(Type.TOP);
		frame.setHomePlayer(game.getHomeLineup().getFirstPlayer());
		frame.setGuestPlayer(game.getGuestLineup().getFirstPlayer());
		frame.setFirstTeam(Team.Type.GUEST);
		
		return frame;
		
	}
	
	public static Frame createNextFrame(Frame frame) {
			
		Frame nextFrame = new Frame();
		
		//IF TOP OF FRAME, ADD BOTTOM
		if (frame.getType() == Frame.Type.TOP) {
			nextFrame.setNumber(frame.getNumber());
			nextFrame.setType(Frame.Type.BOTTOM);
			
		} else {
		//IF BOTTOM, ADD NEXT FRAME
			nextFrame.setNumber(frame.getNumber() + 1);
			nextFrame.setType(Frame.Type.TOP);
		}
		
		//SET PLAYERS
		Player homePlayer = frame.getGame().getHomeLineup().getNextPlayer(frame.getHomePlayer());
		Player guestPlayer = frame.getGame().getGuestLineup().getNextPlayer(frame.getGuestPlayer());
		nextFrame.setHomePlayer(homePlayer);
		nextFrame.setGuestPlayer(guestPlayer);
		
		//FIRST TEAM
		if (frame.getHomePoints() > frame.getGuestPoints()) {
			nextFrame.setFirstTeam(Team.Type.HOME);
		} else if (frame.getHomePoints() < frame.getGuestPoints()) {
			nextFrame.setFirstTeam(Team.Type.GUEST);
		} else {
			//TIED (FOLLOW YOUR PARTNER, SO LAST TEAM OF PREVIOUS ROUND GOES FIRST)
			if (frame.getFirstTeam() == Team.Type.HOME) {
				nextFrame.setFirstTeam(Team.Type.GUEST);
			} else {
				nextFrame.setFirstTeam(Team.Type.HOME);
			}
		}
		
		//GAME
		nextFrame.setGame(frame.getGame());
		
		logger.debug("Created next frame: " + nextFrame);
		
		return nextFrame;
	}
	
	public static boolean isGameComplete(Game game) {
	
		if ( game.getHomeScore() >= game.getObjectiveScore() || game.getGuestScore() >= game.getObjectiveScore()) {
			//CHECK IF GAME IS WIN-BY-TWO
			int differential = Math.abs(game.getGuestScore() - game.getHomeScore());
			
			if ( game.isWinByTwo() && differential < 2) {
				return false;
			}
			return true;
		}
		
		return false;
		
	}
	
}
