package com.formula.formula.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formula.formula.models.dtos.request.TeamRequestDto;
import com.formula.formula.models.dtos.response.TeamResponseDto;
import com.formula.formula.services.TeamService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/team")
@Tag(name = "Team Controller", description = "Team Managment")
public class TeamController {

    private TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping()
    @ApiResponse(responseCode = "500", description = "Team with that name already exists!")
    @Operation(summary = "Create team")
    public void create(@RequestBody TeamRequestDto teamDto) {
        teamService.create(teamDto);
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "Get team by Id")
    public TeamResponseDto get(@PathVariable UUID id) {
        return teamService.findById(id);
    }

    @GetMapping("/all")
    public List<TeamResponseDto> getAll() {
        return teamService.findAll();
    }

    @PutMapping("/update")
    @Operation(summary = "Update team")
    public void update(@RequestBody TeamResponseDto teamDto) {
        teamService.update(teamDto);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete team")
    public void delete(@PathVariable UUID id) {
        teamService.delete(id);
    }
}
