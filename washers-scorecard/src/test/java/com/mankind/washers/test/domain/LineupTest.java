package com.mankind.washers.test.domain;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mankind.washers.domain.Lineup;
import com.mankind.washers.domain.LineupPlayer;
import com.mankind.washers.domain.Player;

public class LineupTest {

	private static Logger logger = LoggerFactory.getLogger(LineupTest.class);
	private static Lineup lineup;
	private static Player player1;
	private static Player player2;
	
	@BeforeClass
	public static void setUp() {
		lineup = new Lineup();
		
		player1 = new Player();
		player1.setId(1L);
		player1.setGuestName("Jay");
		lineup.add(new LineupPlayer(player1, 1));
		
		player2 = new Player();
		player2.setId(2L);
		player2.setGuestName("Erin");
		lineup.add(new LineupPlayer(player2, 2));
	}
	
	@Test
	public void testGetPlayerNumber() {
		
		for (LineupPlayer player : lineup.getPlayers()) {
			logger.debug("Player: " + player);
		}
		int playerNumber = lineup.getPlayerNumber(player1);
		
		assertEquals(playerNumber, 1);
		
	}
	
	@Test
	public void testGet() {
		
		Player player = lineup.get(1);
		
		assertEquals(player1, player);
		
	}
	
	@Test
	public void testGetFirstPlayer() {
		
		Player player = lineup.getFirstPlayer();
		
		assertEquals(player1, player);
	}
	
	@Test
	public void testGetNextPlayer() {
		
		Player nextPlayer = lineup.getNextPlayer(player1);
		
		assertEquals(player2, nextPlayer);
		
	}
	
	@Test
	public void testGetNextPlayerWithLastPlayer() {
		
		Player nextPlayer = lineup.getNextPlayer(player2);
		
		assertEquals(player1, nextPlayer);
		
	}

}
