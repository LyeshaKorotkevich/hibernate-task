package ru.clevertec.house.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.clevertec.house.dto.request.HouseRequest;
import ru.clevertec.house.dto.response.HouseResponse;
import ru.clevertec.house.entity.House;

/**
 * Интерфейс-маппер для преобразования объектов между классами House и связанными с ними DTO.
 */
@Mapper(componentModel = "spring")
public interface HouseMapper {

    /**
     * Преобразование объекта HouseRequest в объект House.
     *
     * @param houseRequest DTO с информацией о доме для создания.
     * @return Объект House.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    House toHouse(HouseRequest houseRequest);

    /**
     * Преобразование объекта House в объект HouseResponse.
     *
     * @param house Объект House.
     * @return DTO с информацией о доме для отображения.
     */
    HouseResponse toResponse(House house);
}