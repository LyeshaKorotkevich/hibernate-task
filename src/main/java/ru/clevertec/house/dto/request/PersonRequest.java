package ru.clevertec.house.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import ru.clevertec.house.entity.House;
import ru.clevertec.house.entity.Passport;
import ru.clevertec.house.entity.Sex;

import java.util.ArrayList;
import java.util.List;

public record PersonRequest(

        @Size(max = 30, message = "name is too long.")
        @NotEmpty(message = "name should not be empty")
        String name,

        @Size(max = 30, message = "surname is too long.")
        @NotEmpty(message = "surname should not be empty")
        String surname,

        Sex sex,

        Passport passport,

        List<House> owningHouses
) {
    public PersonRequest {
        owningHouses = new ArrayList<>();
    }
}
