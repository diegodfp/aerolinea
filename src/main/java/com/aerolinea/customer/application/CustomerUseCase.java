package com.aerolinea.customer.application;

import java.util.List;

import com.aerolinea.customer.domain.entity.Customer;
import com.aerolinea.customer.domain.service.CustomerService;

public class CustomerUseCase {
    private final CustomerService customerService;

    public CustomerUseCase(CustomerService customerService) {
        this.customerService = customerService;
    }
    
    public void createCustomer(Customer customer){
        customerService.createCustomer(customer);
    }

    public void updateCustomer(Customer customerUpdate, String originalPlate){
        customerService.updateCustomer(customerUpdate, originalPlate);
    }

    public void deleteCustomer(String plate){
        customerService.deleteCustomer(plate);
    }

    public Customer findCustomerById(String id){
        return customerService.findCustomerById(id);
    }

    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }
}
