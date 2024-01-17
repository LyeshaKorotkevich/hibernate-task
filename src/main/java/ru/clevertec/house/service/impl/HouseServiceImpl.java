package ru.clevertec.house.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.house.dto.request.HouseRequest;
import ru.clevertec.house.dto.response.HouseResponse;
import ru.clevertec.house.entity.House;
import ru.clevertec.house.exception.NotFoundException;
import ru.clevertec.house.mapper.HouseMapper;
import ru.clevertec.house.repository.HouseRepository;
import ru.clevertec.house.service.HouseService;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {

    private final HouseMapper houseMapper;

    private final HouseRepository houseRepository;

    @Override
    public HouseResponse save(HouseRequest houseRequest) {
        log.info("Saving a new house...");
        House houseToSave = houseMapper.toHouse(houseRequest);
        House savedHouse = houseRepository.save(houseToSave);
        HouseResponse response = houseMapper.toResponse(savedHouse);
        log.info("House saved successfully. UUID: {}", response.uuid());
        return response;
    }

    @Override
    public HouseResponse findByUuid(UUID uuid) {
        log.info("Finding house by UUID: {}", uuid);
        HouseResponse response = houseRepository.findByUuid(uuid)
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
        List<HouseResponse> houses = houseRepository.findAll(PageRequest.of(pageNumber, pageSize))
                .stream()
                .map(houseMapper::toResponse)
                .toList();
        log.info("Found {} houses.", houses.size());
        return houses;
    }

    @Override
    public List<HouseResponse> findHousesByPersonUuid(UUID uuid) {
        log.info("Finding all houses. Person uuid: {}", uuid);
        List<HouseResponse> houses = houseRepository.findByOwners_Uuid(uuid).stream()
                .map(houseMapper::toResponse)
                .toList();
        log.info("Found {} houses.", houses.size());
        return houses;
    }

    @Override
    public List<HouseResponse> findByFullText(String text) {
        String search = "%" + text + "%";
        List<HouseResponse> houses = houseRepository
                .findByAreaIgnoreCaseLikeOrCityIgnoreCaseLikeOrCountryIgnoreCaseLikeOrStreetIgnoreCaseLike(search, search, search, search)
                .stream()
                .map(houseMapper::toResponse)
                .toList();
        log.info("Found {} houses.", houses.size());
        return houses;
    }

    @Override
    public HouseResponse update(UUID uuid, HouseRequest houseRequest) {
        log.info("Updating house with UUID: {}", uuid);
        House existingHouse = houseRepository.findByUuid(uuid)
                .orElseThrow(() -> NotFoundException.of(House.class, uuid));
        House houseToUpdate = houseMapper.toHouse(houseRequest);
        houseToUpdate.setUuid(uuid);
        houseToUpdate.setId(existingHouse.getId());
        houseToUpdate.setCreateDate(existingHouse.getCreateDate());
        House updatedHouse = houseRepository.save(houseToUpdate);
        log.info("House updated successfully. UUID: {}", uuid);
        return houseMapper.toResponse(updatedHouse);
    }

    @Override
    public HouseResponse patch(UUID uuid, Map<String, Object> updates) {
        log.info("Patching house with UUID: {}", uuid);
        House houseToUpdate = houseRepository.findByUuid(uuid)
                .orElseThrow(() -> NotFoundException.of(House.class, uuid));

        updates.forEach((key, value) -> {
            try {
                Field field = House.class.getDeclaredField(key);
                field.setAccessible(true);
                field.set(houseToUpdate, value);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                log.warn("Failed to apply update for field {}: {}", key, e.getMessage());
            }
        });

        House updatedHouse = houseRepository.save(houseToUpdate);
        log.info("House updated successfully. UUID: {}", uuid);
        return houseMapper.toResponse(updatedHouse);
    }

    @Override
    public void delete(UUID uuid) {
        log.info("Deleting house with UUID: {}", uuid);
        houseRepository.deleteByUuid(uuid);
        log.info("House deleted successfully. UUID: {}", uuid);
    }
}