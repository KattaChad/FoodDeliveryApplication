package com.example.customer_service.service;

import org.springframework.stereotype.Service;

import com.example.customer_service.dto.Customer;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private final List<Customer> customers = new ArrayList<>();

    // Adds data to customer list upon run time
    public CustomerService() {
        customers.add(new Customer(1, "Alice", "123 Main St", 1200));
        customers.add(new Customer(2, "Bob", "456 Park Ave", 200));
        customers.add(new Customer(3, "Charles", "761 Central Park(Yes he is homeless)", 0));
    }

    public List<Customer> getAllCustomers() {
        return customers;
    }

    public Customer getCustomerById(int id) {
        return customers.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Customer addCustomer(Customer customer) {
        customers.add(customer);
        return customer;
    }
}
