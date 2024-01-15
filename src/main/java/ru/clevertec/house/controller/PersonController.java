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
import ru.clevertec.house.dto.request.PersonRequest;
import ru.clevertec.house.dto.response.PersonResponse;
import ru.clevertec.house.service.PersonService;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/people")
public class PersonController {

    private final PersonService personService;

    @GetMapping
    public ResponseEntity<List<PersonResponse>> getAllPeople(@RequestParam(defaultValue = "1") int pageNumber,
                                                             @RequestParam(defaultValue = "15") int pageSize) {

        return ResponseEntity.ok(personService.findAll(pageNumber, pageSize));
    }

    @GetMapping("/live/{uuid}")
    public ResponseEntity<List<PersonResponse>> getTenants(@PathVariable UUID uuid) {
        return ResponseEntity.ok(personService.findTenantsByHouseUuid(uuid));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<PersonResponse> getByUuid(@PathVariable UUID uuid) {

        return ResponseEntity.ok(personService.findByUuid(uuid));
    }

    @SneakyThrows
    @PostMapping
    public ResponseEntity<PersonResponse> save(@Valid @RequestBody PersonRequest personRequest) {

        PersonResponse savedPerson = personService.save(personRequest);
        return ResponseEntity.created(new URI("/people/" + savedPerson.uuid())).body(savedPerson);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<PersonResponse> update(@PathVariable UUID uuid,
                                                 @Valid @RequestBody PersonRequest personRequest) {

        return ResponseEntity.ok(personService.update(uuid, personRequest));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable UUID uuid) {

        personService.delete(uuid);
        return ResponseEntity.noContent().build();
    }
}

