package com.formula.formula.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "teams")
public class Team extends BaseEntity {
    private String title;
    private Country country;

    public Team(String title, Country country) {
        this.title = title;
        this.country = country;
    }

    protected Team() {
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    @ManyToOne
    @JoinColumn(name = "country_id")
    public Country getCountry() {
        return country;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
