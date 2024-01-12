package ru.clevertec.house.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.clevertec.house.dto.request.HouseRequest;
import ru.clevertec.house.dto.response.HouseResponse;
import ru.clevertec.house.entity.House;

@Mapper(componentModel = "spring")
public interface HouseMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    House toHouse(HouseRequest houseRequest);

    HouseResponse toResponse(House house);
}
