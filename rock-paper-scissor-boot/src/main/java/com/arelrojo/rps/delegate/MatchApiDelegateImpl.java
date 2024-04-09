package com.arelrojo.rps.delegate;

import com.arelrojo.rps.contract.endpoint.model.MetricsDTO;
import com.arelrojo.rps.mapper.MatchMapper;
import com.arelrojo.rps.service.MatchService;
import com.arelrojo.rps.contract.endpoint.api.MatchApiDelegate;
import com.arelrojo.rps.contract.endpoint.model.MatchDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


import java.util.List;
@RequiredArgsConstructor
@Component
public class MatchApiDelegateImpl implements MatchApiDelegate {

    private final MatchService matchService;

    private final MatchMapper matchMapper;

    public ResponseEntity<MatchDTO> createMatch(MatchDTO matchDTO){
        var entity = matchMapper.dtoToEntity(matchDTO);
        entity = matchService.save(entity);
        matchDTO = matchMapper.entityToDTO(entity);
        return ResponseEntity.ok().body(matchDTO);

    }

    public  ResponseEntity<MatchDTO> getMatch(Long id){
        var match = matchService.retrieveById(id);
        var matchDTO = matchMapper.entityToDTO(match);
        return ResponseEntity.ok().body(matchDTO);
    }

    public ResponseEntity<List<MatchDTO>> getMatches(){
        var list = matchService.retrieveMatchs();
        var listDTO = list.stream().map(matchMapper::entityToDTO).toList();
        return ResponseEntity.ok().body(listDTO);
    }

    public ResponseEntity<MetricsDTO> getMetrics(String type){
        MetricsDTO metricsDTO = matchService.retrieveMatchMetrics(type);
        return ResponseEntity.ok().body(metricsDTO);
    }
}
