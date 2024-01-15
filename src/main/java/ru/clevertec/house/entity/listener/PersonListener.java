package ru.clevertec.house.entity.listener;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import ru.clevertec.house.entity.Person;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Слушатель событий для сущности Person.
 * Обеспечивает установку значений полей перед сохранением и обновлением персоны в базе данных.
 */
public class PersonListener {

    private LocalDateTime now;

    /**
     * Метод, вызываемый перед сохранением новой персоны в базу данных.
     * Устанавливает значения полей createDate, updateDate и uuid.
     *
     * @param person Сущность персоны, перед сохранением.
     */
    @PrePersist
    public void prePersist(Person person) {
        now = LocalDateTime.now();
        person.setCreateDate(now);
        person.setUpdateDate(now);
        person.setUuid(UUID.randomUUID());
    }

    /**
     * Метод, вызываемый перед обновлением существующей персоны в базе данных.
     * Устанавливает значение поля updateDate.
     *
     * @param person Сущность персоны, перед обновлением.
     */
    @PreUpdate
    public void preUpdate(Person person) {
        now = LocalDateTime.now();
        person.setUpdateDate(now);
    }
}