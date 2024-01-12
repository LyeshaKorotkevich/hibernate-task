package ru.clevertec.house.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record HouseResponse(
        UUID uuid,

        String area,

        String country,

        String city,

        String street,

        int number,

        LocalDateTime createDate
) {
}
