package ru.clevertec.house.dto.request;

import ru.clevertec.house.entity.House;
import ru.clevertec.house.entity.Passport;
import ru.clevertec.house.entity.Sex;

import java.util.ArrayList;
import java.util.List;

public record PersonRequest(

        String name,

        String surname,

        Sex sex,

        Passport passport,

        List<House> owningHouses
) {
    public PersonRequest {
        owningHouses = new ArrayList<>();
    }
}
