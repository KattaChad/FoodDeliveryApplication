package com.example.order_service.controller;

import com.example.order_service.dto.OrderRequest;
import com.example.order_service.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderRequest>> getAllOrders() {
        List<OrderRequest> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @PostMapping
    public ResponseEntity<OrderRequest> placeOrder(@RequestBody OrderRequest orderRequest) {
        OrderRequest placedOrder = orderService.placeOrder(orderRequest);
        return ResponseEntity.ok(placedOrder);
    }

    @PostMapping("/rollback")
    public ResponseEntity<String> rollbackOrder(@RequestBody OrderRequest request) {
        orderService.rollbackOrder(request);
        return ResponseEntity.ok("Rollback completed for order of customer " + request.getCustomerId());
    }

}
