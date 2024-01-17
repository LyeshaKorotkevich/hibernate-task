package ru.clevertec.house.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.house.dto.request.PersonRequest;
import ru.clevertec.house.dto.response.PersonResponse;
import ru.clevertec.house.entity.Person;
import ru.clevertec.house.exception.NotFoundException;
import ru.clevertec.house.mapper.PersonMapper;
import ru.clevertec.house.repository.PersonRepository;
import ru.clevertec.house.service.PersonService;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonMapper personMapper;

    private final PersonRepository personRepository;

    @Override
    public PersonResponse save(PersonRequest personRequest) {
        log.info("Saving a new person...");
        Person personToSave = personMapper.toPerson(personRequest);
        Person savedPerson = personRepository.save(personToSave);
        PersonResponse response = personMapper.toResponse(savedPerson);
        log.info("Person saved successfully. UUID: {}", response.uuid());
        return response;
    }

    @Override
    public PersonResponse findByUuid(UUID uuid) {
        log.info("Finding person by UUID: {}", uuid);
        PersonResponse response = personRepository.findByUuid(uuid)
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
        List<PersonResponse> persons = personRepository.findAll(PageRequest.of(pageNumber, pageSize))
                .stream()
                .map(personMapper::toResponse)
                .toList();
        log.info("Found {} persons.", persons.size());
        return persons;
    }

    @Override
    public List<PersonResponse> findTenantsByHouseUuid(UUID houseUuid) {
        List<PersonResponse> tenants = personRepository.findByLivingHouse_Uuid(houseUuid).stream()
                .map(personMapper::toResponse)
                .toList();
        log.info("Found {} tenants.", tenants.size());
        return tenants;
    }

    @Override
    public List<PersonResponse> findByFullText(String text) {
        String search = "%" + text + "%";
        List<PersonResponse> people = personRepository.findByNameLikeIgnoreCaseOrSurnameLikeIgnoreCase(search, search)
                .stream()
                .map(personMapper::toResponse)
                .toList();
        log.info("Found {} houses.", people.size());
        return people;
    }

    @Override
    public PersonResponse update(UUID uuid, PersonRequest personRequest) {
        log.info("Updating person with UUID: {}", uuid);
        Person existingPerson = personRepository.findByUuid(uuid)
                .orElseThrow(() -> NotFoundException.of(Person.class, uuid));
        Person personToUpdate = personMapper.toPerson(personRequest);
        personToUpdate.setUuid(uuid);
        personToUpdate.setId(existingPerson.getId());
        personToUpdate.setCreateDate(existingPerson.getCreateDate());
        Person updatedPerson = personRepository.saveAndFlush(personToUpdate);
        log.info("Person updated successfully. UUID: {}", uuid);
        return personMapper.toResponse(updatedPerson);
    }

    @Override
    public PersonResponse patch(UUID uuid, Map<String, Object> updates) {
        log.info("Patching person with UUID: {}", uuid);
        Person personToUpdate = personRepository.findByUuid(uuid).orElse(null);
        updates.forEach((key, value) -> {
                    Field field;
                    try {
                        field = Person.class.getDeclaredField(key);
                        field.setAccessible(true);
                        field.set(personToUpdate, value);
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        log.warn("Failed to apply update for field {}: {}", key, e.getMessage());
                    }
                });
        Person updatedPerson = null;
        if (personToUpdate != null) {
            updatedPerson = personRepository.saveAndFlush(personToUpdate);
        }
        log.info("Person patched successfully. UUID: {}", uuid);
        return personMapper.toResponse(updatedPerson);
    }

    @Override
    public void delete(UUID uuid) {
        log.info("Deleting person with UUID: {}", uuid);
        personRepository.deleteByUuid(uuid);
        log.info("Person deleted successfully. UUID: {}", uuid);
    }
}
