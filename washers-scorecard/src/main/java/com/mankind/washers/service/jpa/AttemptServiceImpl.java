package com.mankind.washers.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mankind.washers.domain.Attempt;
import com.mankind.washers.repository.AttemptRepository;
import com.mankind.washers.service.AttemptService;

@Repository
@Transactional
public class AttemptServiceImpl implements AttemptService {

	@Autowired
	private AttemptRepository repos;

	@Override
	@Transactional(readOnly=false)
	public Iterable<Attempt> save(Iterable<Attempt> attempts) {
		return repos.save(attempts);
	}
	
	

}
