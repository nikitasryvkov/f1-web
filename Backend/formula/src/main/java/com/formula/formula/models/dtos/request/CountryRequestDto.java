package com.formula.formula.models.dtos.request;

public class CountryRequestDto {
    private String title;

    public CountryRequestDto(String title) {
        this.title = title;
    }

    protected CountryRequestDto() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
