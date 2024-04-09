package com.arelrojo.rps.delegate;

import com.arelrojo.rps.contract.endpoint.api.MovementApiDelegate;
import com.arelrojo.rps.contract.endpoint.model.MovementDTO;
import com.arelrojo.rps.domain.RPSMove;
import com.arelrojo.rps.mapper.MovementMapper;
import com.arelrojo.rps.service.MatchService;
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

    private final MatchService matchService;

    public ResponseEntity<MovementDTO> createMovement(MovementDTO movementDTO) {
        var movement = movementMapper.dtoToEntity(movementDTO);
        movement.setMatch(matchService.retrieveById(movementDTO.getIdMatch()));
        //Llamar a quien complete la jugada
        movement = movementService.completeMovement(movement);
        //Guardar movimiento
        movementService.save(movement);
        //Ver si la partida ha terminado
        var match = movement.getMatch();
        if(movement.getIsFinal()){
            //Actualizar datos de la partida con el ganador
                //Encontrar al ganador
            String winner = "";
            var human = movement.getHumanType();
            var robot = movement.getRobotType();

            switch (human){
                case PAPER:
                    if(robot.equals(RPSMove.PAPER)){
                        winner = "HUMAN";
                    }else if(robot.equals(RPSMove.ROCK)){
                        winner = "ROBOT";
                    }
                case ROCK:
                    if(robot.equals((RPSMove.SCISSOR))){
                        winner = "HUMAN";
                    }else if(robot.equals(RPSMove.PAPER)) {
                        winner = "ROBOT";
                    }
                case SCISSOR:
                    if(robot.equals(RPSMove.PAPER)){
                        winner = "HUMAN";
                    }else if(robot.equals(RPSMove.ROCK)){
                        winner = "ROBOT";
                    }
            }

            match.setWinner(winner);

        }
        //Actualizar los movimientos de la partida
        match.setMovements(match.getMovements()+1);
        //Guardar la partida
        matchService.save(match);

        //Devolver los datos del movimiento
        movement.setNum(match.getMovements());
        movement = movementService.save(movement);
        movementDTO = movementMapper.entityToDTO(movement);
        return ResponseEntity.ok().body(movementDTO);
    }

    public ResponseEntity<List<MovementDTO>> getMovementsByMatch(Long idMatch) {
        var list = movementService.retrieveByMatchId(idMatch);
        var listDTO = list.stream().map(movementMapper::entityToDTO).toList();
        return ResponseEntity.ok().body(listDTO);
    }

}
