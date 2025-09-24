package com.example.customer_service.controller;

import com.example.customer_service.dto.Customer;
import com.example.customer_service.service.CustomerService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String test() {
        return "Customer service is functional";
    }

    @GetMapping("/list")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/find/{id}")
    public Customer getCustomerById(@PathVariable int id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping("/add")
    public Customer addCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

    @PostMapping("/refund/{id}/{amount}")
    public ResponseEntity<String> refundBalance(@PathVariable int id, @PathVariable int amount) {
        customerService.addBalance(id, amount);
        return ResponseEntity.ok("Refunded " + amount + " to customer " + id);
    }
}
