package ru.clevertec.house.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.clevertec.house.entity.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByUuid(UUID uuid);

    void deleteByUuid(UUID uuid);

    List<Person> findByNameLikeIgnoreCaseOrSurnameLikeIgnoreCase(String name, String surname);

    List<Person> findByLivingHouse_Uuid(UUID uuid);
}
