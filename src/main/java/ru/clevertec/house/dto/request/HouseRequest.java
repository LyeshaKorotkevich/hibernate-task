package ru.clevertec.house.dto.request;

import java.util.UUID;

public record HouseRequest(
        UUID uuid,

        String area,

        String country,

        String city,

        String street,

        int number
) {
}
