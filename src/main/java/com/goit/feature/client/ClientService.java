package com.goit.feature.client;

import com.goit.feature.exceptions.InvalidNameLengthException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
    private PreparedStatement createSt;
    private PreparedStatement getByIdSt;
    private PreparedStatement selectMaxIdSt;
    private PreparedStatement updateSt;
    private PreparedStatement deleteByIdSt;
    private PreparedStatement getAllSt;

    public ClientService(Connection connection){
        try {
            createSt = connection.prepareStatement(
                    "INSERT INTO client (name) VALUES(?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            getByIdSt = connection.prepareStatement(
                    "SELECT name FROM client WHERE id = ?"
            );

            selectMaxIdSt = connection.prepareStatement(
                    "SELECT max(id) AS maxId FROM client"
            );

            updateSt = connection.prepareStatement(
                    ("UPDATE client SET name = ? WHERE id = ?")
            );

            deleteByIdSt = connection.prepareStatement(
                    "DELETE FROM client WHERE id = ?"
            );

            getAllSt = connection.prepareStatement(
                    "SELECT id, name FROM client"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public long create(String name) throws SQLException {
        checkIfNameValid(name);
        createSt.setString(1, name);

        createSt.executeUpdate();

        long id;

        try (ResultSet generatedKeys = createSt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                id = generatedKeys.getLong("id");
            }
            else {
                throw new SQLException("Creating client failed, no ID obtained.");
            }
        }

        return id;
    }

    public Client getById(long id) throws SQLException {
        getByIdSt.setLong(1, id);

        try (ResultSet rs = getByIdSt.executeQuery()) {
            if (!rs.next()) {
                return null;
            }

            Client result = new Client();
            result.setId(id);
            result.setName(rs.getString("name"));

            return result;
        }
    }

    public void setName(long id, String name) throws SQLException {
        checkIfNameValid(name);
        updateSt.setString(1, name);
        updateSt.setLong(2, id);

        updateSt.executeUpdate();
    }

    public void deleteById(long id) throws SQLException {
        deleteByIdSt.setLong(1, id);

        deleteByIdSt.executeUpdate();
    }

    public List<Client> listAll() throws SQLException {
        try (ResultSet rs = getAllSt.executeQuery()) {
            List<Client> result = new ArrayList<>();
            while (rs.next()) {
                Client client = new Client();

                client.setId(rs.getLong("id"));
                client.setName(rs.getString("name"));

                result.add(client);
            }
            return result;
        }
    }

    private void checkIfNameValid(String name) {
        if (name.length() < 2) {
            throw new InvalidNameLengthException("Name too short!");
        } else if (name.length() > 1000) {
            throw new InvalidNameLengthException("Name too long!");
        }
    }
}
