package com.suppdiff.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.suppdiff.domain.entities.Client;
import com.suppdiff.domain.entities.Ticket;
import com.suppdiff.infrastructure.config.DatabaseConfig;

public class TicketRepositoryImpl {
    public void save(Ticket ticket) {
        String sql = "INSERT INTO Ticket (description, status, client_id) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, ticket.getDescription());
            statement.setString(2, ticket.getStatus());
            statement.setInt(3, ticket.getClient().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Ticket getById(int id) {
        String sql = "SELECT * FROM Ticket WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(resultSet.getInt("id"));
                ticket.setDescription(resultSet.getString("description"));
                ticket.setStatus(resultSet.getString("status"));
                
                Client client = new Client();
                client.setName(resultSet.getString("name"));
                client.setEmail(resultSet.getString("email"));
                client.setCpf(resultSet.getString("cpf"));
                client.setPhone(resultSet.getString("phone"));
                ticket.setClient(client);
                return ticket;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Ticket> getAll() {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT t.id, t.description, t.status, p.name, p.email, p.cpf, p.phone FROM Ticket t inner join Person p where p.id = t.client_id";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(resultSet.getInt("id"));
                ticket.setDescription(resultSet.getString("description"));
                ticket.setStatus(resultSet.getString("status"));
                Client client = new Client();
                client.setName(resultSet.getString("name"));
                client.setEmail(resultSet.getString("email"));
                client.setCpf(resultSet.getString("cpf"));
                client.setPhone(resultSet.getString("phone"));
                ticket.setClient(client);
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    public void update(Ticket ticket) {
        String sql = "UPDATE Ticket SET description = ?, status = ?, client_id = ?, employee = ? WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, ticket.getDescription());
            statement.setString(2, ticket.getStatus());
            statement.setInt(3, ticket.getClient().getId());
            statement.setInt(4, ticket.getEmployee().getId());
            statement.setInt(5, ticket.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM Ticket WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
