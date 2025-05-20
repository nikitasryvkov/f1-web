package com.formula.formula.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.formula.formula.models.Team;
import com.formula.formula.models.dtos.request.TeamRequestDto;
import com.formula.formula.models.dtos.response.TeamResponseDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = ReferenceMapper.class)
public interface TeamMapper {

    @Mapping(target = "country", source = "countryId")
    Team toTeam(TeamRequestDto team);

    @Mapping(target = "country", source = "countryId")
    Team toTeam(TeamResponseDto team);

    @Mapping(target = "countryId", source = "country.id")
    TeamRequestDto toTeamRequest(Team team);

    @Mapping(target = "countryId", source = "country.id")
    TeamResponseDto toTeamResponse(Team team);
}
