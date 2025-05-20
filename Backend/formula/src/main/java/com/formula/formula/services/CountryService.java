package com.formula.formula.services;

import java.util.List;
import java.util.UUID;

import com.formula.formula.models.dtos.request.CountryRequestDto;
import com.formula.formula.models.dtos.response.CountryResponseDto;

public interface CountryService {

    void create(CountryRequestDto countryDto);

    CountryResponseDto findById(UUID id);

    List<CountryResponseDto> findAll();

    void update(CountryResponseDto countryDto);

    void delete(UUID id);
}
