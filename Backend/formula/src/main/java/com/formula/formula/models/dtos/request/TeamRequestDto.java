package com.formula.formula.models.dtos.request;

import java.util.UUID;

public class TeamRequestDto {
    private String title;
    private UUID countryId;

    public TeamRequestDto(String title, UUID countryId) {
        this.title = title;
        this.countryId = countryId;
    }

    protected TeamRequestDto() {
    }

    public String getTitle() {
        return title;
    }

    public UUID getCountryId() {
        return countryId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCountryId(UUID countryId) {
        this.countryId = countryId;
    }
}
