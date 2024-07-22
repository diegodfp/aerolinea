package com.aerolinea.model.infrastructure.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aerolinea.common.infrastructure.config.DatabaseConfig;
import com.aerolinea.model.domain.entity.Model;
import com.aerolinea.model.domain.service.ModelService;

public class ModelRepository implements ModelService {

    @Override
    public List<Model> getAllModels() {
          String sql = "SELECT id, name FROM models";
        List<Model> models = new ArrayList<>();
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Model model = new Model();
                model.setId(resultSet.getInt("id"));
                model.setName(resultSet.getString("name"));
                models.add(model);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return models;
    }
    

}
