package ru.clevertec.house.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Passport {

    private String passportSeries;

    private String passportNumber;
}
