package com.arelrojo.rps.delegate;

import com.arelrojo.rps.mapper.MatchMapper;
import com.arelrojo.rps.service.MatchService;
import com.arelrojo.rps.contract.endpoint.api.MatchApiDelegate;
import com.arelrojo.rps.contract.endpoint.model.MatchDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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
        return null;
    }

    public ResponseEntity<List<MatchDTO>> getMatches(){
        var list = matchService.retrieveMatchs();
        var listDTO = list.stream().map(m -> matchMapper.entityToDTO(m)).toList();
        return ResponseEntity.ok().body(listDTO);
    }
}
