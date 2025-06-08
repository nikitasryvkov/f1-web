package com.formula.formula.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.formula.formula.exceptions.CountryException;
import com.formula.formula.exceptions.PilotException;
import com.formula.formula.exceptions.TeamException;
import com.formula.formula.mappers.PilotMapper;
import com.formula.formula.models.Country;
import com.formula.formula.models.Pilot;
import com.formula.formula.models.Team;
import com.formula.formula.models.dtos.request.PilotRequestDto;
import com.formula.formula.models.dtos.response.PilotResponseDto;
import com.formula.formula.models.enums.PilotStatusEnum;
import com.formula.formula.repositories.CountryRepository;
import com.formula.formula.repositories.PilotRepository;
import com.formula.formula.repositories.TeamRepository;
import com.formula.formula.services.PilotService;

@Service
public class PilotServiceImpl implements PilotService {

    private PilotRepository pilotRepository;
    private TeamRepository teamRepository;
    private CountryRepository countryRepository;
    private PilotMapper pilotMapper;

    public PilotServiceImpl(PilotRepository pilotRepository, TeamRepository teamRepository,
            CountryRepository countryRepository, PilotMapper pilotMapper) {
        this.pilotRepository = pilotRepository;
        this.teamRepository = teamRepository;
        this.countryRepository = countryRepository;
        this.pilotMapper = pilotMapper;
    }

    @Override
    public void create(PilotRequestDto pilotDto) {
        List<Pilot> allPilot = pilotRepository.findAll();

        for (Pilot p : allPilot) {
            if (p.getNumber() == pilotDto.getNumber()) {
                throw new PilotException.ResourseAlreadyExistsException("Pilot with this number already exist!");
            }
        }

        if (pilotDto.getStatus().equals(PilotStatusEnum.FIRST.toString())
                || pilotDto.getStatus().equals(PilotStatusEnum.SECOND.toString())) {
            if (!checkAvailableSeats(pilotDto.getTeamId())) {
                throw new PilotException.ResourseAlreadyExistsException(
                        "The number of active pilots has reached its maximum!");
            }
        }

        pilotRepository.save(pilotMapper.toPilot(pilotDto));
    }

    @Override
    public PilotResponseDto findById(UUID id) {
        return pilotMapper.toPilotResponse(pilotRepository.findById(id)
                .orElseThrow(PilotException.NotFoundException.supplier("Pilot {0} was not found!", id)));
    }

    @Override
    public List<PilotResponseDto> findAll() {
        return pilotRepository
                .findAll()
                .stream()
                .map(p -> pilotMapper.toPilotResponse(p))
                .toList();
    }

    @Override
    public void update(PilotResponseDto pilotDto) {
        if (pilotDto.isBlocked()) {
            throw new PilotException.InvalidStateException("This pilot is blocked for modification!");
        }

        Pilot pilot = pilotRepository.findById(pilotDto.getId())
                .orElseThrow(PilotException.NotFoundException.supplier("Pilot {0} was not found!", pilotDto.getId()));

        Team team = teamRepository.findById(pilotDto.getTeamId())
                .orElseThrow(TeamException.NotFoundException.supplier("Team {0} was not found!", pilotDto.getTeamId()));

        Country country = countryRepository.findById(pilotDto.getCountryId()).orElseThrow(
                CountryException.NotFoundException.supplier("Country {0} was not found!", pilotDto.getCountryId()));

        PilotStatusEnum status;
        try {
            status = PilotStatusEnum.valueOf(pilotDto.getStatus().toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new PilotException.InvalidStatusException("Invalid status: " + pilotDto.getStatus());
        }

        pilot.setFirstName(pilotDto.getFirstName());
        pilot.setSecondName(pilotDto.getSecondName());
        pilot.setTeam(team);
        pilot.setStatus(status);
        pilot.setNumber(pilotDto.getNumber());
        pilot.setCountry(country);

        pilotRepository.update(pilot);
    }

    @Override
    public void delete(UUID id) {
        Pilot pilot = pilotRepository.findById(id)
                .orElseThrow(PilotException.NotFoundException.supplier("Pilot {0} was not found!", id));

        if (pilot.isBlocked()) {
            throw new PilotException.InvalidStateException("This pilot is blocked for modification!");
        }

        pilotRepository.delete(pilot);
    }

    private boolean checkAvailableSeats(UUID teamId) {
        List<Pilot> allPilot = pilotRepository.findAll();
        int countPilot = 0;

        for (Pilot p : allPilot) {
            if (p.getTeam().getId().equals(teamId) && (p.getStatus() == PilotStatusEnum.FIRST
                    || p.getStatus() == PilotStatusEnum.SECOND)) {
                countPilot++;
            }
        }

        if (countPilot >= 2) {
            return false;
        }

        return true;
    }

    @Override
    public void changeStatus(UUID id, String status) {
        Pilot pilot = pilotRepository.findById(id)
                .orElseThrow(PilotException.NotFoundException.supplier("Pilot {0} was not found!", id));

        if (pilot.isBlocked()) {
            throw new PilotException.InvalidStateException("This pilot is blocked for modification!");
        }

        if (!checkAvailableSeats(pilot.getTeam().getId())) {
            if (status.equalsIgnoreCase("RESERVE")) {
                pilot.setStatus(PilotStatusEnum.valueOf(status.toUpperCase()));
                pilotRepository.update(pilot);
                return;
            }

            throw new PilotException.ResourseAlreadyExistsException(
                    "The number of active pilots has reached its maximum!");
        }

        pilot.setStatus(PilotStatusEnum.valueOf(status.toUpperCase()));
        pilotRepository.update(pilot);
    }

    @Override
    public void changeBlocked(UUID id) {
        Pilot pilot = pilotRepository.findById(id)
                .orElseThrow(PilotException.NotFoundException.supplier("Pilot {0} was not found!", id));

        pilot.setBlocked(!pilot.isBlocked());
        pilotRepository.update(pilot);
    }
}
