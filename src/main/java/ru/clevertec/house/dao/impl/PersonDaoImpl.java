package ru.clevertec.house.dao.impl;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.house.dao.Dao;
import ru.clevertec.house.entity.Person;
import ru.clevertec.house.exception.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PersonDaoImpl implements Dao<Person, UUID> {

    private final SessionFactory sessionFactory;

    private final JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public Person save(Person obj) {
        try (Session session = sessionFactory.openSession()) {
            session.persist(obj);
            return obj;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Person> findByUuid(UUID uuid) {
        String sql = "SELECT * FROM people WHERE uuid = ?";
        Person person = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Person.class), uuid).get(0);
        return Optional.ofNullable(person);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Person> findAll(int pageNumber, int pageSize) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Person ", Person.class)
                    .setFirstResult((pageNumber - 1) * pageSize)
                    .setMaxResults(pageSize)
                    .getResultList();
        }
    }

    public List<Person> findTenantsByHouseUuid(UUID houseUuid) {
        String sql = "SELECT * FROM people p JOIN houses h ON h.id = p.house_id WHERE h.uuid = ? ";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Person.class), houseUuid);
    }

    @Override
    @Transactional
    public Person update(UUID uuid, Person obj) {
        try (Session session = sessionFactory.openSession()) {
            Person personToUpdate = session.get(Person.class, uuid);

            if (personToUpdate != null) {
                return session.merge(obj);
            } else {
                throw NotFoundException.of(Person.class, uuid);
            }
        }
    }

    @Override
    @Transactional
    public void delete(UUID uuid) {
        try (Session session = sessionFactory.openSession()) {
            Person personToDelete = session.get(Person.class, uuid);

            if (personToDelete != null) {
                session.remove(personToDelete);
            } else {
                throw NotFoundException.of(Person.class, uuid);
            }
        }
    }
}
