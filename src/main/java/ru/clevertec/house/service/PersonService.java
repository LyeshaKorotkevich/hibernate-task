package ru.clevertec.house.service;

import ru.clevertec.house.dto.request.PersonRequest;
import ru.clevertec.house.dto.response.PersonResponse;

import java.util.List;
import java.util.UUID;

/**
 * Интерфейс сервиса для работы с людьми.
 */
public interface PersonService {

    /**
     * Создает нового человека на основе информации из запроса.
     *
     * @param personRequest DTO с информацией о человеке для создания.
     * @return DTO с информацией о созданном человеке.
     */
    PersonResponse save(PersonRequest personRequest);

    /**
     * Возвращает информацию о человеке по его UUID.
     *
     * @param uuid UUID человека.
     * @return DTO с информацией о человеке.
     */
    PersonResponse findByUuid(UUID uuid);

    /**
     * Возвращает список всех людей с пагинацией.
     *
     * @param pageNumber Номер страницы.
     * @param pageSize   Количество элементов на странице.
     * @return Список DTO с информацией о людях.
     */
    List<PersonResponse> findAll(int pageNumber, int pageSize);

    /**
     * Возвращает список жильцов дома по его UUID.
     *
     * @param houseUuid UUID дома.
     * @return Список DTO с информацией о жильцах дома.
     */
    List<PersonResponse> findTenantsByHouseUuid(UUID houseUuid);

    /**
     * Обновляет информацию о человеке по его UUID.
     *
     * @param uuid          UUID человека.
     * @param personRequest DTO с новой информацией о человеке.
     * @return DTO с обновленной информацией о человеке.
     */
    PersonResponse update(UUID uuid, PersonRequest personRequest);

    /**
     * Удаляет человека по его UUID.
     *
     * @param uuid UUID человека.
     */
    void delete(UUID uuid);
}