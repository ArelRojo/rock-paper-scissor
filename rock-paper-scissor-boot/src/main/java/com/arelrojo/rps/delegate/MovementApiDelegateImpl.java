package com.arelrojo.rps.delegate;

import com.arelrojo.rps.contract.endpoint.api.MovementApiDelegate;
import com.arelrojo.rps.contract.endpoint.model.MovementDTO;
import com.arelrojo.rps.domain.Match;
import com.arelrojo.rps.domain.Movement;
import com.arelrojo.rps.domain.RPSMove;
import com.arelrojo.rps.mapper.MovementMapper;
import com.arelrojo.rps.service.MatchService;
import com.arelrojo.rps.service.MovementService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class MovementApiDelegateImpl implements MovementApiDelegate {

    private final MovementService movementService;

    private final MovementMapper movementMapper;

    private final MatchService matchService;

    public ResponseEntity<MovementDTO> createMovement(MovementDTO movementDTO) {
        movementDTO = movementService.createMovement(movementDTO);
        return ResponseEntity.ok().body(movementDTO);
    }


    public ResponseEntity<List<MovementDTO>> getMovementsByMatch(Long idMatch) {
        var list = movementService.retrieveByMatchId(idMatch);
        var listDTO = list.stream().map(movementMapper::entityToDTO).toList();
        return ResponseEntity.ok().body(listDTO);
    }

}
