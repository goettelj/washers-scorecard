package com.mankind.washers.service;

import com.mankind.washers.domain.Frame;

public interface FrameService {

	public Frame findOne(long frameId);
	public Frame save(Frame frame);
}
