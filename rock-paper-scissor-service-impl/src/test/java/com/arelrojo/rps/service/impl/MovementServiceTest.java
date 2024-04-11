package com.arelrojo.rps.service.impl;

import com.arelrojo.rps.contract.endpoint.model.MovementDTO;
import com.arelrojo.rps.domain.Match;
import com.arelrojo.rps.domain.Movement;
import com.arelrojo.rps.domain.RPSMove;
import com.arelrojo.rps.mapper.MovementMapper;
import com.arelrojo.rps.repository.MovementsRepository;
import com.arelrojo.rps.service.MatchService;
import com.arelrojo.rps.service.MovementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;


@ExtendWith(MockitoExtension.class)
class MovementServiceTest {

    @Mock
    private MovementsRepository repository;

    @Mock
    private MovementMapper movementMapper;

    @Mock
    private MatchService matchService;

    @Spy
    MovementService movementService = new MovementServiceImpl(null,null,null);

    private static final String PLAYER_ROBOT = "ROBOT";
    private static final String PLAYER_HUMAN = "HUMAN";

    @BeforeEach
    void setup(){
        ReflectionTestUtils.setField(movementService, "repository", repository);
        ReflectionTestUtils.setField(movementService, "movementMapper", movementMapper);
        ReflectionTestUtils.setField(movementService, "matchService", matchService);
    }

    @Test
    void testCompleteMovementDifferentMoves() {
        Movement movement = new Movement();
        movement.setHumanType(RPSMove.ROCK);
        when(movementService.getComputerMove()).thenReturn(RPSMove.SCISSOR);
        Movement result = movementService.completeMovement(movement);
        assertEquals(RPSMove.SCISSOR, result.getRobotType());
        assertTrue(result.getIsFinal());
    }

    @Test
    void testCompleteMovementSameMoves() {
        Movement movement = new Movement();
        movement.setHumanType(RPSMove.ROCK);
        when(movementService.getComputerMove()).thenReturn(RPSMove.ROCK);
        Movement result = movementService.completeMovement(movement);
        assertFalse(result.getIsFinal());
    }

    @Test
    void testDetermineWinner_HumanWinsRockPaper() {
        Movement movement = new Movement();
        movement.setHumanType(RPSMove.ROCK);
        movement.setRobotType(RPSMove.PAPER);
        String winner = movementService.determineWinner(movement);
        assertEquals(PLAYER_ROBOT, winner);
    }

    @Test
    void testDetermineWinner_RobotWinsRockScissor() {
        Movement movement = new Movement();
        movement.setHumanType(RPSMove.ROCK);
        movement.setRobotType(RPSMove.SCISSOR);
        String winner = movementService.determineWinner(movement);
        assertEquals(PLAYER_HUMAN, winner);
    }

    @Test
    void testDetermineWinner_HumanWinsScissorPaper() {
        Movement movement = new Movement();
        movement.setHumanType(RPSMove.SCISSOR);
        movement.setRobotType(RPSMove.PAPER);
        String winner = movementService.determineWinner(movement);
        assertEquals(PLAYER_HUMAN, winner);
    }

    @Test
    void testCreateMovement() {
        MovementDTO movementDTO = new MovementDTO();
        movementDTO.setIdMatch(123L);

        Movement movement = new Movement();
        movement.setIsFinal(true);
        movement.setMoveOrder(1);
        movement.setHumanType(RPSMove.PAPER);
        movement.setRobotType(RPSMove.ROCK);

        Match match = new Match();
        match.setMovements(0);

        when(movementMapper.dtoToEntity(any())).thenReturn(movement);
        when(matchService.retrieveById(anyLong())).thenReturn(match);
        when(repository.save(any(Movement.class))).thenReturn(movement);

        movementService.createMovement(movementDTO);

        verify(repository, times(2)).save(any(Movement.class));
        verify(matchService, times(1)).save(match);
        verify(matchService, times(1)).retrieveById(123L);
    }

}
