package com.mankind.washers.service;

import com.mankind.washers.domain.Team;

public interface TeamService {
	
	public Team findOne(long teamId);
	
	public Team save(Team team);
	
}
