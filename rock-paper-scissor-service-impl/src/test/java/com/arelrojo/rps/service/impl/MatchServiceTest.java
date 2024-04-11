package com.arelrojo.rps.service.impl;

import com.arelrojo.rps.contract.endpoint.model.MetricsDTO;
import com.arelrojo.rps.domain.Match;
import com.arelrojo.rps.domain.Movement;
import com.arelrojo.rps.domain.RPSMove;
import com.arelrojo.rps.repository.MatchRepository;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MatchServiceTest {
    @Mock
    private MovementsRepository movementsRepositoryMock;
    @Mock
    private MatchRepository matchRepositoryMock;
    private static final String PLAYER_HUMAN = "HUMAN";

    @Spy
    MatchService matchService = new MatchServiceImpl(null,null);

    @BeforeEach
    void setup(){
        ReflectionTestUtils.setField(matchService, "repository", matchRepositoryMock);
        ReflectionTestUtils.setField(matchService, "movementsRepository", movementsRepositoryMock
        );
    }

    @Test
    public void testRetrieveMatchMetrics() {
        List<Match> matches = IntStream.range(0, 5)
                .mapToObj(i -> {
                    Match match = new Match();
                    match.setWinner(RPSMove.ROCK.getValue());
                    Movement movement = new Movement();
                    movement.setHumanType(RPSMove.ROCK);
                    movement.setIsFinal(true);
                    movement.setMatch(match);
                    return match;
                })
                .collect(Collectors.toList());

        when(matchRepositoryMock.findAllByWinner(anyString())).thenReturn(matches);
        when(matchRepositoryMock.count()).thenReturn((long) matches.size());
        doReturn(5).when(matchService).countMoves(matches,PLAYER_HUMAN,RPSMove.ROCK);
        MetricsDTO metricsDTO = matchService.retrieveMatchMetrics(PLAYER_HUMAN);

        assertEquals(100.0, metricsDTO.getWins());
        assertEquals(100.0, metricsDTO.getRock());
        assertEquals(0.0, metricsDTO.getPaper());
        assertEquals(0.0, metricsDTO.getScissor());
    }


    @Test
    void testCountMoves() {
        Movement movement1 = new Movement();
        movement1.setHumanType(RPSMove.ROCK);
        List<Match> matches = Arrays.asList(
                new Match(1L,PLAYER_HUMAN,1),
                new Match(2L,PLAYER_HUMAN,1),
                new Match(3L,PLAYER_HUMAN,1)
        );

        when(movementsRepositoryMock.findByMatchIdAndIsFinal(1L, true))
                .thenReturn(movement1);
        int rockCount = matchService.countMoves(matches, PLAYER_HUMAN, RPSMove.ROCK);
        assertEquals(1, rockCount);
    }

}
