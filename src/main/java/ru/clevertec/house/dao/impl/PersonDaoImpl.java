package ru.clevertec.house.dao.impl;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.house.dao.Dao;
import ru.clevertec.house.entity.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PersonDaoImpl implements Dao<Person, UUID> {

    private final SessionFactory sessionFactory;

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
        try (Session session = sessionFactory.openSession()) {
            Person person = session.get(Person.class, uuid);
            return Optional.ofNullable(person);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Person> findAll(int pageNumber, int pageSize) {
        try (Session session = sessionFactory.openSession()) {
//            List<House> houses = session.createQuery("select * from House").getResultList()
//            return Optional.ofNullable(house);
        }
    }

    @Override
    @Transactional
    public void update(UUID uuid, Person obj) {
        try (Session session = sessionFactory.openSession()) {
            Optional<Person> personToUpdate = this.findByUuid(uuid);

            if (personToUpdate.isPresent()) {
                Person person = personToUpdate.get();
                person.setName(obj.getName());
                person.setSurname(obj.getSurname());
                person.setSex(obj.getSex());
                person.setPassport(obj.getPassport());
            }
        }
    }

    @Override
    @Transactional
    public void delete(UUID uuid) {
        try (Session session = sessionFactory.openSession()) {
            Optional<Person> personToDelete = this.findByUuid(uuid);
            if (personToDelete.isPresent()) {
                session.remove(personToDelete);
            }
        }
    }
}
