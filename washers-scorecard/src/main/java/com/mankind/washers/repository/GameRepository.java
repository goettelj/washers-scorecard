package com.mankind.washers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mankind.washers.domain.Game;

public interface GameRepository extends JpaRepository<Game, Long> {

}
