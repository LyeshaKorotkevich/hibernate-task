package ru.clevertec.house.dao;

import java.util.List;
import java.util.Optional;

/**
 * Интерфейс для работы с объектами в базе данных.
 */
public interface Dao<T, V> {

    /**
     * Создает новый объект.
     *
     * @param obj Данные нового объекта.
     * @return Объект T.
     */
    T save(T obj);

    /**
     * Получает объект по его uuid.
     *
     * @param uuid uuid объекта.
     * @return Объект Optional<T>, представляющий найденный объект.
     */
    Optional<T> findByUuid(V uuid);

    /**
     * Получает список всех объектов.
     *
     * @param pageNumber pageNumber номер страницы.
     * @param pageSize pageSize размер страницы.
     * @return Список всех объектов.
     */
    List<T> findAll(int pageNumber, int pageSize);

    /**
     * Обновляет информацию об объекте.
     *
     * @param obj Объект для обновления.
     */
    void update(V uuid, T obj);

    /**
     * Удаляет объект по его uuid.
     *
     * @param uuid id объекта для удаления.
     */
    void delete(V uuid);
}