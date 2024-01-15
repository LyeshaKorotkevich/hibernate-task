package ru.clevertec.house.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.house.dto.request.HouseRequest;
import ru.clevertec.house.dto.response.HouseResponse;
import ru.clevertec.house.service.HouseService;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/houses")
public class HouseController {

    private final HouseService houseService;

    @GetMapping
    public ResponseEntity<List<HouseResponse>> getAllHouses(@RequestParam(defaultValue = "1") int pageNumber,
                                                            @RequestParam(defaultValue = "15") int pageSize) {

        return ResponseEntity.ok(houseService.findAll(pageNumber, pageSize));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<HouseResponse> getByUuid(@PathVariable UUID uuid) {

        return ResponseEntity.ok(houseService.findByUuid(uuid));
    }

    @GetMapping("/own/{uuid}")
    public ResponseEntity<List<HouseResponse>> getHouses(@PathVariable UUID uuid) {
        return ResponseEntity.ok(houseService.findHousesByPersonUuid(uuid));
    }

    @SneakyThrows
    @PostMapping
    public ResponseEntity<HouseResponse> save(@Valid @RequestBody HouseRequest houseRequest) {

        HouseResponse savedHouse = houseService.save(houseRequest);
        return ResponseEntity.created(new URI("/houses/" + savedHouse.uuid())).body(savedHouse);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<HouseResponse> update(@PathVariable UUID uuid,
                                                @Valid @RequestBody HouseRequest houseRequest) {

        return ResponseEntity.ok(houseService.update(uuid, houseRequest));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable UUID uuid) {

        houseService.delete(uuid);
        return ResponseEntity.noContent().build();
    }
}
