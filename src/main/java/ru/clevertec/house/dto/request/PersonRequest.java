package ru.clevertec.house.dto.request;

import ru.clevertec.house.entity.Passport;
import ru.clevertec.house.entity.Sex;

import java.util.UUID;

public record PersonRequest(
        UUID uuid,

        String name,

        String surname,

        Sex sex,

        Passport passport
) {
}
