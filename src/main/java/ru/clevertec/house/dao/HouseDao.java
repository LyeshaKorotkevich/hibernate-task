package ru.clevertec.house.dao;

import ru.clevertec.house.entity.House;

import java.util.List;
import java.util.UUID;

public interface HouseDao extends Dao<House, UUID> {

    List<House> findHousesByPersonUuid(UUID personUuid);
}
