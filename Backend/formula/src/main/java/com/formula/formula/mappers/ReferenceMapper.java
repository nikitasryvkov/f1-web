package com.formula.formula.mappers;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.formula.formula.exceptions.CountryException;
import com.formula.formula.exceptions.TeamException;
import com.formula.formula.models.Country;
import com.formula.formula.models.Team;
import com.formula.formula.repositories.CountryRepository;
import com.formula.formula.repositories.TeamRepository;

@Component
public class ReferenceMapper {

    private TeamRepository teamRepository;
    private CountryRepository countryRepository;

    public ReferenceMapper(TeamRepository teamRepository, CountryRepository countryRepository) {
        this.teamRepository = teamRepository;
        this.countryRepository = countryRepository;
    }

    public Team mapTeam(UUID id) {
        return teamRepository.findById(id)
                .orElseThrow(TeamException.NotFoundException.supplier("Team {0} was not found!", id));
    }

    public Country mapCountry(UUID id) {
        return countryRepository.findById(id)
                .orElseThrow(CountryException.NotFoundException.supplier("Country {0} was not found!", id));
    }
}
