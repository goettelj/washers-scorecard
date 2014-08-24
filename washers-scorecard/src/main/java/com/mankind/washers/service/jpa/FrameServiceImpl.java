package com.mankind.washers.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mankind.washers.domain.Frame;
import com.mankind.washers.repository.FrameRepository;
import com.mankind.washers.service.FrameService;

@Repository
@Transactional
public class FrameServiceImpl implements FrameService {

	@Autowired
	private FrameRepository repos;
	
	@Override
	public Frame save(Frame frame) {
		return repos.save(frame);
	}

	@Override
	public Frame findOne(long frameId) {
		return repos.findOne(frameId);
	}

}
