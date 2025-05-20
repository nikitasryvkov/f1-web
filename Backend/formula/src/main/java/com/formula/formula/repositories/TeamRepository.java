package com.formula.formula.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.formula.formula.models.Team;

public interface TeamRepository {

    void save(Team team);

    Optional<Team> findById(UUID id);

    List<Team> findAll();

    void update(Team team);

    void delete(Team team);
}
