package com.suppdiff.application.services;

import com.suppdiff.domain.entities.Person;

public class Authentication {
    private PersonService userService = new PersonService();

    public boolean Login(String email, String password) {
        Person person = userService.getByEmail(email);

        if (person != null && person.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }
}
