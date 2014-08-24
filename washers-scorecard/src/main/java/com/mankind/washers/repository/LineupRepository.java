package com.mankind.washers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mankind.washers.domain.Lineup;

public interface LineupRepository extends JpaRepository<Lineup, Long> {

}
