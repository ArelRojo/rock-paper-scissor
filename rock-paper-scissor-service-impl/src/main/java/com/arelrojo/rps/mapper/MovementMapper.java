package com.arelrojo.rps.mapper;


import com.arelrojo.rps.contract.endpoint.model.MovementDTO;
import com.arelrojo.rps.domain.Movement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovementMapper {

    Movement dtoToEntity(MovementDTO item);
    @Mapping(source = "match.id", target = "idMatch")
    MovementDTO entityToDTO(Movement item);

}
