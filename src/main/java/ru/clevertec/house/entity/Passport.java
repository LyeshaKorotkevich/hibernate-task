package ru.clevertec.house.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class Passport {

    private String passportSeries;

    private String passportNumber;
}
