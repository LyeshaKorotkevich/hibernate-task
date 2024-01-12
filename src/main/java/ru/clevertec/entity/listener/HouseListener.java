package ru.clevertec.entity.listener;

import jakarta.persistence.PrePersist;
import ru.clevertec.entity.House;

import java.time.LocalDateTime;
import java.util.UUID;

public class HouseListener {

    @PrePersist
    public void prePersist(House house) {
        house.setCreateDate(LocalDateTime.now());
        house.setUuid(UUID.randomUUID());
    }
}
