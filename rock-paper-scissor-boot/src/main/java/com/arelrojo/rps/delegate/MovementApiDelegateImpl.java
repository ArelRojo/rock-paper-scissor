package com.arelrojo.rps.delegate;

import com.arelrojo.rps.contract.endpoint.api.MovementApiDelegate;
import com.arelrojo.rps.contract.endpoint.model.MatchDTO;
import com.arelrojo.rps.contract.endpoint.model.MovementDTO;
import com.arelrojo.rps.mapper.MovementMapper;
import com.arelrojo.rps.service.MovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class MovementApiDelegateImpl implements MovementApiDelegate {

    private final MovementService movementService;

    private final MovementMapper movementMapper;

    public ResponseEntity<MovementDTO> createMovement(MovementDTO movementDTO) {
        var entity = movementMapper.dtoToEntity(movementDTO);
        entity = movementService.save(entity);
        movementDTO = movementMapper.entityToDTO(entity);
        return ResponseEntity.ok().body(movementDTO);
    }

    public ResponseEntity<List<MovementDTO>> getMovementsByMatch(Long idMatch) {
        var list = movementService.retrieveByMatchId(idMatch);
        var listDTO = list.stream().map(movementMapper::entityToDTO).toList();
        return ResponseEntity.ok().body(listDTO);
    }

}
