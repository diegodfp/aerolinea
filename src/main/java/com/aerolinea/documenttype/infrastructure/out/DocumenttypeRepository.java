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


public class DocumenttypeRepository implements DocumenttypeService{
    @Override
    public List<Documenttype> getAllDocumenttypes() {
       String sql = "SELECT id, name FROM documenttype";
        List<Documenttype> statuses = new ArrayList<>();

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Documenttype status = new Documenttype();
                status.setId(resultSet.getInt("id"));
                status.setName(resultSet.getString("name"));
                statuses.add(status);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return statuses;
    }
}
