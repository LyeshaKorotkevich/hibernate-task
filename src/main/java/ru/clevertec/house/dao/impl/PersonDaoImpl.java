package ru.clevertec.house.dao.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.house.dao.PersonDao;
import ru.clevertec.house.entity.Person;
import ru.clevertec.house.exception.NotFoundException;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Repository
@RequiredArgsConstructor
public class PersonDaoImpl implements PersonDao {

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

    @Override
    public List<Person> findTenantsByHouseUuid(UUID houseUuid) {
        String sql = "SELECT * FROM people p JOIN houses h ON h.id = p.house_id WHERE h.uuid = ? ";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Person.class), houseUuid);
    }

    @Override
    public List<Person> findByFullText(String text) {
        String sql = "SELECT * FROM people p WHERE p.name LIKE ? OR p.surname LIKE ? ";
        String searchText = "%" + text + "%";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Person.class), searchText, searchText);
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
    public Person patch(UUID uuid, Map<String, Object> updates) {
        try (Session session = sessionFactory.openSession()) {
            Person personToUpdate = session
                    .byNaturalId(Person.class)
                    .using("uuid", uuid)
                    .load();

            if (personToUpdate != null) {
                updates.forEach((key, value) -> {
                    Field field;
                    try {
                        field = Person.class.getDeclaredField(key);
                        field.setAccessible(true);
                        field.set(personToUpdate, value);
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        log.warn("Failed to apply update for field {}: {}", key, e.getMessage());
                    }
                });

                Person mergedPerson = session.merge(personToUpdate);
                log.info("Patched house with UUID: {}", uuid);
                return mergedPerson;
            } else {
                log.warn("Attempted to patch non-existent house with UUID: {}", uuid);
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
