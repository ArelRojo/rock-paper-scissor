package com.arelrojo.rps.repository;

import com.arelrojo.rps.domain.Movement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovementsRepository extends JpaRepository<Movement,Long> {
    List<Movement> findByMatchId(Long matchId);
}
