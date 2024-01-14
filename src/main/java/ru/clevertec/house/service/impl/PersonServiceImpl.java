package ru.clevertec.house.service.impl;

import org.springframework.stereotype.Service;
import ru.clevertec.house.dto.request.PersonRequest;
import ru.clevertec.house.dto.response.PersonResponse;
import ru.clevertec.house.service.PersonService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonServiceImpl implements PersonService {
    @Override
    public PersonResponse save(PersonRequest personRequest) {
        return null;
    }

    @Override
    public PersonResponse findByUuid(UUID uuid) {
        return Optional.empty();
    }

    @Override
    public List<PersonResponse> findAll(int pageNumber, int pageSize) {
        return null;
    }

    @Override
    public void update(UUID uuid, PersonRequest personRequest) {

    }

    @Override
    public void delete(UUID uuid) {

    }
}
