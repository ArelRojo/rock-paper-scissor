package com.arelrojo.rps.repository;

import com.arelrojo.rps.domain.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match,Long> {
}
