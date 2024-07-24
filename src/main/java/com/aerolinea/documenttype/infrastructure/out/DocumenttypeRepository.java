package com.aerolinea.documenttype.infrastructure.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aerolinea.common.infrastructure.config.DatabaseConfig;
import com.aerolinea.documenttype.domain.entity.Documenttype;
import com.aerolinea.documenttype.domain.service.DocumenttypeService;

public class DocumenttypeRepository implements DocumenttypeService {

    @Override
    public void createDocumenttype(Documenttype documenttype) {
        String sql = "INSERT INTO documentTypes (name) VALUES (?)";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, documenttype.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Documenttype findDocumenttypeById(int id) {
        String sql = "SELECT id, name FROM documentTypes WHERE id = ?";
        Documenttype documenttype = null;

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    documenttype = new Documenttype();
                    documenttype.setId(resultSet.getInt("id"));
                    documenttype.setName(resultSet.getString("name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return documenttype;
    }

    @Override
    public void updateDocumenttype(Documenttype documenttype) {
        String sql = "UPDATE documentTypes SET name = ? WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, documenttype.getName());
            statement.setInt(2, documenttype.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDocumenttype(int id) {
        String sql = "DELETE FROM documentTypes WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Documenttype> getAllDocumenttypes() {
        List<Documenttype> documenttypes = new ArrayList<>();
        String sql = "SELECT * FROM documentTypes";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Documenttype documenttype = new Documenttype();
                documenttype.setId(resultSet.getInt("id"));
                documenttype.setName(resultSet.getString("name"));
                documenttypes.add(documenttype);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return documenttypes;
    }
}
