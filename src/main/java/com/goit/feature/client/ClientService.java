package com.goit.feature.client;

import com.goit.feature.exceptions.InvalidNameLengthException;
import lombok.RequiredArgsConstructor;

import javax.xml.namespace.QName;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientService {
    private PreparedStatement createSt;
    private PreparedStatement getByIdSt;
    private PreparedStatement selectMaxIdSt;

    public ClientService(Connection connection){
        try {
            createSt = connection.prepareStatement(
                    "INSERT INTO client (name) VALUES(?)"
            );

            getByIdSt = connection.prepareStatement(
                    "SELECT name FROM client WHERE id = ?"
            );

            selectMaxIdSt = connection.prepareStatement(
                    "SELECT max(id) AS maxId FROM client"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public long create(String name) throws SQLException {
        if (name.length() < 2) {
            throw new InvalidNameLengthException("Name too short!");
        } else if (name.length() > 1000) {
            throw new InvalidNameLengthException("Name too long!");
        }
        createSt.setString(1, name);
        createSt.executeUpdate();

        long id;

//        try (ResultSet generatedKeys = createSt.getGeneratedKeys()) {
//            if (generatedKeys.next()) {
//                id = generatedKeys.getLong("id");
//            }
//            else {
//                throw new SQLException("Creating client failed, no ID obtained.");
//            }
//        }

        try (ResultSet rs = selectMaxIdSt.executeQuery()) {
            rs.next();
            id = rs.getLong("maxId");
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
}
