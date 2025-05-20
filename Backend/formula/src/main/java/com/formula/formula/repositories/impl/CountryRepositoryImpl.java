package com.formula.formula.repositories.impl;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.formula.formula.models.Country;
import com.formula.formula.repositories.CountryRepository;

@Repository
public class CountryRepositoryImpl extends BaseRepository<Country, UUID> implements CountryRepository {

    public CountryRepositoryImpl() {
        super(Country.class);
    }

}
