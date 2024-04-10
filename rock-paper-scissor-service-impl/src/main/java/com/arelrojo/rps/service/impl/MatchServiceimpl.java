package com.arelrojo.rps.service.impl;

import com.arelrojo.rps.contract.endpoint.model.MetricsDTO;
import com.arelrojo.rps.domain.Match;
import com.arelrojo.rps.domain.Movement;
import com.arelrojo.rps.domain.RPSMove;
import com.arelrojo.rps.repository.MatchRepository;
import com.arelrojo.rps.repository.MovementsRepository;
import com.arelrojo.rps.service.MatchService;
import com.arelrojo.rps.service.MovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchServiceimpl implements MatchService {

    private final MatchRepository repository;
    private final MovementsRepository movementsRepository;
    private static final String PLAYER_HUMAN = "HUMAN";
    private static final String PLAYER_ROBOT = "ROBOT";
    @Override
    public Match save(Match match) {
        return repository.save(match);
    }

    @Override
    public List<Match> retrieveMatchs() {
        return repository.findAll();
    }

    @Override
    public Match retrieveById(Long id) {
        Optional<Match> matchOptional = repository.findById(id);
        return matchOptional.orElse(null);
    }

    @Override
    public MetricsDTO retrieveMatchMetrics(String type) {
        MetricsDTO dto = new MetricsDTO();

        List<Match> matches = repository.findAllByWinner(type.toUpperCase());
        int totalMatches = (int) repository.count();
        double winPercentage = (double) matches.size() / totalMatches * 100;

        double rockCount = (double) countMoves(matches, type, RPSMove.ROCK) / matches.size() * 100;
        double paperCount = (double) countMoves(matches, type, RPSMove.PAPER) / matches.size() * 100;
        double scissorCount = (double) countMoves(matches, type, RPSMove.SCISSOR) / matches.size() * 100;

        dto.setRock(rockCount);
        dto.setPaper(paperCount);
        dto.setScissor(scissorCount);
        dto.setWins(winPercentage);
        return dto;
    }

    private int countMoves(List<Match> matches, String type, RPSMove moveType) {
        return (int) matches.stream()
                .map(Match::getId)
                .map(id -> movementsRepository.findByMatchIdAndIsFinal(id, true))
                .filter(m -> m != null && (type.equalsIgnoreCase(PLAYER_HUMAN) || type.equalsIgnoreCase(PLAYER_ROBOT)))
                .filter(m -> type.equalsIgnoreCase(PLAYER_HUMAN) ? m.getHumanType() == moveType : m.getRobotType() == moveType)
                .count();
    }


}
