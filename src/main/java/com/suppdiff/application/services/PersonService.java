package com.suppdiff.application.services;

import java.util.List;

import com.suppdiff.application.enums.TypeUser;
import com.suppdiff.domain.entities.Person;
import com.suppdiff.repositories.PersonRepositoryImpl;

public class PersonService {
    private PersonRepositoryImpl userRepository = new PersonRepositoryImpl();

    public void save(Person person) {
        userRepository.save(person);
    }

    public List<Person> getAll() {
        return userRepository.getAll();
    }

    public Person getById(int id) {
        return userRepository.getById(id);
    }

    public void update(Person person) {
        userRepository.update(person);
    }

    public void delete(int id) {
        userRepository.delete(id);
    }

    public Person getByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    public TypeUser getUserTypeById(int id) {
        return userRepository.getUserTypeById(id);
    }
}
