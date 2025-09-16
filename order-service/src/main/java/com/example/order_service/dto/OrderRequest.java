package com.example.order_service.dto;

public class OrderRequest {
    private int customerId;
    private int resturantId;
    private String menuItem;
    private int amount;
    private String orderStatus;

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public OrderRequest() {}

    public OrderRequest(int customerId, int resturantId, String menuItem, int amount, String orderStatus) {
        this.customerId = customerId;
        this.resturantId = resturantId;
        this.menuItem = menuItem;
        this.amount = amount;
        this.orderStatus = orderStatus;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getResturantId() {
        return resturantId;
    }

    public void setResturantId(int resturantId) {
        this.resturantId = resturantId;
    }

    public String getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(String menuItem) {
        this.menuItem = menuItem;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
