package com.formula.formula.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.formula.formula.models.Pilot;

public interface PilotRepository {

    void save(Pilot pilot);

    Optional<Pilot> findById(UUID id);

    List<Pilot> findAll();

    void update(Pilot pilot);

    void delete(Pilot pilot);
}
