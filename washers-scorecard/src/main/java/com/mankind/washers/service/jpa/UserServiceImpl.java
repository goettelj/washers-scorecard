package com.mankind.washers.service.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mankind.washers.domain.User;
import com.mankind.washers.repository.UserRepository;
import com.mankind.washers.service.UserService;

@Repository
@Transactional
public class UserServiceImpl implements UserService {

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserRepository repos;
	
	@Override
	public User findOne(long userId) {
		return repos.findOne(userId);
	}

	@Override
	public User findByEmail(String email) {
		return repos.findByEmail(email);
	}

	@Override
	public User findByIdent(String ident) {
		return repos.findByIdent(ident);
	}

	@Override
	@Transactional(readOnly=false)
	public User save(User user) {
		return repos.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException {
		
		logger.debug("Trying to load user by username: " + email);
		User user = findByEmail(email);
		
		if (user == null) {
			throw new UsernameNotFoundException("Could not find user with email: " + email);
		}
		
		return user;
	}

}
