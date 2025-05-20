package com.formula.formula.repositories.impl;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.formula.formula.models.Pilot;
import com.formula.formula.repositories.PilotRepository;

@Repository
public class PilotRepositoryImpl extends BaseRepository<Pilot, UUID> implements PilotRepository {

    public PilotRepositoryImpl() {
        super(Pilot.class);
    }
}
