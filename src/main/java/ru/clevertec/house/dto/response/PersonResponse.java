package ru.clevertec.house.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
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

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        LocalDateTime createDate,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        LocalDateTime updateDate
) {
}
