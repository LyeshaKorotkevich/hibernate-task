package ru.clevertec.house.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Встраиваемый объект, представляющий паспортные данные.
 */
@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Passport {

    /** Серия паспорта. */
    private String passportSeries;

    /** Номер паспорта. */
    private String passportNumber;
}