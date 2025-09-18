package com.example.order_service.dto;

import java.util.List;

public class ResturantDto {
    private String id;
    private String name;
    private List<String> menuItems;

    public ResturantDto(String id, String name, List<String> menuItems) {
        this.id = id;
        this.name = name;
        this.menuItems = menuItems;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getMenuItems() {
        return menuItems;
    }
}
