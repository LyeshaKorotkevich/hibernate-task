package ru.clevertec.house.service;

import ru.clevertec.house.dto.request.PersonRequest;
import ru.clevertec.house.dto.response.PersonResponse;

import java.util.List;
import java.util.UUID;

public interface PersonService {

    PersonResponse save(PersonRequest personRequest);

    PersonResponse findByUuid(UUID uuid);

    List<PersonResponse> findAll(int pageNumber, int pageSize);

    void update(UUID uuid, PersonRequest personRequest);

    void delete(UUID uuid);
}
