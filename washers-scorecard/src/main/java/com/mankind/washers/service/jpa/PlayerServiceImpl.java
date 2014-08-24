package com.mankind.washers.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mankind.washers.domain.Player;
import com.mankind.washers.repository.PlayerRepository;
import com.mankind.washers.service.PlayerService;

@Repository
@Transactional
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	private PlayerRepository repos;
	
	@Override
	public Player findOne(long playerId) {
		return repos.findOne(playerId);
	}

	@Override
	public Player save(Player player) {
		return repos.save(player);
	}

}
