package ru.clevertec.house.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.house.mapper.HouseMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class HouseServiceImplTest {

    @Mock
    private HouseDaoImpl houseDao;

    @Mock
    private HouseMapper houseMapper;

    @InjectMocks
    private HouseServiceImpl houseService;

    @Test
    void save() {
        // given
//        HouseRequest houseRequest = HouseTestData.builder().build().getRequest();
//        House savedHouse = HouseTestData.builder().build().getHouse();
//        HouseResponse expectedResponse =  HouseTestData.builder().build().getResponse();
//
//        when(houseMapper.toHouse(houseRequest)).thenReturn(savedHouse);
//        when(houseDao.save(savedHouse)).thenReturn(savedHouse);
//        when(houseMapper.toResponse(savedHouse)).thenReturn(expectedResponse);
//
//        // when
//        HouseResponse actualResponse = houseService.save(houseRequest);
//
//        // then
//        verify(houseDao).save(savedHouse);
//        verify(houseMapper).toResponse(savedHouse);
//
//        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void delete() {
        // given
//        UUID uuid = UUID.randomUUID();
//
//        // when
//        houseService.delete(uuid);
//
//        // then
//        verify(houseDao).delete(uuid);
    }

    @Test
    void findByUuid() {

    }

    @Test
    void findAllHouses() {

    }
}