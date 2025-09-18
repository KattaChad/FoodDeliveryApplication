package com.example.delivery_service.controller;
import com.example.delivery_service.config.NoDeliveryPartnerException;
import com.example.delivery_service.dto.OrderRequestDto;
import com.example.delivery_service.service.DeliveryService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping("/notify")
    public ResponseEntity<String> notifyDelivery(@RequestBody OrderRequestDto request) {
        try {
            String result = deliveryService.assignDeliveryPartner(request);
            return ResponseEntity.ok(result);

        } catch(NoDeliveryPartnerException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public String test() {
        return "Delivery service is functional";
    }
}
