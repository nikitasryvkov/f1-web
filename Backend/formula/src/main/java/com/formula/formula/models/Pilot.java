package com.formula.formula.models;

import com.formula.formula.models.enums.PilotStatusEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pilots")
public class Pilot extends BaseEntity {
    private String firstName;
    private String secondName;
    private Team team;
    private PilotStatusEnum status;
    private int number;
    private Country country;
    private boolean blocked;

    public Pilot(String firstName, String secondName, Team team, PilotStatusEnum status, int number,
            Country country) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.team = team;
        this.status = status;
        this.number = number;
        this.country = country;
        this.blocked = false;
    }

    protected Pilot() {
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    @Column(name = "second_name")
    public String getSecondName() {
        return secondName;
    }

    @ManyToOne
    @JoinColumn(name = "team_id")
    public Team getTeam() {
        return team;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    public PilotStatusEnum getStatus() {
        return status;
    }

    @Column(name = "number")
    public int getNumber() {
        return number;
    }

    @ManyToOne
    @JoinColumn(name = "country_id")
    public Country getCountry() {
        return country;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setStatus(PilotStatusEnum status) {
        this.status = status;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
}
