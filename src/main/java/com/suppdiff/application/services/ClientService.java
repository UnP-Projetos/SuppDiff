package com.suppdiff.application.services;

import java.util.List;
import com.suppdiff.domain.entities.Client;
import com.suppdiff.repositories.ClientRepositoryImpl;

public class ClientService {
    private ClientRepositoryImpl clientRepositoryImpl = new ClientRepositoryImpl();
    
    public void save(Client client) {
        clientRepositoryImpl.save(client);
    }

    public List<Client> getAll() {
        return clientRepositoryImpl.getAll();
    }

    public Client getById(int id) {
        return clientRepositoryImpl.getById(id);
    }

    public void delete(int id) {
        clientRepositoryImpl.delete(id);
    }
}
