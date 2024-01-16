package ru.clevertec.house.dao;

import ru.clevertec.house.entity.Person;

import java.util.List;
import java.util.UUID;

public interface PersonDao extends Dao<Person, UUID> {

    List<Person> findTenantsByHouseUuid(UUID houseUuid);
}
