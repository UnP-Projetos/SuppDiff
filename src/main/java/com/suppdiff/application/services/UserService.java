package com.suppdiff.application.services;

import java.util.List;

import com.suppdiff.application.DTO.UserDto;
import com.suppdiff.domain.entities.Administrator;
import com.suppdiff.domain.entities.Client;
import com.suppdiff.domain.entities.Employee;
import com.suppdiff.domain.entities.Person;

public class UserService {
    private final PersonService personService = new PersonService();
    private final AdministratorService administratorService = new AdministratorService();
    private final EmployeeService employeeService = new EmployeeService();
    private final ClientService clientService = new ClientService();

    public void save(UserDto userDto) {
        personService.save(userDto);
        Person person = personService.getByEmail(userDto.getEmail());
        int personId = person.getId();

        switch (userDto.getTypeUser()) {
            case ADMIN:
                Administrator admin = new Administrator();
                admin.setId(personId);
                administratorService.save(admin);
                break;

            case CLIENT:
                Client client = new Client();
                client.setId(personId);
                clientService.save(client);
                break;

            case EMPLOYEE:
                Employee employee = new Employee();
                employee.setId(personId);
                employeeService.save(employee);
                break;

            default:
                throw new IllegalArgumentException("Invalid user type");
        }
    }

    public Person getPersonById(int id) {
        return personService.getById(id);
    }

    public List<Person> getAllPersons() {
        return personService.getAll();
    }

    public void delete(UserDto userDto) {
        Person person = personService.getByEmail(userDto.getEmail());
        int personId = person.getId();
        switch (userDto.getTypeUser()) {
            case ADMIN:
                administratorService.delete(personId);
                break;

            case CLIENT:
                clientService.delete(personId);
                break;

            case EMPLOYEE:
                employeeService.delete(personId);
                break;

            default:
                throw new IllegalArgumentException("Invalid user type");
        }
        personService.delete(personId);
    }

    public List<Administrator> getAllAdministrators() {
        return administratorService.getAll();
    }

    public List<Client> getAllClients() {
        return clientService.getAll();
    }

    public List<Employee> getAllEmployees() {
        return employeeService.getAll();
    }

    public Administrator getAdministratorById(int id) {
        return administratorService.getById(id);
    }

    public Client getClientById(int id) {
        return clientService.getById(id);
    }

    public Employee getEmployeeById(int id) {
        return employeeService.getById(id);
    }
}