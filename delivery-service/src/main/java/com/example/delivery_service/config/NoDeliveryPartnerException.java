package com.example.delivery_service.config;

public class NoDeliveryPartnerException extends RuntimeException {
    public NoDeliveryPartnerException(String message) {
        super(message);
    }
}
