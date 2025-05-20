package com.formula.formula.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.formula.formula.models.Country;
import com.formula.formula.models.dtos.request.CountryRequestDto;
import com.formula.formula.models.dtos.response.CountryResponseDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = ReferenceMapper.class)
public interface CountryMapper {

    Country toCountry(CountryRequestDto country);

    Country toCountry(CountryResponseDto country);

    CountryRequestDto toCountryRequest(Country country);

    CountryResponseDto toCountryResponse(Country country);
}
