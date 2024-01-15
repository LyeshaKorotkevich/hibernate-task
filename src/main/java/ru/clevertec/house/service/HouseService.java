package ru.clevertec.house.service;

import ru.clevertec.house.dto.request.HouseRequest;
import ru.clevertec.house.dto.response.HouseResponse;

import java.util.List;
import java.util.UUID;

/**
 * Интерфейс сервиса для работы с домами.
 */
public interface HouseService {

    /**
     * Создает новый дом на основе информации из запроса.
     *
     * @param houseRequest DTO с информацией о доме для создания.
     * @return DTO с информацией о созданном доме.
     */
    HouseResponse save(HouseRequest houseRequest);

    /**
     * Возвращает информацию о доме по его UUID.
     *
     * @param uuid UUID дома.
     * @return DTO с информацией о доме.
     */
    HouseResponse findByUuid(UUID uuid);

    /**
     * Возвращает список всех домов с пагинацией.
     *
     * @param pageNumber Номер страницы.
     * @param pageSize   Количество элементов на странице.
     * @return Список DTO с информацией о домах.
     */
    List<HouseResponse> findAll(int pageNumber, int pageSize);

    /**
     * Возвращает список домов, принадлежащих человеку по его UUID.
     *
     * @param personUuid UUID человека.
     * @return Список DTO с информацией о домах.
     */
    List<HouseResponse> findHousesByPersonUuid(UUID personUuid);

    /**
     * Обновляет информацию о доме по его UUID.
     *
     * @param uuid         UUID дома.
     * @param houseRequest DTO с новой информацией о доме.
     * @return DTO с обновленной информацией о доме.
     */
    HouseResponse update(UUID uuid, HouseRequest houseRequest);

    /**
     * Удаляет дом по его UUID.
     *
     * @param uuid UUID дома.
     */
    void delete(UUID uuid);
}