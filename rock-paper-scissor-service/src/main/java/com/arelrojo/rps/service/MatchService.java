package com.arelrojo.rps.service;

import com.arelrojo.rps.contract.endpoint.model.MetricsDTO;
import com.arelrojo.rps.domain.Match;
import com.arelrojo.rps.domain.RPSMove;

import java.util.List;

public interface MatchService {

    Match save(Match match);

    List<Match> retrieveMatches();

    Match retrieveById(Long id);

    MetricsDTO retrieveMatchMetrics(String type);
    int countMoves(List<Match> matches, String type, RPSMove moveType);
}
