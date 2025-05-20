package com.formula.formula.models.dtos.request;

import java.util.UUID;

public class PilotRequestDto {
    private String firstName;
    private String secondName;
    private UUID teamId;
    private String status;
    private int number;
    private UUID countryId;
    private boolean blocked;

    public PilotRequestDto(String firstName, String secondName, UUID teamId, String status, int number,
            UUID countryId) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.teamId = teamId;
        this.status = status;
        this.number = number;
        this.countryId = countryId;
        this.blocked = false;
    }

    protected PilotRequestDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public UUID getTeamId() {
        return teamId;
    }

    public String getStatus() {
        return status;
    }

    public int getNumber() {
        return number;
    }

    public UUID getCountryId() {
        return countryId;
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

    public void setTeamId(UUID teamId) {
        this.teamId = teamId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setCountryId(UUID countryId) {
        this.countryId = countryId;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
}
