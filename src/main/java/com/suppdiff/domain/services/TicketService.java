package com.suppdiff.domain.services;

import java.util.List;

import com.suppdiff.domain.entities.Ticket;
import com.suppdiff.repositories.TicketRepositoryImpl;

public class TicketService {
    private TicketRepositoryImpl ticketRepository = new TicketRepositoryImpl();

    public void save(Ticket person) {
        ticketRepository.save(person);
    }

    public List<Ticket> getAll() {
        return ticketRepository.getAll();
    }

    public Ticket getById(int id) {
        return ticketRepository.getById(id);
    }

    public void update(Ticket person) {
        ticketRepository.update(person);
    }

    public void delete(int id) {
        ticketRepository.delete(id);
    }
}
