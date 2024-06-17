package com.suppdiff.domain.entities;

import java.util.List;

public class TicketSystem {
    private int id;
    private List<Client> clients;
    private List<Employee> employees;
    private List<Ticket> tickets;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public List<Client> getClients() {
        return clients;
    }
    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
    public List<Employee> getEmployees() {
        return employees;
    }
    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
    public List<Ticket> getTickets() {
        return tickets;
    }
    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
