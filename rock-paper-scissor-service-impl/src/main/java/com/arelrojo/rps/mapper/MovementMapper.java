package com.arelrojo.rps.mapper;


import com.arelrojo.rps.contract.endpoint.model.MovementDTO;
import com.arelrojo.rps.domain.Movement;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovementMapper {

    Movement dtoToEntity(MovementDTO item);
    MovementDTO entityToDTO(Movement item);
}
