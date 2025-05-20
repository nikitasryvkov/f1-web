package com.formula.formula.services;

import java.util.List;
import java.util.UUID;

import com.formula.formula.models.dtos.request.PilotRequestDto;
import com.formula.formula.models.dtos.response.PilotResponseDto;
import com.formula.formula.models.enums.PilotStatusEnum;

public interface PilotService {

    void create(PilotRequestDto pilotDto);

    PilotResponseDto findById(UUID id);

    List<PilotResponseDto> findAll();

    void update(PilotResponseDto pilotDto);

    void delete(UUID id);

    void changeStatus(UUID id, String status);

    void changeBlocked(UUID id);
}
