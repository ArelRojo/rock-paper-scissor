package com.arelrojo.rps.service;

import com.arelrojo.rps.contract.endpoint.model.MetricsDTO;
import com.arelrojo.rps.domain.Match;

import java.util.List;

public interface MatchService {

    Match save(Match match);

    List<Match> retrieveMatchs();

    Match retrieveById(Long id);

    MetricsDTO retrieveMatchMetrics(String type);
}
