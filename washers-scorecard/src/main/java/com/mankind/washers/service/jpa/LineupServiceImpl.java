package com.mankind.washers.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mankind.washers.domain.Lineup;
import com.mankind.washers.repository.LineupRepository;
import com.mankind.washers.service.LineupService;

@Repository
@Transactional
public class LineupServiceImpl implements LineupService {

	@Autowired
	private LineupRepository repos;
	
	@Override
	public Lineup save(Lineup lineup) {
		return repos.save(lineup);
	}

}
