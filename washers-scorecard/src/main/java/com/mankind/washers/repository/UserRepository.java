package com.mankind.washers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mankind.washers.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmail(String email);
	public User findByIdent(String ident);
}
