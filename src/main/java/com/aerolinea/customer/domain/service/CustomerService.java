package com.aerolinea.customer.domain.service;

import java.util.List;

import com.aerolinea.customer.domain.entity.Customer;

public interface CustomerService {
    void createCustomer(Customer customer);

    Customer findCustomerById(String id);

    void updateCustomer(Customer customerUpdate, String id);

    void deleteCustomer(String plate);

    List<Customer> getAllCustomers();

    boolean isCustomerIdExists(String id);
}
