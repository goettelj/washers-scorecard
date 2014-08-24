package com.mankind.washers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mankind.washers.domain.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {

}
