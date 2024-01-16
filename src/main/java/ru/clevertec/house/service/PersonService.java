package ru.clevertec.house.service;

import ru.clevertec.house.dto.request.PersonRequest;
import ru.clevertec.house.dto.response.PersonResponse;

import java.util.List;
import java.util.UUID;

/**
 * Интерфейс сервиса для работы с людьми.
 */
public interface PersonService extends Service<PersonResponse, PersonRequest, UUID> {

    /**
     * Возвращает список жильцов дома по его UUID.
     *
     * @param houseUuid UUID дома.
     * @return Список DTO с информацией о жильцах дома.
     */
    List<PersonResponse> findTenantsByHouseUuid(UUID houseUuid);
}