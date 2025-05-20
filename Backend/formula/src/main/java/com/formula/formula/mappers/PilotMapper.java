package com.formula.formula.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.formula.formula.models.Pilot;
import com.formula.formula.models.dtos.request.PilotRequestDto;
import com.formula.formula.models.dtos.response.PilotResponseDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = ReferenceMapper.class)
public interface PilotMapper {

    @Mapping(target = "team", source = "teamId")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "country", source = "countryId")
    Pilot toPilot(PilotResponseDto pilot);

    @Mapping(target = "team", source = "teamId")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "country", source = "countryId")
    Pilot toPilot(PilotRequestDto pilot);

    @Mapping(target = "teamId", source = "team.id")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "countryId", source = "country.id")
    PilotRequestDto toPilotRequest(Pilot pilot);

    @Mapping(target = "teamId", source = "team.id")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "countryId", source = "country.id")
    PilotResponseDto toPilotResponse(Pilot pilot);
}
