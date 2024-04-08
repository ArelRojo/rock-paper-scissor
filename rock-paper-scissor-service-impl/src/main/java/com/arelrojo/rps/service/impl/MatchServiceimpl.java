package com.arelrojo.rps.service.impl;

import com.arelrojo.rps.domain.Match;
import com.arelrojo.rps.repository.MatchRepository;
import com.arelrojo.rps.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MatchServiceimpl implements MatchService {

    private final MatchRepository repository;
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
}
