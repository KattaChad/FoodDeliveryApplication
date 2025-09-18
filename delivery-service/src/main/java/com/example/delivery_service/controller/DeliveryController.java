package com.example.delivery_service.controller;
import com.example.delivery_service.dto.OrderRequestDto;
import com.example.delivery_service.service.DeliveryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping("/notify")
    public String notifyDelivery(@RequestBody OrderRequestDto request) {
        return deliveryService.assignDeliveryPartner(request);
    }

    @GetMapping
    public String test() {
        return "Delivery service is functional";
    }
}
