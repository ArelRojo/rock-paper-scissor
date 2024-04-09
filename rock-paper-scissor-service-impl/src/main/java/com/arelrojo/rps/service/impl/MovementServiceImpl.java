package com.arelrojo.rps.service.impl;

import com.arelrojo.rps.contract.endpoint.model.MovementDTO;
import com.arelrojo.rps.domain.Match;
import com.arelrojo.rps.domain.Movement;
import com.arelrojo.rps.domain.RPSMove;
import com.arelrojo.rps.mapper.MovementMapper;
import com.arelrojo.rps.repository.MovementsRepository;
import com.arelrojo.rps.service.MatchService;
import com.arelrojo.rps.service.MovementService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MovementServiceImpl implements MovementService {

    private final MovementsRepository repository;
    private final MovementMapper movementMapper;
    private final MatchService matchService;
    private static final String PLAYER_HUMAN = "HUMAN";
    private static final String PLAYER_ROBOT = "ROBOT";

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

    @Override
    public Movement findByMatchIdAndIsFinal(long matchId, boolean isFinal) {
        return repository.findByMatchIdAndIsFinal(matchId, true);
    }

    public String determineWinner(Movement movement) {
        RPSMove human = movement.getHumanType();
        RPSMove robot = movement.getRobotType();

        switch (human) {
            case PAPER:
                return (robot == RPSMove.ROCK) ? PLAYER_HUMAN : PLAYER_ROBOT;
            case ROCK:
                return (robot == RPSMove.SCISSOR) ? PLAYER_HUMAN : PLAYER_ROBOT;
            case SCISSOR:
                return (robot == RPSMove.PAPER) ? PLAYER_HUMAN : PLAYER_ROBOT;
            default:
                return StringUtils.EMPTY;
        }
    }

    @Override
    public MovementDTO createMovement(MovementDTO movementDTO) {
        Movement movement = movementMapper.dtoToEntity(movementDTO);
        Match match = matchService.retrieveById(movementDTO.getIdMatch());
        movement.setMatch(match);
        movement = completeMovement(movement);
        save(movement);
        if (movement.getIsFinal()) {
            String winner = determineWinner(movement);
            match.setWinner(winner);
        }

        match.setMovements(match.getMovements() + 1);
        matchService.save(match);
        movement.setMoveOrder(match.getMovements());
        movement = save(movement);
        return movementMapper.entityToDTO(movement);
    }

    private static RPSMove getComputerMove() {
        Random random = new Random();
        int randomNumber = random.nextInt(3);

        return RPSMove.values()[randomNumber];
    }


}
