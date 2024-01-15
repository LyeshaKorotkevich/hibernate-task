package ru.clevertec.house.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.house.dao.impl.PersonDaoImpl;
import ru.clevertec.house.dto.request.PersonRequest;
import ru.clevertec.house.dto.response.PersonResponse;
import ru.clevertec.house.entity.Person;
import ru.clevertec.house.mapper.PersonMapper;
import util.PersonTestData;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

    @Mock
    private PersonDaoImpl personDao;

    @Mock
    private PersonMapper personMapper;

    @InjectMocks
    private PersonServiceImpl personService;

    @Test
    void save() {
        // given
        PersonRequest personRequest = PersonTestData.builder().build().getRequest();
        Person savedPerson = PersonTestData.builder().build().getPerson();
        PersonResponse expectedResponse =  PersonTestData.builder().build().getResponse();

        when(personMapper.toPerson(personRequest)).thenReturn(savedPerson);
        when(personDao.save(savedPerson)).thenReturn(savedPerson);
        when(personMapper.toResponse(savedPerson)).thenReturn(expectedResponse);

        // when
        PersonResponse actualResponse = personService.save(personRequest);

        // then
        verify(personDao).save(savedPerson);
        verify(personMapper).toResponse(savedPerson);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void delete() {
        // given
        UUID uuid = UUID.randomUUID();

        // when
        personService.delete(uuid);

        // then
        verify(personDao).delete(uuid);
    }
}