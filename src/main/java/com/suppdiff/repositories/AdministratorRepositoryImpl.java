package com.suppdiff.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.suppdiff.domain.entities.Administrator;
import com.suppdiff.infrastructure.config.DatabaseConfig;

public class AdministratorRepositoryImpl {
    public void save(Administrator administrator) {
        String sql = "INSERT INTO Administrator (id) VALUES (?)";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, administrator.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Administrator getById(int id) {
        String sql = "SELECT * FROM Administrator WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Administrator administrator = new Administrator();
                administrator.setId(resultSet.getInt("id"));
                return administrator;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Administrator> getAll() {
        List<Administrator> administrators = new ArrayList<>();
        String sql = "SELECT * FROM Administrator";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Administrator administrator = new Administrator();
                administrator.setId(resultSet.getInt("id"));
                administrators.add(administrator);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return administrators;
    }

    public void delete(int id) {
        String sql = "DELETE FROM Administrator WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
