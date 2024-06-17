package com.suppdiff.domain.services;

import java.util.List;

import com.suppdiff.domain.entities.Person;
import com.suppdiff.repositories.UserRepositoryImpl;

public class UserService {
    private UserRepositoryImpl userRepository = new UserRepositoryImpl();

    // public UserService(UserRepository userRepository) {
    //     this.userRepository = userRepository;
    // }

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
}
