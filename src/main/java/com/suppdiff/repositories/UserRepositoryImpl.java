package com.suppdiff.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.suppdiff.domain.entities.Client;
import com.suppdiff.domain.entities.Person;
import com.suppdiff.infrastructure.config.DatabaseConfig;

public class UserRepositoryImpl {

    public void save(Person person) {
        String sql = "INSERT INTO Person (name, email, cpf, phone, password, birth_date) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, person.getName());
            statement.setString(2, person.getEmail());
            statement.setString(3, person.getCpf());
            statement.setString(4, person.getPhone());
            statement.setString(5, person.getPassword());
            statement.setDate(6, new java.sql.Date(person.getBirthDate().getTime()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Client getById(int id) {
        String sql = "SELECT * FROM Person WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Client person = new Client();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setEmail(resultSet.getString("email"));
                person.setCpf(resultSet.getString("cpf"));
                person.setPhone(resultSet.getString("phone"));
                person.setPassword(resultSet.getString("password"));
                person.setBirthDate(resultSet.getDate("birth_date"));
                return person;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Person getByEmail(String email) {
        String sql = "SELECT * FROM Person WHERE email = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setEmail(resultSet.getString("email"));
                person.setCpf(resultSet.getString("cpf"));
                person.setPhone(resultSet.getString("phone"));
                person.setPassword(resultSet.getString("password"));
                person.setBirthDate(resultSet.getDate("birth_date"));
                return person;
            }
        } catch (SQLException e) {
            return null;
        }
        return null;
    }

    public List<Person> getAll() {
        List<Person> people = new ArrayList<>();
        String sql = "SELECT * FROM Person";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setEmail(resultSet.getString("email"));
                person.setCpf(resultSet.getString("cpf"));
                person.setPhone(resultSet.getString("phone"));
                person.setPassword(resultSet.getString("password"));
                person.setBirthDate(resultSet.getDate("birth_date"));
                people.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return people;
    }

    public void update(Person person) {
        String sql = "UPDATE Person SET name = ?, email = ?, cpf = ?, phone = ?, password = ?, birth_date = ? WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, person.getName());
            statement.setString(2, person.getEmail());
            statement.setString(3, person.getCpf());
            statement.setString(4, person.getPhone());
            statement.setString(5, person.getPassword());
            statement.setDate(6, new java.sql.Date(person.getBirthDate().getTime()));
            statement.setInt(7, person.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM Person WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
