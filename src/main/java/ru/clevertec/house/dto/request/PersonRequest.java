package ru.clevertec.house.dto.request;

import ru.clevertec.house.entity.Passport;
import ru.clevertec.house.entity.Sex;

public record PersonRequest(

        String name,

        String surname,

        Sex sex,

        Passport passport
) {
}
