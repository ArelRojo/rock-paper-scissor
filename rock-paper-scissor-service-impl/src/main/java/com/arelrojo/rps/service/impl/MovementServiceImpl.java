package com.arelrojo.rps.service.impl;

import com.arelrojo.rps.domain.Movement;
import com.arelrojo.rps.repository.MovementsRepository;
import com.arelrojo.rps.service.MovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovementServiceImpl implements MovementService {

    private final MovementsRepository repository;

    @Override
    public Movement save(Movement movement) {
        return repository.save(movement);
    }

    @Override
    public List<Movement> retrieveByMatchId(Long matchId) {
        return repository.findByMatchId(matchId);
    }
}
