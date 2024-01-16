package ru.clevertec.house.service;

import ru.clevertec.house.dto.request.HouseRequest;
import ru.clevertec.house.dto.response.HouseResponse;

import java.util.List;
import java.util.UUID;

/**
 * Интерфейс сервиса для работы с домами.
 */
public interface HouseService extends Service<HouseResponse, HouseRequest, UUID> {

    /**
     * Возвращает список домов, принадлежащих человеку по его UUID.
     *
     * @param personUuid UUID человека.
     * @return Список DTO с информацией о домах.
     */
    List<HouseResponse> findHousesByPersonUuid(UUID personUuid);
}