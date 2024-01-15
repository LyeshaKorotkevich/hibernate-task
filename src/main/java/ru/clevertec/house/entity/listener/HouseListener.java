package ru.clevertec.house.entity.listener;

import jakarta.persistence.PrePersist;
import ru.clevertec.house.entity.House;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Слушатель событий для сущности House.
 * Обеспечивает установку значений полей перед сохранением нового дома в базу данных.
 */
public class HouseListener {

    /**
     * Метод, вызываемый перед сохранением нового дома в базу данных.
     * Устанавливает значения полей createDate и uuid.
     *
     * @param house Сущность дома, перед сохранением.
     */
    @PrePersist
    public void prePersist(House house) {
        house.setCreateDate(LocalDateTime.now());
        house.setUuid(UUID.randomUUID());
    }
}