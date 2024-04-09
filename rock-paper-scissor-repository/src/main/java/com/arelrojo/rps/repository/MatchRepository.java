package com.arelrojo.rps.repository;

import com.arelrojo.rps.domain.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match,Long> {
    List<Match> findAllByWinner(String winner);
}
