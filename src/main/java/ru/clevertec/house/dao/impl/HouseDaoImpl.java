package ru.clevertec.house.dao.impl;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.house.dao.Dao;
import ru.clevertec.house.entity.House;

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
//            List<House> houses = session.createQuery("select * from House").getResultList()
//            return Optional.ofNullable(house);
        }
    }

    @Override
    @Transactional
    public void update(UUID uuid, House obj) {
        try (Session session = sessionFactory.openSession()) {
            Optional<House> houseToUpdate = this.findByUuid(uuid);

            if (houseToUpdate.isPresent()) {
                House house = houseToUpdate.get();
                house.setArea(obj.getArea());
                house.setCountry(obj.getCountry());
                house.setCity(obj.getCity());
                house.setStreet(obj.getStreet());
                house.setNumber(obj.getNumber());
            }
        }
    }

    @Override
    @Transactional
    public void delete(UUID uuid) {
        try (Session session = sessionFactory.openSession()) {
            Optional<House> houseToDelete = this.findByUuid(uuid);
            if (houseToDelete.isPresent()) {
                session.remove(houseToDelete);
            }
        }
    }
}
