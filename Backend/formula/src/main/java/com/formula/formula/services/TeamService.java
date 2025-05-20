package com.formula.formula.services;

import java.util.List;
import java.util.UUID;

import com.formula.formula.models.dtos.request.TeamRequestDto;
import com.formula.formula.models.dtos.response.TeamResponseDto;

public interface TeamService {

    void create(TeamRequestDto teamDto);

    TeamResponseDto findById(UUID id);

    List<TeamResponseDto> findAll();

    void update(TeamResponseDto teamDto);

    void delete(UUID id);
}
