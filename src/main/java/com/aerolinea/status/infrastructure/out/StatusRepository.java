package com.aerolinea.status.infrastructure.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aerolinea.common.infrastructure.config.DatabaseConfig;
import com.aerolinea.status.domain.entity.Status;
import com.aerolinea.status.domain.service.StatusService;

public class StatusRepository implements StatusService {

    @Override
    public List<Status> getAllStatuses() {
       String sql = "SELECT id, name FROM status";
        List<Status> statuses = new ArrayList<>();

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Status status = new Status();
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
