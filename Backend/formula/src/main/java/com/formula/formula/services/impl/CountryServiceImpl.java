package com.formula.formula.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.formula.formula.exceptions.CountryException;
import com.formula.formula.mappers.CountryMapper;
import com.formula.formula.models.Country;
import com.formula.formula.models.dtos.request.CountryRequestDto;
import com.formula.formula.models.dtos.response.CountryResponseDto;
import com.formula.formula.repositories.CountryRepository;
import com.formula.formula.services.CountryService;

@Service
public class CountryServiceImpl implements CountryService {

    private CountryRepository countryRepository;
    private CountryMapper countryMapper;

    public CountryServiceImpl(CountryRepository countryRepository, CountryMapper countryMapper) {
        this.countryRepository = countryRepository;
        this.countryMapper = countryMapper;
    }

    @Override
    public void create(CountryRequestDto country) {
        List<Country> allCountry = countryRepository.findAll();

        for (Country c : allCountry) {
            if (c.getTitle().equals(country.getTitle())) {
                throw new CountryException.ResourseAlreadyExistsException("Country already exist!");
            }
        }

        countryRepository.save(countryMapper.toCountry(country));
    }

    @Override
    public CountryResponseDto findById(UUID id) {
        return countryMapper.toCountryResponse(countryRepository.findById(id)
                .orElseThrow(CountryException.NotFoundException.supplier("Country {0} was not found!", id)));
    }

    @Override
    public List<CountryResponseDto> findAll() {
        return countryRepository
                .findAll()
                .stream()
                .map(c -> countryMapper.toCountryResponse(c))
                .toList();
    }

    @Override
    public void update(CountryResponseDto country) {
        countryRepository.update(countryMapper.toCountry(country));
    }

    @Override
    public void delete(UUID id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(CountryException.NotFoundException.supplier("Country {0} was not found!", id));

        countryRepository.delete(country);
    }
}
