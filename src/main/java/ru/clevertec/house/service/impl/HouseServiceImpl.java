package ru.clevertec.house.service.impl;

import lombok.RequiredArgsConstructor;
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

@Service
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {

    private final HouseDaoImpl houseDao;

    private final HouseMapper houseMapper;

    @Override
    public HouseResponse save(HouseRequest houseRequest) {
        House houseToSave = houseMapper.toHouse(houseRequest);
        House savedHouse = houseDao.save(houseToSave);
        HouseResponse response = houseMapper.toResponse(savedHouse);
        return response;
    }

    @Override
    public HouseResponse findByUuid(UUID uuid) {
        HouseResponse response = houseDao.findByUuid(uuid)
                .map(houseMapper::toResponse)
                .orElseThrow(() -> NotFoundException.of(House.class, uuid));
        return response;
    }

    @Override
    public List<HouseResponse> findAll(int pageNumber, int pageSize) {
        List<HouseResponse> houses = houseDao.findAll(pageNumber, pageSize).stream()
                .map(houseMapper::toResponse)
                .toList();

        return houses;
    }

    @Override
    public void update(UUID uuid, HouseRequest houseRequest) {
        House houseToUpdate = houseMapper.toHouse(houseRequest);
        houseDao.update(uuid, houseToUpdate);
    }

    @Override
    public void delete(UUID uuid) {
        houseDao.delete(uuid);
    }
}
