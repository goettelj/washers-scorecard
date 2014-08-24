package com.mankind.washers.service;

import com.mankind.washers.domain.Game;

public interface GameService {

	public Game findOne(long gameId);
	
	public Game save(Game game);
	
	
}
