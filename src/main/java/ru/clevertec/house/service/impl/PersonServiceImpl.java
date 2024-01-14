package ru.clevertec.house.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.clevertec.house.dao.impl.PersonDaoImpl;
import ru.clevertec.house.dto.request.PersonRequest;
import ru.clevertec.house.dto.response.PersonResponse;
import ru.clevertec.house.entity.Person;
import ru.clevertec.house.exception.NotFoundException;
import ru.clevertec.house.mapper.PersonMapper;
import ru.clevertec.house.service.PersonService;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonDaoImpl personDao;

    private final PersonMapper personMapper;

    @Override
    public PersonResponse save(PersonRequest personRequest) {
        log.info("Saving a new person...");
        Person personToSave = personMapper.toPerson(personRequest);
        Person savedPerson = personDao.save(personToSave);
        PersonResponse response = personMapper.toResponse(savedPerson);
        log.info("Person saved successfully. UUID: {}", response.uuid());
        return response;
    }

    @Override
    public PersonResponse findByUuid(UUID uuid) {
        log.info("Finding person by UUID: {}", uuid);
        PersonResponse response = personDao.findByUuid(uuid)
                .map(personMapper::toResponse)
                .orElseThrow(() -> {
                    log.warn("Person not found with UUID: {}", uuid);
                    return NotFoundException.of(Person.class, uuid);
                });
        log.info("Person found successfully. UUID: {}", response.uuid());
        return response;
    }

    @Override
    public List<PersonResponse> findAll(int pageNumber, int pageSize) {
        log.info("Finding all persons. Page number: {}, Page size: {}", pageNumber, pageSize);
        List<PersonResponse> persons = personDao.findAll(pageNumber, pageSize).stream()
                .map(personMapper::toResponse)
                .toList();
        log.info("Found {} persons.", persons.size());
        return persons;
    }

    @Override
    public void update(UUID uuid, PersonRequest personRequest) {
        log.info("Updating person with UUID: {}", uuid);
        Person personToUpdate = personMapper.toPerson(personRequest);
        personDao.update(uuid, personToUpdate);
        log.info("Person updated successfully. UUID: {}", uuid);
    }

    @Override
    public void delete(UUID uuid) {
        log.info("Deleting person with UUID: {}", uuid);
        personDao.delete(uuid);
        log.info("Person deleted successfully. UUID: {}", uuid);
    }
}
