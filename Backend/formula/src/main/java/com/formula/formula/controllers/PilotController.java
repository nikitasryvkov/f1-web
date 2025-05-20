package com.formula.formula.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.formula.formula.models.dtos.request.PilotRequestDto;
import com.formula.formula.models.dtos.response.PilotResponseDto;
import com.formula.formula.models.enums.PilotStatusEnum;
import com.formula.formula.services.PilotService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/pilot")
@Tag(name = "Pilot Controller", description = "Pilot management")
public class PilotController {

    private PilotService pilotService;

    public PilotController(PilotService pilotService) {
        this.pilotService = pilotService;
    }

    @PostMapping
    @ApiResponse(responseCode = "500", description = "Pilot already exists!")
    @Operation(summary = "Create pilot")
    public void create(@RequestBody PilotRequestDto pilotDto) {
        pilotService.create(pilotDto);
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "Get pilot by Id")
    public PilotResponseDto get(@PathVariable UUID id) {
        return pilotService.findById(id);
    }

    @GetMapping("/all")
    public List<PilotResponseDto> getAll() {
        return pilotService.findAll();
    }

    @PutMapping("/update")
    @Operation(summary = "Update pilot")
    public void update(@RequestBody PilotResponseDto pilotDto) {
        pilotService.update(pilotDto);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete pilot")
    public void delete(@PathVariable UUID id) {
        pilotService.delete(id);
    }

    @PostMapping("/status/update/{id}")
    @Operation(summary = "Update pilot status")
    public void changeStatus(@PathVariable UUID id, @RequestParam String status) {
        pilotService.changeStatus(id, status);
    }

    @PostMapping("/blocked/{id}")
    @Operation(summary = "Change block status")
    public void changeBlocked(@PathVariable UUID id) {
        pilotService.changeBlocked(id);
    }
}
