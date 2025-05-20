package com.formula.formula.repositories.impl;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.formula.formula.models.Team;
import com.formula.formula.repositories.TeamRepository;

@Repository
public class TeamRepositoryImpl extends BaseRepository<Team, UUID> implements TeamRepository {

    public TeamRepositoryImpl() {
        super(Team.class);
    }

}
