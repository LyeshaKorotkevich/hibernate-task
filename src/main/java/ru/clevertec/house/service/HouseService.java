package ru.clevertec.house.service;

import ru.clevertec.house.dto.request.HouseRequest;
import ru.clevertec.house.dto.response.HouseResponse;

import java.util.List;
import java.util.UUID;

public interface HouseService {

    HouseResponse save(HouseRequest houseRequest);

    HouseResponse findByUuid(UUID uuid);

    List<HouseResponse> findAll(int pageNumber, int pageSize);

    List<HouseResponse> findHousesByPersonUuid(UUID personUuid);

    HouseResponse update(UUID uuid, HouseRequest houseRequest);

    void delete(UUID uuid);
}
