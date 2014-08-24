package com.mankind.washers.service;

import com.mankind.washers.domain.Player;

public interface PlayerService {

	public Player findOne(long playerId);
	
	public Player save(Player player);
}
