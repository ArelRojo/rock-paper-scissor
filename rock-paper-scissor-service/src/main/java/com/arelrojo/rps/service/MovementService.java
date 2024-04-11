package com.arelrojo.rps.service;

import com.arelrojo.rps.contract.endpoint.model.MovementDTO;
import com.arelrojo.rps.domain.Movement;
import com.arelrojo.rps.domain.RPSMove;

import java.util.List;

public interface MovementService {
    Movement save(Movement movement);
    List<Movement> retrieveByMatchId(Long id);
    Movement completeMovement(Movement movement);
    String determineWinner(Movement movement);
    MovementDTO createMovement(MovementDTO movementDTO);
    RPSMove getComputerMove();

}
