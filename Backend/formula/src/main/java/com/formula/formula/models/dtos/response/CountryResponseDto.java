package com.formula.formula.models.dtos.response;

import java.util.UUID;

public class CountryResponseDto {
    private UUID id;
    private String title;

    public CountryResponseDto(String title) {
        this.title = title;
    }

    protected CountryResponseDto() {
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
