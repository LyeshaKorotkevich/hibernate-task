package ru.clevertec.house.dto.request;

public record HouseRequest(

        String area,

        String country,

        String city,

        String street,

        int number
) {
}
