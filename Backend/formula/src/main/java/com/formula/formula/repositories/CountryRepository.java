package com.formula.formula.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.formula.formula.models.Country;

public interface CountryRepository {

    void save(Country country);

    Optional<Country> findById(UUID id);

    List<Country> findAll();

    void update(Country country);

    void delete(Country country);
}
