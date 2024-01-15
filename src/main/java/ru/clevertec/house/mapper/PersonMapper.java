package ru.clevertec.house.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.clevertec.house.dto.request.PersonRequest;
import ru.clevertec.house.dto.response.PersonResponse;
import ru.clevertec.house.entity.Person;

/**
 * Интерфейс-маппер для преобразования объектов между классами Person и связанными с ними DTO.
 */
@Mapper(componentModel = "spring")
public interface PersonMapper {

    /**
     * Преобразование объекта PersonRequest в объект Person.
     *
     * @param personRequest DTO с информацией о человеке для создания.
     * @return Объект Person.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "livingHouse", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    Person toPerson(PersonRequest personRequest);

    /**
     * Преобразование объекта Person в объект PersonResponse.
     *
     * @param person Объект Person.
     * @return DTO с информацией о человеке для отображения.
     */
    PersonResponse toResponse(Person person);
}