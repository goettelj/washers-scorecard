package com.mankind.washers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mankind.washers.domain.Attempt;

public interface AttemptRepository extends JpaRepository<Attempt, Long> {

}
