package com.arelrojo.rps.service.impl;

import com.arelrojo.rps.domain.Movement;
import com.arelrojo.rps.domain.RPSMove;
import com.arelrojo.rps.repository.MovementsRepository;
import com.arelrojo.rps.service.MovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

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

    @Override
    public Movement completeMovement(Movement movement) {
        RPSMove robotMovement = getComputerMove();
        movement.setRobotType(robotMovement);
        if(!robotMovement.equals(movement.getHumanType())){
            movement.setIsFinal(true);
        }else{
            movement.setIsFinal(false);
        }
        return movement;
    }

    private static RPSMove getComputerMove() {
        Random random = new Random();
        int randomNumber = random.nextInt(3);

        return RPSMove.values()[randomNumber];
    }
}
