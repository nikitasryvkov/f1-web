package com.formula.formula.models.dtos.response;

import java.util.UUID;

public class TeamResponseDto {
    private UUID id;
    private String title;
    private UUID countryId;

    public TeamResponseDto(String title, UUID countryId) {
        this.title = title;
        this.countryId = countryId;
    }

    protected TeamResponseDto() {
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public UUID getCountryId() {
        return countryId;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCountryId(UUID countryId) {
        this.countryId = countryId;
    }
}
