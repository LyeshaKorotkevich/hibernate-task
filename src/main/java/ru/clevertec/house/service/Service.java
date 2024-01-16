package ru.clevertec.house.service;

import java.util.List;
import java.util.Map;

public interface Service<T, V, K> {

    /**
     * Создает новый дом на основе информации из запроса.
     *
     * @param request DTO с информацией о доме для создания.
     * @return DTO с информацией о созданном доме.
     */
    T save(V request);

    /**
     * Возвращает информацию о доме по его UUID.
     *
     * @param id UUID дома.
     * @return DTO с информацией о доме.
     */
    T findByUuid(K id);

    /**
     * Возвращает список всех домов с пагинацией.
     *
     * @param pageNumber Номер страницы.
     * @param pageSize   Количество элементов на странице.
     * @return Список DTO с информацией о домах.
     */
    List<T> findAll(int pageNumber, int pageSize);

    List<T> findByFullText(String text);

    /**
     * Обновляет информацию о доме по его UUID.
     *
     * @param id      id дома.
     * @param request DTO с новой информацией о доме.
     * @return DTO с обновленной информацией о доме.
     */
    T update(K id, V request);

    T patch(K id, Map<String, Object> updates);

    /**
     * Удаляет дом по его UUID.
     *
     * @param id UUID дома.
     */
    void delete(K id);
}
