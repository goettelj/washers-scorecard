package com.mankind.washers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mankind.washers.domain.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {

}
