package com.arelrojo.rps.repository;

import com.arelrojo.rps.domain.Movement;
import com.arelrojo.rps.domain.RPSMove;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovementsRepository extends JpaRepository<Movement,Long> {
    List<Movement> findByMatchId(Long matchId);
    Movement findByMatchIdAndIsFinal(Long matchId, boolean isFinal);
}
