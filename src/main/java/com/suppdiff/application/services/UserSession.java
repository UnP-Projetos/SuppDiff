package com.suppdiff.application.services;

import com.suppdiff.application.enums.TypeUser;
import com.suppdiff.domain.entities.Person;

public class UserSession {
    private static UserSession instance;
    private Person loggedInUser;
    private TypeUser userType;

    private UserSession() {}

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public void setLoggedInUser(Person user, TypeUser userType) {
        this.loggedInUser = user;
        this.userType = userType;
    }

    public Person getLoggedInUser() {
        return loggedInUser;
    }

    public TypeUser getUserType() {
        return userType;
    }

    public void clearSession() {
        loggedInUser = null;
        userType = null;
    }
}

