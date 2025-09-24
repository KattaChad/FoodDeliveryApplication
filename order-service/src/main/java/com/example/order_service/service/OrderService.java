package com.example.order_service.service;

import com.example.order_service.dto.CustomerDto;
import com.example.order_service.dto.OrderRequest;
import com.example.order_service.dto.ResturantDto;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final List<OrderRequest> orders = new ArrayList<>();
    private final RestTemplate restTemplate;

    public OrderService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<OrderRequest> getAllOrders() {
        return orders;
    }

    public OrderRequest placeOrder(OrderRequest orderRequest) {
        // Step 1: Check if restaurant exists
        String restaurantUrl = "http://localhost:8082/resturants/find/" + orderRequest.getResturantId(); //OK
        var restaurant = restTemplate.getForObject(restaurantUrl, ResturantDto.class); // Replace with Resturant DTO

        if (restaurant == null) {
            throw new RuntimeException("Restaurant not found");
        }

        // Step 2: Check if menu item exists
        String menuUrl = "http://localhost:8082/resturants/" + orderRequest.getResturantId() + "/menu/" + orderRequest.getMenuItem(); //OK
        Boolean itemExists = restTemplate.getForObject(menuUrl, Boolean.class);

        if (itemExists == null || !itemExists) {
            throw new RuntimeException("Menu item not found in restaurant");
        }

        // Step 3: Check if customer exists
        String customerUrl = "http://localhost:8081/customers/find/" + orderRequest.getCustomerId(); //OK
        var customer = restTemplate.getForObject(customerUrl, CustomerDto.class);

        if (customer == null) {
            throw new RuntimeException("Customer not found");
        }

        // Step 4: Check balance
        if (customer.getBalance() < orderRequest.getAmount()) {
            throw new RuntimeException("Insufficient balance");
        }

        // Step 5: Create new OrderRequest object
        OrderRequest order = new OrderRequest(
                orderRequest.getCustomerId(),
                orderRequest.getResturantId(),
                orderRequest.getMenuItem(),
                orderRequest.getAmount(),
                "PLACED"
        );

        // Deduct price from balance
        customer.setBalance(customer.getBalance() - orderRequest.getAmount());

        orders.add(order);

        
        
        // Step 6: Notify delivery service
        String deliveryUrl = "http://localhost:8084/delivery/notify"; //Build the damn delivery service
        restTemplate.postForObject(deliveryUrl, order, String.class);
        
        return order;
        
    }

    public void rollbackOrder(OrderRequest request) {
        String refundUrl = "http://localhost:8081/customers/refund/" + request.getCustomerId() + "/" + request.getAmount();
        String response = restTemplate.postForObject(refundUrl, null, String.class);
        System.out.println("Rollback executed -> customer refunded");
        System.out.print("Message from customer-service: ");
        System.out.println(response);
    }
}
