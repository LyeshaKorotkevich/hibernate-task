package ru.clevertec.house.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import ru.clevertec.house.entity.Person;

import java.util.ArrayList;
import java.util.List;

public record HouseRequest(

        @Size(max = 40, message = "area is too long.")
        @NotEmpty(message = "area should not be empty")
        String area,

        @Size(max = 40, message = "country is too long.")
        @NotEmpty(message = "country should not be empty")
        String country,

        @Size(max = 40, message = "city is too long.")
        @NotEmpty(message = "city should not be empty")
        String city,

        @Size(max = 40, message = "street is too long.")
        @NotEmpty(message = "street should not be empty")
        String street,

        @Min(value = 1, message = "number should not be less than 1")
        @Max(value = 9999, message = "number should not be greater than 9999")
        int number,

        List<Person> tenants,

        List<Person> owners
) {
    public HouseRequest {
        tenants = new ArrayList<>();
        owners = new ArrayList<>();
    }
}
