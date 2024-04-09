package com.arelrojo.rps.service;

import com.arelrojo.rps.contract.endpoint.model.MovementDTO;
import com.arelrojo.rps.domain.Movement;

import java.util.List;

public interface MovementService {
    Movement save(Movement movement);
    List<Movement> retrieveByMatchId(Long id);
    Movement completeMovement(Movement movement);
    Movement findByMatchIdAndIsFinal(long matchId, boolean isFinal);
    String determineWinner(Movement movement);
    MovementDTO createMovement(MovementDTO movementDTO);

}
