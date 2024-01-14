package ru.clevertec.house.dao.impl;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.house.dao.Dao;
import ru.clevertec.house.entity.House;
import ru.clevertec.house.exception.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class HouseDaoImpl implements Dao<House, UUID> {

    private final SessionFactory sessionFactory;

    @Override
    @Transactional
    public House save(House obj) {
        try (Session session = sessionFactory.openSession()) {
            session.persist(obj);
            return obj;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<House> findByUuid(UUID uuid) {
        try (Session session = sessionFactory.openSession()) {
            House house = session.get(House.class, uuid);
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
            return houses;
        }
    }

    @Override
    @Transactional
    public House update(UUID uuid, House obj) {
        try (Session session = sessionFactory.openSession()) {
            House houseToUpdate = session.get(House.class, uuid);

            if (houseToUpdate != null) {
                return session.merge(obj);
            } else {
                throw NotFoundException.of(House.class, uuid);
            }
        }
    }

    @Override
    @Transactional
    public void delete(UUID uuid) {
        try (Session session = sessionFactory.openSession()) {
            House houseToDelete = session.get(House.class, uuid);

            if (houseToDelete != null) {
                session.remove(houseToDelete);
            } else {
                throw NotFoundException.of(House.class, uuid);
            }
        }
    }
}
