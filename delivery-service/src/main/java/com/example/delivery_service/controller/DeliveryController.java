package com.example.delivery_service.controller;
import com.example.delivery_service.config.NoDeliveryPartnerException;
import com.example.delivery_service.dto.OrderRequestDto;
import com.example.delivery_service.service.DeliveryService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    private final DeliveryService deliveryService;
    private RestTemplate restTemplate;

    public DeliveryController(DeliveryService deliveryService, RestTemplate restTemplate) {
        this.deliveryService = deliveryService;
        this.restTemplate = restTemplate;
    }

    @PostMapping("/notify")
    public ResponseEntity<String> notifyDelivery(@RequestBody OrderRequestDto request) {
        try {
            String result = deliveryService.assignDeliveryPartner(request);
            return ResponseEntity.ok(result);

        } catch(NoDeliveryPartnerException e) {
            // Logic to trigger rollback in order-service
            String url = "http://localhost:8083/orders/rollback";
            restTemplate.postForObject(url, request, String.class);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Rollback triggered: " + e.getMessage());
        }
    }

    @GetMapping
    public String test() {
        return "Delivery service is functional";
    }
}
