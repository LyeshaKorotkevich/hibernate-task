package ru.clevertec.entity.listener;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import ru.clevertec.entity.Person;

import java.time.LocalDateTime;
import java.util.UUID;

public class PersonListener {

    private LocalDateTime now;

    @PrePersist
    public void prePersist(Person person) {
        now = LocalDateTime.now();
        person.setCreateDate(now);
        person.setUpdateDate(now);

        person.setUuid(UUID.randomUUID());
    }

    @PreUpdate
    public void preUpdate(Person person) {
        now = LocalDateTime.now();
        person.setUpdateDate(now);
    }
}
