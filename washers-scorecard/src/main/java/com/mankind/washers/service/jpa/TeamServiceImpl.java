package com.mankind.washers.service.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mankind.washers.domain.Team;
import com.mankind.washers.repository.TeamRepository;
import com.mankind.washers.service.TeamService;

@Repository
@Transactional
public class TeamServiceImpl implements TeamService {

	private Logger logger = LoggerFactory.getLogger(TeamServiceImpl.class);
	
	@Autowired
	private TeamRepository repos;
	
	@Override
	public Team findOne(long teamId) {
		return repos.findOne(teamId);
	}

	@Override
	public Team save(Team team) {
		return repos.save(team);
	}

}
