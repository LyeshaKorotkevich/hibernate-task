package ru.clevertec.house.dto.response;

import ru.clevertec.house.entity.Passport;
import ru.clevertec.house.entity.Sex;

import java.time.LocalDateTime;
import java.util.UUID;

public record PersonResponse(
        UUID uuid,

        String name,

        String surname,

        Sex sex,

        Passport passport,

        LocalDateTime createDate,

        LocalDateTime updateDate
) {
}
