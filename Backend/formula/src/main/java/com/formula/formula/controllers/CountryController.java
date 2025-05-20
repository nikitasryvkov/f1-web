package com.formula.formula.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formula.formula.models.dtos.request.CountryRequestDto;
import com.formula.formula.models.dtos.response.CountryResponseDto;
import com.formula.formula.services.CountryService;

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
@RequestMapping("/api/country")
@Tag(name = "Country Controller", description = "Country Managment")
public class CountryController {

    private CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping()
    @ApiResponse(responseCode = "500", description = "Country with that name already exists!")
    @Operation(summary = "Create Country")
    public void create(@RequestBody CountryRequestDto countryDto) {
        countryService.create(countryDto);
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "Get country by Id")
    public CountryResponseDto get(@PathVariable UUID id) {
        return countryService.findById(id);
    }

    @GetMapping("/all")
    @Operation(summary = "Get all country")
    public List<CountryResponseDto> getAll() {
        return countryService.findAll();
    }

    @PutMapping("/update")
    @Operation(summary = "Update country")
    public void update(@RequestBody CountryResponseDto countryDto) {
        countryService.update(countryDto);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete country")
    public void delete(@PathVariable UUID id) {
        countryService.delete(id);
    }
}
