package com.suppdiff.application.services;

import java.util.List;

import com.suppdiff.domain.entities.Administrator;
import com.suppdiff.repositories.AdministratorRepositoryImpl;

public class AdministratorService {
    private AdministratorRepositoryImpl adminRepositoryImpl = new AdministratorRepositoryImpl();
    
    public void save(Administrator Administrator) {
        adminRepositoryImpl.save(Administrator);
    }

    public List<Administrator> getAll() {
        return adminRepositoryImpl.getAll();
    }

    public Administrator getById(int id) {
        return adminRepositoryImpl.getById(id);
    }

    public void delete(int id) {
        adminRepositoryImpl.delete(id);
    }
}
