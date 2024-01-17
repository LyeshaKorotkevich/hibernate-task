package ru.clevertec.house.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.clevertec.house.entity.House;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface HouseRepository extends JpaRepository<House, Long> {

    Optional<House> findByUuid(UUID uuid);

    void deleteByUuid(UUID uuid);

    List<House> findByAreaIgnoreCaseLikeOrCityIgnoreCaseLikeOrCountryIgnoreCaseLikeOrStreetIgnoreCaseLike(
            String area, String city, String country, String street
    );


    List<House> findByOwners_Uuid(UUID personUuid);
}
