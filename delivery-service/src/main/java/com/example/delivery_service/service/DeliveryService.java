package com.example.delivery_service.service;

import com.example.delivery_service.config.NoDeliveryPartnerException;
import com.example.delivery_service.dto.CustomerDto;
import com.example.delivery_service.dto.DeliveryRequest;
import com.example.delivery_service.dto.OrderRequestDto;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeliveryService {

    private final List<String> partners = new ArrayList<>();
    private final RestTemplate restTemplate;

    public DeliveryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        partners.add("Rahul");
        partners.add("Sneha");
        partners.add("Vikram");
    }

    public String assignDeliveryPartner(OrderRequestDto request) {
        if (partners.isEmpty()) {
            throw new NoDeliveryPartnerException("No delivery partners available → Trigger rollback");
        }

        String customerUrl = "http://localhost:8081/customers/find/" + request.getCustomerId();
        var customer = restTemplate.getForObject(customerUrl, CustomerDto.class);

        DeliveryRequest delivery_request = new DeliveryRequest(
            request.getCustomerId(),
            request.getResturantId(),
            request.getMenuItem(),
            request.getAmount(),
            request.getOrderStatus(),
            customer.getAddress()
        );


        String partner = partners.remove(0);
        return "Assigned " + partner + " to deliver " + delivery_request.getMenuItem() +
               " for customer " + delivery_request.getCustomerId() + " to '" + delivery_request.getCustomerAddress() + "'";
    }
}
