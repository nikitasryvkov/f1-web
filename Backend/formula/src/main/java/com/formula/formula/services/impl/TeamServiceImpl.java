package com.formula.formula.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.formula.formula.exceptions.TeamException;
import com.formula.formula.mappers.TeamMapper;
import com.formula.formula.models.Team;
import com.formula.formula.models.dtos.request.TeamRequestDto;
import com.formula.formula.models.dtos.response.TeamResponseDto;
import com.formula.formula.repositories.TeamRepository;
import com.formula.formula.services.TeamService;

@Service
public class TeamServiceImpl implements TeamService {

    private TeamRepository teamRepository;
    private TeamMapper teamMapper;

    public TeamServiceImpl(TeamRepository teamRepository, TeamMapper teamMapper) {
        this.teamRepository = teamRepository;
        this.teamMapper = teamMapper;
    }

    @Override
    public void create(TeamRequestDto team) {
        List<Team> allTeam = teamRepository.findAll();

        for (Team t : allTeam) {
            if (t.getTitle().equals(team.getTitle())) {
                throw new TeamException.ResourseAlreadyExistsException("Team already exist!");
            }
        }

        teamRepository.save(teamMapper.toTeam(team));
    }

    @Override
    public TeamResponseDto findById(UUID id) {
        return teamMapper.toTeamResponse(teamRepository.findById(id)
                .orElseThrow(TeamException.NotFoundException.supplier("Team {0} was not found!", id)));
    }

    @Override
    public List<TeamResponseDto> findAll() {
        return teamRepository
                .findAll()
                .stream()
                .map(t -> teamMapper.toTeamResponse(t))
                .toList();
    }

    @Override
    public void update(TeamResponseDto team) {
        teamRepository.update(teamMapper.toTeam(team));
    }

    @Override
    public void delete(UUID id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(TeamException.NotFoundException.supplier("Team {0} was not found!", id));

        teamRepository.delete(team);
    }
}
