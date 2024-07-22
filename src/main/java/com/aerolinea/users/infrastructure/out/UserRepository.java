package com.aerolinea.users.infrastructure.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.aerolinea.users.domain.entity.User;
import com.aerolinea.users.domain.service.UserAuthenticationPort;
import com.aerolinea.users.domain.service.UserService;
import com.aerolinea.common.infrastructure.config.DatabaseConfig;

public class UserRepository implements UserService, UserAuthenticationPort   {

    @Override
    public void createUser(User user) {
        String sql = "INSERT INTO usuario (nombre, email, password, rol_id) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql,
                        PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, user.getNombre());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getIdRol());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findUserById(int id) {
        String sql = "SELECT id, name, email, rol_id FROM usuario WHERE id = ?";
        User user = null;

        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setNombre(resultSet.getString("name"));
                    user.setEmail(resultSet.getString("email"));
                    user.setIdRol(resultSet.getInt("rol_id"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public void updateUser(User userUpdate) {
        String sql = "UPDATE usuario SET nombre = ?, email = ?, rol_id = ? WHERE id = ?";
        System.out.println(userUpdate.getId());
        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userUpdate.getNombre());
            statement.setString(2, userUpdate.getEmail());
            statement.setInt(3, userUpdate.getIdRol());
            statement.setInt(4, userUpdate.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int id) {
        String query = "DELETE FROM usuarios WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUsuarioByCredentials(String usuario, String password) {
        String query = "SELECT * FROM usuario WHERE nombre = ? AND password = ?";
        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, usuario);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setNombre(rs.getString("nombre"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setIdRol(rs.getInt("rol_id"));
                return user;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String obtenerRolUsuario(String nombreUsuario) {
        String query = "SELECT r.nombre FROM usuario u " +
                "JOIN rol r ON u.rol_id = r.id " +
                "WHERE u.nombre = ?";

        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, nombreUsuario);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("nombre");
                } else {
                    System.out.println("Usuario no encontrado.");
                    return null;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener rol del usuario: " + e.getMessage());
            return null;
        }

    }

}
