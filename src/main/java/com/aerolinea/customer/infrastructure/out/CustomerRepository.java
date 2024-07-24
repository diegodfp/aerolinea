package com.aerolinea.customer.infrastructure.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aerolinea.common.infrastructure.config.DatabaseConfig;
import com.aerolinea.customer.domain.entity.Customer;
import com.aerolinea.customer.domain.service.CustomerService;

public class CustomerRepository implements CustomerService {

    @Override
    public void createCustomer(Customer customer) {
        String sql = "INSERT INTO customers (id, name, age, idDocType, documentNumber) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, customer.getId());
            statement.setString(2, customer.getName());
            statement.setInt(3, customer.getAge());
            statement.setInt(4, customer.getIdDocType());
            statement.setString(5, customer.getDocumentNumber());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Customer findCustomerById(String id) {
        String sql = "SELECT id, name, age, idDocType, documentNumber FROM customers WHERE id = ?";
        Customer customer = null;

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    customer = new Customer();
                    customer.setId(resultSet.getString("id"));
                    customer.setName(resultSet.getString("name"));
                    customer.setAge(resultSet.getInt("age"));
                    customer.setIdDocType(resultSet.getInt("idDocType"));
                    customer.setDocumentNumber(resultSet.getString("documentNumber"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public void updateCustomer(Customer customer, String originalId) {
        String sql = "UPDATE customers SET id = ?, name = ?, age = ?, idDocType = ?, documentNumber = ? WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, customer.getId());
            statement.setString(2, customer.getName());
            statement.setInt(3, customer.getAge());
            statement.setInt(4, customer.getIdDocType());
            statement.setString(5, customer.getDocumentNumber());
            statement.setString(6, originalId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCustomer(String id) {
        String sql = "DELETE FROM customers WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isCustomerIdExists(String id) {
        String sql = "SELECT COUNT(*) FROM customers WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getString("id"));
                customer.setName(resultSet.getString("name"));
                customer.setAge(resultSet.getInt("age"));
                customer.setIdDocType(resultSet.getInt("idDocType"));
                customer.setDocumentNumber(resultSet.getString("documentNumber"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
}
