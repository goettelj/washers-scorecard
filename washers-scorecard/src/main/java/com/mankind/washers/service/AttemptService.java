package com.mankind.washers.service;

import java.util.List;

import com.mankind.washers.domain.Attempt;

public interface AttemptService {

	public Iterable<Attempt> save(Iterable<Attempt> attempts);
}
