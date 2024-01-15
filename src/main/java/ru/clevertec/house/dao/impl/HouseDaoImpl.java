package ru.clevertec.house.dao.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.house.dao.Dao;
import ru.clevertec.house.entity.House;
import ru.clevertec.house.exception.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Repository
@RequiredArgsConstructor
public class HouseDaoImpl implements Dao<House, UUID> {

    private final SessionFactory sessionFactory;

    private final JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public House save(House obj) {
        try (Session session = sessionFactory.openSession()) {
            session.persist(obj);
            log.info("Saved a new house with UUID: {}", obj.getUuid());
            return obj;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<House> findByUuid(UUID uuid) {
        try (Session session = sessionFactory.openSession()) {
            House house = session
                    .byNaturalId(House.class)
                    .using("uuid", uuid)
                    .load();
            if (house != null) {
                log.info("Found a house with UUID: {}", uuid);
            } else {
                log.info("No house found with UUID: {}", uuid);
            }
            return Optional.ofNullable(house);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<House> findAll(int pageNumber, int pageSize) {
        try (Session session = sessionFactory.openSession()) {
            List<House> houses = session.createQuery("FROM House", House.class)
                    .setFirstResult((pageNumber - 1) * pageSize)
                    .setMaxResults(pageSize)
                    .getResultList();
            log.info("Retrieved {} houses from the database", houses.size());
            return houses;
        }
    }

    public List<House> findHousesByPersonUuid(UUID personUuid) {
        String sql = "SELECT * FROM houses h JOIN houseOwnership ho ON h.id = ho.house_id JOIN people p ON ho.person_id = p.id WHERE p.uuid = ? ";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(House.class), personUuid);
    }

    @Override
    @Transactional
    public House update(UUID uuid, House obj) {
        try (Session session = sessionFactory.openSession()) {
            House houseToUpdate = session
                    .byNaturalId(House.class)
                    .using("uuid", uuid)
                    .load();

            if (houseToUpdate != null) {
                House mergedHouse = session.merge(obj);
                log.info("Updated house with UUID: {}", uuid);
                return mergedHouse;
            } else {
                log.warn("Attempted to update non-existent house with UUID: {}", uuid);
                throw NotFoundException.of(House.class, uuid);
            }
        }
    }

    @Override
    @Transactional
    public void delete(UUID uuid) {
        try (Session session = sessionFactory.openSession()) {
            House houseToDelete = session
                    .byNaturalId(House.class)
                    .using("uuid", uuid)
                    .load();

            if (houseToDelete != null) {
                session.remove(houseToDelete);
                log.info("Deleted house with UUID: {}", uuid);
            } else {
                log.warn("Attempted to delete non-existent house with UUID: {}", uuid);
                throw NotFoundException.of(House.class, uuid);
            }
        }
    }
}