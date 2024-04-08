package com.arelrojo.rps.mapper;

import com.arelrojo.rps.contract.endpoint.model.MatchDTO;
import com.arelrojo.rps.domain.Match;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MatchMapper {
    MatchMapper INSTANCE = Mappers.getMapper(MatchMapper.class);

    Match dtoToEntity(MatchDTO item);
    MatchDTO entityToDTO(Match item);
}
