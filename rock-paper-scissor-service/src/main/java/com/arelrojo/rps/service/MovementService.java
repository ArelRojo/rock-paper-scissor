package com.arelrojo.rps.service;

import com.arelrojo.rps.domain.Movement;

import java.util.List;

public interface MovementService {
    Movement save(Movement movement);
    List<Movement> retrieveByMatchId(Long id);
}
