package ru.clevertec.house.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.clevertec.house.dao.impl.HouseDaoImpl;
import ru.clevertec.house.dto.request.HouseRequest;
import ru.clevertec.house.dto.response.HouseResponse;
import ru.clevertec.house.entity.House;
import ru.clevertec.house.exception.NotFoundException;
import ru.clevertec.house.mapper.HouseMapper;
import ru.clevertec.house.service.HouseService;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {

    private final HouseDaoImpl houseDao;
    private final HouseMapper houseMapper;

    @Override
    public HouseResponse save(HouseRequest houseRequest) {
        log.info("Saving a new house...");
        House houseToSave = houseMapper.toHouse(houseRequest);
        House savedHouse = houseDao.save(houseToSave);
        HouseResponse response = houseMapper.toResponse(savedHouse);
        log.info("House saved successfully. UUID: {}", response.uuid());
        return response;
    }

    @Override
    public HouseResponse findByUuid(UUID uuid) {
        log.info("Finding house by UUID: {}", uuid);
        HouseResponse response = houseDao.findByUuid(uuid)
                .map(houseMapper::toResponse)
                .orElseThrow(() -> {
                    log.warn("House not found with UUID: {}", uuid);
                    return NotFoundException.of(House.class, uuid);
                });
        log.info("House found successfully. UUID: {}", response.uuid());
        return response;
    }

    @Override
    public List<HouseResponse> findAll(int pageNumber, int pageSize) {
        log.info("Finding all houses. Page number: {}, Page size: {}", pageNumber, pageSize);
        List<HouseResponse> houses = houseDao.findAll(pageNumber, pageSize).stream()
                .map(houseMapper::toResponse)
                .toList();
        log.info("Found {} houses.", houses.size());
        return houses;
    }

    @Override
    public List<HouseResponse> findHousesByPersonUuid(UUID uuid) {
        log.info("Finding all houses. Person uuid: {}", uuid);
        List<HouseResponse> houses = houseDao.findHousesByPersonUuid(uuid).stream()
                .map(houseMapper::toResponse)
                .toList();
        log.info("Found {} houses.", houses.size());
        return houses;
    }

    @Override
    public HouseResponse update(UUID uuid, HouseRequest houseRequest) {
        log.info("Updating house with UUID: {}", uuid);
        House houseToUpdate = houseMapper.toHouse(houseRequest);
        House updatedHouse = houseDao.update(uuid, houseToUpdate);
        log.info("House updated successfully. UUID: {}", uuid);
        return houseMapper.toResponse(updatedHouse);
    }

    @Override
    public void delete(UUID uuid) {
        log.info("Deleting house with UUID: {}", uuid);
        houseDao.delete(uuid);
        log.info("House deleted successfully. UUID: {}", uuid);
    }
}