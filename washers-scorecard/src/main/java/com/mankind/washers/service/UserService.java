package com.mankind.washers.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.mankind.washers.domain.User;

public interface UserService extends UserDetailsService {

	public User findOne(long userId);
	public User findByEmail(String email);
	public User findByIdent(String ident);

	public User save(User user);
	
	
	
}
