package ru.clevertec.house.dto.request;

import ru.clevertec.house.entity.Person;

import java.util.ArrayList;
import java.util.List;

public record HouseRequest(

        String area,

        String country,

        String city,

        String street,

        int number,

        List<Person> tenants,

        List<Person> owners
) {
    public HouseRequest {
        tenants = new ArrayList<>();
        owners = new ArrayList<>();
    }
}
