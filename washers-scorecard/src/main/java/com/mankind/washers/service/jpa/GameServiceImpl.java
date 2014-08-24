package com.mankind.washers.service.jpa;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mankind.washers.domain.Game;
import com.mankind.washers.repository.GameRepository;
import com.mankind.washers.service.GameService;

@Repository
@Transactional
public class GameServiceImpl implements GameService {

	@Autowired
	private GameRepository repos;
	
	@Override
	public Game findOne(long gameId) {
		Game game = repos.findOne(gameId);
		
		if (game == null) {
			throw new EntityNotFoundException("Could not find Game with Id: " + gameId);
		}
		
		return game;
		
	}

	@Override
	public Game save(Game game) {
		return repos.save(game);
	}

}
