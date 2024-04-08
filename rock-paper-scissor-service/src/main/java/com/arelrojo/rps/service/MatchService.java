package com.arelrojo.rps.service;

import com.arelrojo.rps.domain.Match;

import java.util.List;

public interface MatchService {

    public Match save(Match match);

    public List<Match> retrieveMatchs();
}
