package com.suppdiff.domain.services;

import com.suppdiff.domain.entities.Person;

public class Authentication {
    private UserService userService = new UserService();

    public boolean Login(String email, String password) {
        Person person = userService.getByEmail(email);

        if (person != null && person.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }
}
